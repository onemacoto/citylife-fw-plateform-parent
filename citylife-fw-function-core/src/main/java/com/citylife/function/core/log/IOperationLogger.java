package com.citylife.function.core.log;

import com.citylife.function.core.boot.template.action.IAction;

public interface IOperationLogger {
  void logOperationBegin(String method);

  void logOperationEnd(String method, long timeMillis);

  void logOperationBegin(IAction action);

  void logOperationEnd(IAction action, long timeMillis);
}
