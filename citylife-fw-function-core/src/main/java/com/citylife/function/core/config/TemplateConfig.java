package com.citylife.function.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.citylife.function.core.boot.template.TemplateService;
import com.citylife.function.core.boot.template.context.TemplateActionContextFactory;

@Configuration
public class TemplateConfig {

	@Bean
	@ConditionalOnMissingBean
	public <T> TemplateActionContextFactory<T> actionContextFactory() {
		TemplateActionContextFactory<T> bean = new TemplateActionContextFactory<>();
		return bean;
	}

	@Bean
	@ConditionalOnMissingBean
	public TemplateService templateService() {
	  TemplateService bean = new TemplateService();
	  return bean;
	}
}
