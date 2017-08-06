/**
 * 
 */
package com.nyp.shopping.business.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Link: http://www.baeldung.com/properties-with-spring, section: 3. Register a
 * Properties File via Java Annotations
 * 
 * @author its_me
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:config/database/jdbc.properties")
public class MessageConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// @Bean
	public MessageSource messageSource() {

		// TODO: ReloadableResourceBundleMessageSource need to be tested for
		// re-loading
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		// all the properties files are loaded in service context so that it
		// could be used any where in app
		//messageSource.setBasenames("file:${CONFDIR}/config/message/errorMessage", "classpath:ValidationMessages");
		messageSource.setBasenames("file:${CONFDIR}/config/message/errorMessage", "classpath:ValidationMessages");
		// if true, the key of the message will be displayed if the key is not
		// found, instead of throwing a NoSuchMessageException
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		// # -1 : never reload, 0 always reload
		messageSource.setCacheSeconds(10000);

		// Test the message resource by printing a key-value pair
		System.out.println("MessageConfig.messageSource()");
		String value = messageSource.getMessage("error.NotNull.name", new Object[] {}, "defaultMessage",
				LocaleContextHolder.getLocale());
		System.out.println("Value for key: " + value);
		return messageSource;
	}
}
