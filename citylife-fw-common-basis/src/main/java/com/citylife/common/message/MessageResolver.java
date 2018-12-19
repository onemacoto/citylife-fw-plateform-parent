package com.citylife.common.message;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


public class MessageResolver {

  @Autowired
  private MessageSource messageSource;

  public MessageModel warn(String key,String defaultMessage) {
    return MessageModel.warn(getMessage(key, defaultMessage));
  }
  
  public MessageModel warn(String key,Object[] args,String defaultMessage) {
    return MessageModel.warn(getMessage(key, args, defaultMessage));
  }

  public MessageModel warn(String key,Object[] args,String defaultMessage, Locale locale) {
    return MessageModel.warn(getMessage(key, args, defaultMessage, locale));
  }

  public MessageModel info(String key,String defaultMessage) {
    return MessageModel.info(getMessage(key, defaultMessage));
  }
  
  public MessageModel info(String key,Object[] args,String defaultMessage) {
    return MessageModel.warn(getMessage(key, args, defaultMessage));
  }

  public MessageModel info(String key,Object[] args,String defaultMessage, Locale locale) {
    return MessageModel.warn(getMessage(key, args, defaultMessage, locale));
  }
  
  public MessageModel error(String key,String defaultMessage) {
    return MessageModel.error(getMessage(key, defaultMessage));
  }
  
  public MessageModel error(String key,Object[] args,String defaultMessage) {
    return MessageModel.error(getMessage(key, args, defaultMessage));
  }

  public MessageModel error(String key,Object[] args,String defaultMessage, Locale locale) {
    return MessageModel.error(getMessage(key, args, defaultMessage, locale));
  }

  public String getMessage(String key,String defaultMessage) {
      return getMessage(key,null, defaultMessage);
  }
  
  public String getMessage(String key,Object[] args,String defaultMessage) {
      return getMessage(key,args,defaultMessage, null);
  }

  public String getMessage(String key,Object[] args,String defaultMessage,Locale locale) {

    if (isEmpty(key)) {
      return defaultMessage;
    }

    if (locale == null) {
      locale = LocaleContextHolder.getLocale();
      if (locale == null) {
          locale = Locale.CHINA;
      }
    }

    return this.messageSource.getMessage(trimToEmpty(key), args, trimToEmpty(defaultMessage), locale);
  }

}
