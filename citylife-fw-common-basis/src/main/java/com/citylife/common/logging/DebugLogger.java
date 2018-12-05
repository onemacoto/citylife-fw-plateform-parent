package com.citylife.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugLogger {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogMarkers.DEBUG.toString());

  public static void debug(String format, Object... args) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(LogMarkers.DEBUG, format, args);
    }
  }

  public static void debug(String msg, Throwable t) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(LogMarkers.DEBUG, msg, t);
    }
  }

  public static void trace(String format, Object... args) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace(LogMarkers.DEBUG, format, args);
    }
  }

  public static void trace(String msg, Throwable t) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace(LogMarkers.DEBUG, msg, t);
    }
  }
}
