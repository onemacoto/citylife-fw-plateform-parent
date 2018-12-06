package com.citylife.function.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.citylife.function.core.log.IOperationLogger;
import com.citylife.function.core.log.OperationLoggerImpl;
import com.citylife.function.starter.properties.StarterFunctionProperties;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
@EnableConfigurationProperties(StarterFunctionProperties.class)
@ConditionalOnProperty(prefix = "citylife.function", value = "enabled", matchIfMissing = true)
public class StarterFunctionAutoConfig {

  @Autowired
  private StarterFunctionProperties properties;

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "citylife.function", value = "mdcFilterEnabled", havingValue = "true")
  public FilterRegistrationBean<MDCInsertingServletFilter> mdcInsertingServletFilter() {
    FilterRegistrationBean<MDCInsertingServletFilter> registration = new FilterRegistrationBean<>(
        new MDCInsertingServletFilter());
    registration.addUrlPatterns("/*"); //
    registration.setName("mdcInsertingServletFilter");
    return registration;
  }
  
  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "citylife.function", value = "operationLogeerEnabled", matchIfMissing = true)
  public IOperationLogger operationLogger() {
    IOperationLogger bean = new OperationLoggerImpl();
    return bean;
  }
}
