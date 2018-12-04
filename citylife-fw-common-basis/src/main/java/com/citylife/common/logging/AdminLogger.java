package com.citylife.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LogMarkers.ADMINI.toString());

  public static void info(String format, Object... args) {
    LOGGER.info(LogMarkers.ADMINI, format, args);
  }

  public static void warn(String format, Object... args) {
    LOGGER.warn(LogMarkers.ADMINI, format, args);
  }

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
