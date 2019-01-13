package com.citylife.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.citylife.common.component.BeanMapper;
import com.citylife.common.component.JWTHelper;
import com.citylife.common.component.TraceHelper;
import com.citylife.common.message.MessageResolver;
import com.citylife.common.redis.config.RedisConfig;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@Configuration
@Import({RedisConfig.class})
public class StarterCommonBasisAutoConfig {

  @Bean
  @ConditionalOnMissingBean
  public Mapper mapper() {
    return DozerBeanMapperBuilder.buildDefault();
  }

  @Bean
  @ConditionalOnMissingBean
  public BeanMapper beanMapper() {
    BeanMapper bean = new BeanMapper();
    return bean;
  }

  @Bean
  @ConditionalOnMissingBean
  public JWTHelper jwtHelper() {
    JWTHelper bean = new JWTHelper();
    return bean;
  }

  @Bean
  @ConditionalOnMissingBean
  public MessageResolver messageResolver() {
    MessageResolver bean = new MessageResolver();
    return bean;
  }

  @Bean
  @ConditionalOnMissingBean
  public TraceHelper traceHelper() {
    TraceHelper bean = new TraceHelper();
    return bean;
  }

}
