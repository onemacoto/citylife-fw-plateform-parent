package com.citylife.function.core.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;

import springfox.documentation.spring.web.json.Json;

@Configuration
public class FunctionWebMvcConfig implements WebMvcConfigurer {

//  @Autowired
//  private UserArgumentResolver userArgumentResolver;

  /**
   * 利用fastjson替换掉jackson，且解决中文乱码问题
   * 
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

    List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
    MediaType mediaTypeJson = MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE);
    supportedMediaTypes.add(mediaTypeJson);
    converter.setSupportedMediaTypes(supportedMediaTypes);

    FastJsonConfig config = new FastJsonConfig();
    config.getSerializeConfig().put(Json.class, new SwaggerJsonSerializer());
    config.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
    converter.setFastJsonConfig(config);
    converters.add(converter);

  }

//  @Override
//  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//    resolvers.add(userArgumentResolver);
//  }

}
