package com.citylife.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationLogger {

  /** Logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(LogMarkers.OPERATION.toString());

  public static void info(String format, Object... args) {
    LOGGER.info(LogMarkers.OPERATION, format, args);
  }
}
