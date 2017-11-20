package com.nyp.shopping.opensource.config;
/**
 * 
 */

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

/**
 * @author Java Developer
 *
 */
public class ClasspathConfigurator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void addTomcatClasspath(String location) throws ConfigurationException {

		// retrieve the sharedLocation from system properties
		String sharedLocation = System.getProperty(location);
		if(StringUtils.isBlank(sharedLocation)) {
			sharedLocation = location;
		}

		// retrieve the cataline.home from system properties
		String catalinaPropertyFilepath = System.getProperty("catalina.home") + "/conf/catalina.properties";
		// create instance of PropertiesConfiguration
		PropertiesConfiguration config = new PropertiesConfiguration(catalinaPropertyFilepath);

		// update the config with shared
		config.setProperty("shared.loader", sharedLocation);
		// save the changes in configuration
		config.save();
		System.out.println("CONFDIRRRR: "+sharedLocation);
	}

}
