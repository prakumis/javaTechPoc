package com.nyp.shopping.web.listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nyp.shopping.common.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Listener for logback to read external configuration.
 * 
 * @author pmis30
 */
public class LogbackConfigListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(LogbackConfigListener.class);
	private String configPath;

	public void contextInitialized(ServletContextEvent sce) {

		try {
			initializeConfigPath();
			initializeLogger();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// do nothing
	}

	private void initializeLogger() throws IOException, JoranException {

		String loggerFileName = configPath + "logback.xml";
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		File externalLogbackConfigFile = new File(loggerFileName);
		if (!externalLogbackConfigFile.exists()) {
			throw new IOException("Logback External Config File Parameter does not reference a file that exists");
		} else {
			if (!externalLogbackConfigFile.isFile()) {
				throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
			} else {
				if (!externalLogbackConfigFile.canRead()) {
					throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
				} else {
					JoranConfigurator configurator = new JoranConfigurator();
					configurator.setContext(lc);
					lc.reset();
					String logDir = System.getProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME) + File.separator
							+ ApplicationConstants.APPLICATION_LOG_DIR;
					lc.putProperty("LOG_LOC", logDir);
					configurator.doConfigure(externalLogbackConfigFile);

					logger.info("Configured Logback with config file from: " + loggerFileName);
				}
			}
		}
	}

	private void initializeConfigPath() {

		configPath = System.getProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME);
		if (null == configPath) {
			throw new InvalidPathException(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME,
					"Application configuration location not found", -1);
		}
		configPath = configPath + File.separator + ApplicationConstants.APPLICATION_CONFIG_DIR + File.separator;
	}

}
