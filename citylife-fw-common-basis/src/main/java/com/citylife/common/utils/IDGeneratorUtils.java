package com.citylife.common.utils;

public class IDGeneratorUtils {
  private static final SnowflakeIDGenerator SNOWFLAKE_ID_GENERATOR = new SnowflakeIDGenerator(0);
  public static long getId() {
    return SNOWFLAKE_ID_GENERATOR.getId();
  }
}
