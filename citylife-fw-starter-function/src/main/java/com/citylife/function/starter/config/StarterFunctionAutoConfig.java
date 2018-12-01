package com.citylife.function.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.citylife.function.starter.properties.StarterFunctionProperties;

@Configuration
@EnableConfigurationProperties(StarterFunctionProperties.class)
public class StarterFunctionAutoConfig {
	
	@Autowired
	private StarterFunctionProperties properties;
	
//	@Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "citylife.service", value = "enabled", havingValue = "true")
	
	
	

}
