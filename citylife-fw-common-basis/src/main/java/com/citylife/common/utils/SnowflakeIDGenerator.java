package com.citylife.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citylife.common.exception.SystemRuntimeException;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 11111111 11111111 11111111 1111111 11111111 11111111 11111111 11111111
 * <p>
 * <p>
 * Generate unique IDs using the Twitter Snowflake algorithm (see https://github.com/twitter/snowflake). Snowflake IDs
 * are 64 bit positive longs composed of:
 * - 41 bits time stamp
 * - 10 bits machine id
 * - 12 bits sequence number
 *
 * @author cosco
 * @link org.apache.marmotta.kiwi.generator.SnowflakeIDGenerator
 * @link https://segmentfault.com/a/1190000011282426
 * @link https://www.cnblogs.com/relucent/p/4955340.html
 * @link https://blog.csdn.net/morewindows/article/details/7354571
 */
public class SnowflakeIDGenerator {

    private static Logger log = LoggerFactory.getLogger(SnowflakeIDGenerator.class);

    /**
     * 标尺时间
     * 2018-10-01 12:00:00
     * 时间戳在64bits总所占位数: 41bits
     * 时间戳的取值范围 [0, 2199023255551]
     * 从标尺时间开始，2199023255551毫秒(69.73057年)之后此ID生成器将失效
     */
    private final long twepoch = 1538366400000L;
    /**
     * 数据中心在64bits中所占的位数: 10bits
     */
    private final long dataCenterIdBits = 10L;
    /**
     * 序列在64bits中所占的位数: 12bits
     * 序列的取值范围 [0, 4095]
     */
    private final long sequenceBits = 12L;
    /**
     * 数据中心的取值范围 [0, 1023]
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    /**
     * 数据中心左移偏移量: 12bits
     */
    private final long dataCenterIdShift = sequenceBits;
    /**
     * 时间戳左移偏移量：12+10=22bits
     */
    private final long timestampLeftShift = sequenceBits + dataCenterIdBits;
    /**
     * 序列mask
     * <p>
     * 00000000 00000000 00000000 0000000 00000000 00000000 00001111 11111111
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 数据中心ID
     */
    private long dataCenterId;
    /**
     * 原始算法默认从0开始, 改进方法：初始化时，随机取[0,1]其中一个
     * 毫秒内累计的规则:
     * 从0开始累积: 0,1,2,3,4...4095
     * 从1开始累积: 1,2,3,4,5...4095
     * 此字段涉及多线程并发写场景 设置volatile保障happens-before 让写立刻对其他线程可见
     */
    private volatile long sequence = ThreadLocalRandom.current().nextInt(2);
    /**
     * 上次生成ID的时间截
     * 此字段涉及多线程并发写场景 设置volatile保障happens-before 让写立刻对其他线程可见
     */
    private volatile long lastTimestamp = -1L;

    /**
     * 时钟回拨最大容忍值: CLOCK_BACKWARD_THRESHOLD毫秒
     */
    private final long CLOCK_BACKWARD_THRESHOLD = 1_000L;

    /**
     * @param dataCenterId 数据中心ID范围 [0, 1023]
     */
    public SnowflakeIDGenerator(long dataCenterId) {
        if (dataCenterId == 0) {
            try {
                this.dataCenterId = getDataCenterId();
            } catch (SocketException | NullPointerException e) {
                // 随机范围[1,1023]
                this.dataCenterId = ThreadLocalRandom.current().nextInt((int) maxDataCenterId) + 1;
                log.warn("SNOWFLAKE: could not determine machine address; using random dataCenterId:{}", this.dataCenterId);
            }
        } else {
            this.dataCenterId = dataCenterId;
        }

        if (this.dataCenterId > maxDataCenterId || dataCenterId < 0) {
            // 随机范围[1,1023]
            this.dataCenterId = ThreadLocalRandom.current().nextInt((int) maxDataCenterId) + 1;
            log.warn("SNOWFLAKE: dataCenterId > maxDataCenterId; using random dataCenterId:{}", this.dataCenterId);
        }

        log.info("SNOWFLAKE: initialised with dataCenterId:{}, sequence:{}", this.dataCenterId, this.sequence);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    protected long getDataCenterId() throws SocketException {
        NetworkInterface network = null;
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

        while (en.hasMoreElements()) {
            NetworkInterface nint = en.nextElement();
            if (!nint.isLoopback() && nint.getHardwareAddress() != null) {
                network = nint;
                break;
            }
        }

        byte[] mac = network.getHardwareAddress();

        byte rndByte = (byte) (ThreadLocalRandom.current().nextInt() & 0x000000FF);

        // take the last byte of the MAC address and a random byte as datacenter ID
        return ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) rndByte) << 8))) >> 6;
    }

    /**
     * Return the next unique id for the type with the given name using the generator's id generation strategy.
     *
     * @return
     * @throws CCFException 系统时钟发生回退异常 线程休眠被终止异常
     */
    public synchronized long getId() {

        // 当前系统时间戳：毫秒
        long timestamp = System.currentTimeMillis();

        // 如果当前时间小于上一次ID生成时的时间戳，说明系统时钟回退过这个时候应当抛出异常
        // 此处采取保守策略：如果时钟回退差值在CLOCK_BACKWARD_THRESHOLD毫秒之内，则等待时钟正常，否则抛出异常 让此次调用失败
        if (timestamp < lastTimestamp) {
            log.warn("Clock moved backwards. Refusing to generate id for {} milliseconds.", (lastTimestamp - timestamp));
            // 如果时钟回退 但是在CLOCK_BACKWARD_THRESHOLD毫秒之内 则选择睡眠等待
            if ((lastTimestamp - timestamp) < CLOCK_BACKWARD_THRESHOLD) {
                try {
                    // 强制线程睡眠 如果是高并发情况下会在此处形成线程在getId方法上排队等待获取锁现象
                    TimeUnit.MILLISECONDS.sleep(lastTimestamp - timestamp);
                } catch (InterruptedException ex) {
                    throw new SystemRuntimeException("系统时钟发生回退，线程:[" + Thread.currentThread().getName() + "在等待时钟恢复时被终止", ex);
                }
            } else {
                // 时钟回退 但是已经超过CLOCK_BACKWARD_THRESHOLD毫秒 让此次调用失败
                throw new SystemRuntimeException("系统时钟发生回退，并且超过" + CLOCK_BACKWARD_THRESHOLD + "毫秒。");
            }
        }

        // 如果是同一时间生成的(同一毫秒内), 则进行毫秒内序列
        // 这种情况只有在极高并发的情况下才会出现: 当前线程和上一个线程 或者是同一个线程前后两次获取本对象实例的锁
        if (lastTimestamp == timestamp) {
            // sequence累加并用sequenceMask防止溢出
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出，超过4095则归0
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            /**
             * 时间戳改变，毫秒内序列重置
             * 原始算法默认从0开始，但根据线上反馈在并发量不高的情况下会导致大量的偶数ID被生成
             * 因为并发量不高的情况下 线程进入getId方法的时差会大于1毫秒 因此上一次获取ID时的时间戳会很大概率不等于当前时间戳
             * 那就有很高的概率sequence都取0
             * 改进方法：初始化时，随机取[0,1]其中一个
             * 从0开始累积: 0,1,2,3,4...4095
             * 从1开始累积: 1,2,3,4,5...4095
             */
            sequence = ThreadLocalRandom.current().nextInt(2);
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        long id = ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | sequence;

        if (id < 0) {
            log.warn("ID is smaller than 0: {}", id);
        }

        return id;
    }
}
