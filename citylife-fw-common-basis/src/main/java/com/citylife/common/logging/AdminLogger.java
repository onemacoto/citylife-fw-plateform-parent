package com.citylife.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 管理用Logger
 * 
 * @author mingliang.chen
 */
public class AdminLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LogMarkers.ADMINI.toString());

  /**
   * 打印Info级别日志
   * @param format 日志输出格式
   * @param args 日志参数
   */
  public static void info(String format, Object... args) {
    LOGGER.info(LogMarkers.ADMINI, format, args);
  }

  /**
   * 打印警告级别日志
   * @param format 日志输出格式
   * @param args 日志参数
   */
  public static void warn(String format, Object... args) {
    LOGGER.warn(LogMarkers.ADMINI, format, args);
  }

  /**
   * 打印警告级别日志
   * @param format 日志输出格式
   * @param args 日志参数
   */  
  public static void warn(String msg, Throwable t) {
    LOGGER.warn(LogMarkers.ADMINI, msg, t);
  }

  public static void error(String format, Object... args) {
    LOGGER.error(LogMarkers.ADMINI, format, args);
  }

  public static void error(String msg, Throwable t) {
    LOGGER.error(LogMarkers.ADMINI, msg, t);
  }
}
