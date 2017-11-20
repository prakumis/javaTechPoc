package com.nyp.shopping.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nyp.shopping.common.constants.ApplicationConstants;
import com.nyp.shopping.opensource.config.ClasspathConfigurator;

/**
 * AlcsContextListener uses {@link PropertiesConfiguration} to add additional
 * properties at application startup.
 * 
 * @author pmis30
 *
 */
@WebListener
public class ApplicationClasspathListener implements ServletContextListener {

	private Logger log = LoggerFactory.getLogger(getClass());
	private String configLocation;

	/**
	 * execution at application shutdown. does nothing.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// do nothing as there is no resource to be released during application
		// shutdown
	}

	/**
	 * updates the catalina.properties by adding new key-value pair
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("CONFDIR: "+System.getProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME));
		try {

			configLocation = System.getProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME);
			ClasspathConfigurator.addTomcatClasspath(configLocation);
		} catch (final Exception ex) {
			System.out.println("ExceptionEx: "+ex);
			log.error("Exception caught in ApplicationClasspathListener while setting configLocation: {}",
					configLocation, ex);
			throw new IllegalStateException(ex);
		}
	}
}
