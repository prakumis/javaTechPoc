package com.nyp.shopping.common.constants;

public abstract class ApplicationConstants {

	public static final String CONFIG_PATH_PROPERTY_NAME = "CONFDIR";
	public static final String APPLICATION_CONFIG_DIR = "config";
	public static final String APPLICATION_MESSAGE_DIR = "message";
	public static final String APPLICATION_LOG_DIR = "logs";

	public static final String STRING_OK = "OK";
	public static final String ACTIVE_PROFILE = "testMem";	// testMem, testMysql
	public static final boolean ROLLBACK = true;
	public static final String UTF8 = "UTF-8";

	// email constants
	public static final String EMAIL_TEMPLATE_PATH = "/email/templates/";
	public static final String EMAIL_TEMPLATE_AUTHENTICATION_FAILURE = "emailPasswordResetTemplate.xml";

	private ApplicationConstants(){
		// do nothing
	}
}
