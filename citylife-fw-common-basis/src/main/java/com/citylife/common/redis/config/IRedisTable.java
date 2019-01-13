package com.citylife.common.redis.config;

public interface IRedisTable {

	String getSchema();
	void setSchema(String schema);
	String getTable();
	void setTable(String table);
	String getColumn();
	void setColumn(String column);
	default String getColumnKey(String id) {
		return String.format("%s:%s:{%s}:%s", getSchema(), getTable(), id, getColumn());
	};
}
