package com.citylife.common.message;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class Messages extends ArrayList<MessageModel> {

  private static final long serialVersionUID = 1L;
  private static final int MAX_SIZE = 128;
  
  private Messages(int size) {
    super(size);
  }

  private Messages() {
    this(MAX_SIZE);
  }

  public static Messages build(MessageModel... messages) {
      return new Messages().add(messages);
  }

  public Messages add(MessageModel... messages) {
    if (! ArrayUtils.isEmpty(messages)) {
      this.addAll(Arrays.asList(messages));
    }
    return this;
  }

}
