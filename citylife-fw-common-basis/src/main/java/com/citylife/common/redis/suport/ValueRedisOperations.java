package com.citylife.common.redis.suport;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.Nullable;

import com.citylife.common.redis.config.IRedisTable;

public class ValueRedisOperations {

	@Autowired
	private ValueOperations<String, Object> valueOperations;

	public void set(IRedisTable column, String key, Object value) {
		valueOperations.set(column.getColumnKey(key), value);
	}

	public void set(IRedisTable column, String key, Object value, long timeout, TimeUnit unit) {
		valueOperations.set(column.getColumnKey(key), value, timeout, unit);
	}

	public void set(IRedisTable column, String key, Object value, Duration timeout) {
		valueOperations.set(column.getColumnKey(key), value, timeout);
	}

	@Nullable
	public Boolean setIfAbsent(IRedisTable column, String key, Object value) {
		return valueOperations.setIfAbsent(column.getColumnKey(key), value);
	}

	@Nullable
	public Boolean setIfAbsent(IRedisTable column, String key, Object value, long timeout, TimeUnit unit) {
		return valueOperations.setIfAbsent(column.getColumnKey(key), value, timeout, unit);
	}

	@Nullable
	public Boolean setIfAbsent(IRedisTable column, String key, Object value, Duration timeout) {
		return valueOperations.setIfAbsent(column.getColumnKey(key), value, timeout);
	}

	@Nullable
	public Boolean setIfPresent(IRedisTable column, String key, Object value) {
		return valueOperations.setIfPresent(column.getColumnKey(key), value);
	}

	@Nullable
	public Boolean setIfPresent(IRedisTable column, String key, Object value, long timeout, TimeUnit unit) {
		return valueOperations.setIfPresent(column.getColumnKey(key), value, timeout, unit);
	}

	@Nullable
	public Boolean setIfPresent(IRedisTable column, String key, Object value, Duration timeout) {
		return valueOperations.setIfPresent(column.getColumnKey(key), value, timeout);
	}

	public void multiSet(IRedisTable column, Map<String, Object> map) {
		valueOperations.multiSet(toTableColumMap(column, map));
	}

	@Nullable
	public Boolean multiSetIfAbsent(IRedisTable column, Map<String, Object> map) {
		return valueOperations.multiSetIfAbsent(toTableColumMap(column, map));
	}
	
	private Map<String, Object> toTableColumMap (IRedisTable column, Map<String, Object> map) {
		return Optional.ofNullable(map).map((v) -> {
			Map<String, Object> params = v.entrySet().stream()
					.collect(Collectors.toMap((e) -> (String) column.getColumnKey(e.getKey()), (e) -> e.getValue()));
			return params;
		}).orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	public <T> T get(IRedisTable column, String key) {
		return (T) valueOperations.get(column.getColumnKey(key));
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	public <T> T getAndSet(IRedisTable column, String key, T value) {
		return (T) valueOperations.getAndSet(column.getColumnKey(key), value);
	}
	
	@Nullable
	public List<?> multiGet(IRedisTable column, Collection<String> keys) {
		return valueOperations.multiGet(keys.stream().map(p -> column.getColumnKey(p)).collect(Collectors.toSet()));
	}
	
	@Nullable
	public Long increment(IRedisTable column, String key) {
		return valueOperations.increment(column.getColumnKey(key));
	}
	
	@Nullable
	public Long increment(IRedisTable column, String key, long delta) {
		return valueOperations.increment(column.getColumnKey(key), delta);
	}

	@Nullable
	public Double increment(IRedisTable column, String key, double delta) {
		return valueOperations.increment(column.getColumnKey(key), delta);
	}
	
	
	@Nullable
	public Long decrement(IRedisTable column, String key) {
		return valueOperations.decrement(column.getColumnKey(key));
	}

	@Nullable
	public Long decrement(IRedisTable column, String key, long delta) {
		return valueOperations.decrement(column.getColumnKey(key), delta);
	}
	
	@Nullable
	public Integer append(IRedisTable column, String key, String value) {
		return valueOperations.append(column.getColumnKey(key), value);
	}
	
	@Nullable
	public String get(IRedisTable column, String key, long start, long end) {
		return valueOperations.get(column.getColumnKey(key), start, end);
	}
	
	public void set(IRedisTable column, String key, Object value, long offset) {
		valueOperations.set(column.getColumnKey(key), value, offset);
	}
	
	
	@Nullable
	public Long size(IRedisTable column, String key) {
		return valueOperations.size(column.getColumnKey(key));
	}
	
	public ValueOperations<String, Object> getNativeValueOperations() {
		return valueOperations;
	}
	
	@Nullable
	public Boolean setBit(IRedisTable column, String key, long offset, boolean value) {
		return valueOperations.setBit(column.getColumnKey(key), offset, value);
	}
	
	@Nullable
	public Boolean getBit(IRedisTable column, String key, long offset) {
		return valueOperations.getBit(column.getColumnKey(key), offset);
	}

	@Nullable
	public List<Long> bitField(IRedisTable column, String key, BitFieldSubCommands subCommands) {
		return valueOperations.bitField(column.getColumnKey(key), subCommands);
	}

}
