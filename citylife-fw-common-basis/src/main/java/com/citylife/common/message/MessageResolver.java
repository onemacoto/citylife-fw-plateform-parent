package com.citylife.common.message;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


public class MessageResolver {
  
  public static final String DEFAULT_MESSAGE = "undefined message";

  @Autowired
  private MessageSource messageSource;
  
  public MessageModel warn(String key) {
    return MessageModel.warn(getMessage(key));
  }
  
  
  public MessageModel warn(String key,Object[] args) {
    return MessageModel.warn(getMessage(key, args));
  }

  public MessageModel warn(String key,Object[] args, Locale locale) {
    return MessageModel.warn(getMessage(key, args, locale));
  }

  public MessageModel info(String key) {
    return MessageModel.info(getMessage(key));
  }
  
  public MessageModel info(String key,Object[] args) {
    return MessageModel.warn(getMessage(key, args));
  }

  public MessageModel info(String key,Object[] args, Locale locale) {
    return MessageModel.warn(getMessage(key, args, locale));
  }
  
  public MessageModel error(String key) {
    return MessageModel.error(getMessage(key));
  }
  
  public MessageModel error(String key,Object[] args) {
    return MessageModel.error(getMessage(key, args));
  }

  public MessageModel error(String key,Object[] args, Locale locale) {
    return MessageModel.error(getMessage(key, args, locale));
  }

  public String getMessage(String key) {
      return getMessage(key,null);
  }
  
  public String getMessage(String key,Object[] args) {
      return getMessage(key,args,null);
  }

  public String getMessage(String key,Object[] args,Locale locale) {
    
    if (isEmpty(key)) {
      return DEFAULT_MESSAGE;
    }

    if (locale == null) {
      locale = LocaleContextHolder.getLocale();
      if (locale == null) {
          locale = Locale.CHINA;
      }
    }

    return this.messageSource.getMessage(trimToEmpty(key), args, DEFAULT_MESSAGE, locale);
  }

}
