package com.nyp.shopping.common.constants;

public abstract class ApplicationConstants {

	public static final String CONFIG_PATH_PROPERTY_NAME = "CONFDIR";
	public static final String APPLICATION_CONFIG_DIR = "config";
	public static final String APPLICATION_LOG_DIR = "logs";

	public static final String STRING_OK = "OK";
	public static final String ACTIVE_PROFILE = "testMem";	// testMem, testMysql
	public static final boolean ROLLBACK = true;

	private ApplicationConstants(){
		// do nothing
	}
}
