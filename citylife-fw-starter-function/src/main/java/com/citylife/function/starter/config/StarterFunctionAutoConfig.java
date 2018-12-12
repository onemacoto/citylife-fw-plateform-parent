package com.citylife.function.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.citylife.function.core.annotations.Dev;
import com.citylife.function.core.config.FunctionFeignConfig;
import com.citylife.function.core.config.FunctionWebMvcConfig;
import com.citylife.function.core.log.IOperationLogger;
import com.citylife.function.core.log.OperationLoggerImpl;
import com.citylife.function.core.method.support.IUserBuilder;
import com.citylife.function.core.method.support.PropertiesUserBuilder;
import com.citylife.function.core.method.support.RequestHeaderUserBuilder;
import com.citylife.function.core.method.support.UserArgumentResolver;
import com.citylife.function.core.properties.FunctionProperties;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

@Configuration
@EnableConfigurationProperties(FunctionProperties.class)
@Import({FunctionWebMvcConfig.class, FunctionFeignConfig.class})
public class StarterFunctionAutoConfig {

  @Autowired
  private FunctionProperties functionProperties;

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

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "citylife.function", value = "userArgumentResolverEnabled", matchIfMissing = true)
  public UserArgumentResolver userArgumentResolver() {
    UserArgumentResolver bean = new UserArgumentResolver();
    return bean;
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix="citylife.function", value = "mockUserBuilderEnabled", havingValue = "false")
  public IUserBuilder requestHeaderUserBuilder() {
    RequestHeaderUserBuilder bean = new RequestHeaderUserBuilder();
    return bean;
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix="citylife.function", value = "mockUserBuilderEnabled", havingValue = "true")
  @Dev
  public IUserBuilder propertiesUserBuilder() {
    PropertiesUserBuilder bean = new PropertiesUserBuilder(functionProperties);
    return bean;
  }
}
