package com.citylife.function.core.log;

import com.citylife.common.logging.OperationLogger;
import com.citylife.function.core.boot.template.action.IAction;

public class OperationLoggerImpl implements IOperationLogger {
  
  protected final static String START_LOG_FORMAT = "operation {} Begin";

  protected final static String END_LOG_FORMAT = "operation {} Complete(Totaltime = {})";

  @Override
  public void logOperationBegin(String method) {
    OperationLogger.info(START_LOG_FORMAT, method);
    
  }

  @Override
  public void logOperationEnd(String method, long timeMillis) {
    OperationLogger.info(END_LOG_FORMAT, method, timeMillis);
  }

  @Override
  public void logOperationBegin(IAction action) {
    logOperationBegin(action.getActionName());
  }

  @Override
  public void logOperationEnd(IAction action, long timeMillis) {
    logOperationEnd(action.getActionName(), timeMillis);
  }

}
