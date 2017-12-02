package com.nyp.shopping.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nyp.shopping.web.interceptor.CustomRequestHandler;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = { "com.nyp.shopping" })
//@ContextConfiguration(classes={TestWebConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CustomRequestHandler());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
