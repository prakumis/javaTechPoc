package com.nyp.shopping.common.constants;

public abstract class ApplicationConstants {

	public static final String CONFIG_PATH_PROPERTY_NAME = "config";
	public static final String APPLICATION_CONFIG_DIR = CONFIG_PATH_PROPERTY_NAME;
	public static final String APPLICATION_MESSAGE_DIR = "message";
	public static final String APPLICATION_LOG_DIR = "logs";

	public static final String STRING_OK = "OK";
	public static final String ACTIVE_PROFILE = "testMem";	// testMem, testMysql
	public static final boolean ROLLBACK = true;
	public static final String UTF8 = "UTF-8";

	// email constants
	public static final String EMAIL_TEMPLATE_PATH = "/email/templates/";
	public static final String EMAIL_TEMPLATE_AUTHENTICATION_FAILURE = "emailPasswordResetTemplate.xml";

	public static final String MESSAGE_TYPE_DASHBOARD = "dashboard";
	public static final String MESSAGE_TYPE_ERRORS = "errors";
	public static final String DOT = ".";
	public static final String SEPARATOR = null;
	public static final String FORWARD_SLASH = "/";
	public static final String URL_CONTEXT_ROOT = FORWARD_SLASH;
	public static final String URL_REST_API = "/api/*";
	public static final String SYSTEM_DB_STATUS = "Database Status";
	public static final String SYSTEM_EMAIL_STATUS = "Email Status";
	public static final String SYSTEM_STATUS_RUNNING = "Running";
	public static final String SYSTEM_STATUS_STOPPED = "Stopped";
	public static final String SECURITY_SERVICE_APP_URL = "/app";
	public static final String SECURITY_SECURE_MAPPING = "/secure";
	public static final String SECURITY_CONFIG = "security.config";
    public static final String SECURITY_CONFIG_MAPPING = "/app/*";

    public static final String APP_INITI_FILE = "appInitialization.yml";
    // tomcat role
    public static final String TOMCAT_ROLE = "manager-gui";
    public static final String TOMCAT_ROLE_ROLE1 = "role1";
	public static final String SPRING_MVC_SERVLET_URL_PATTERN = "/*";

	private ApplicationConstants(){
		// do nothing
	}
}
