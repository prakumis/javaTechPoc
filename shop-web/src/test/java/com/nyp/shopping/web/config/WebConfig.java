package com.nyp.shopping.web.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.nyp.shopping.web.interceptor.CustomRequestHandler;
import com.nyp.shopping.web.listener.LogbackConfigListener;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.nyp.shopping" })
@ImportResource({ "classpath*:config/spring/shoppingApp-persistence.xml", "classpath*:config/spring/shoppingApp-service.xml" })
//@ContextConfiguration({"classpath:config/spring/shoppingApp-servlet-test.xml"})
@ContextConfiguration(classes={WebConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	public static LogbackConfigListener logbackConfigListener = new LogbackConfigListener();
	static {
		System.out.println("Static started");
		System.setProperty("catalina.home", "D:/Application/apache-tomcat-9.0.0.M9_shop");
		logbackConfigListener.contextInitialized(null);
		System.out.println("Static Completed");
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomRequestHandler());
    }

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();

        exceptionMappings.put("com.nyp.exceptoin.MyBaseException", "error/404");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();

        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }
}