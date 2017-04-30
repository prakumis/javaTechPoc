package com.nyp.shopping.common.constants;

import java.io.File;

public abstract class ApplicationConstants {

	public static final String CONFIG_PATH_PROPERTY_NAME = "catalina.home";
	public static final String APPLICATION_CONFIG_DIR = "shop"+File.separator+"configuration";
	public static final String APPLICATION_LOG_DIR = "shop"+File.separator+"logs";

	public static final String STRING_OK = "OK";
	public static final String ACTIVE_PROFILE = "testMem";	// testMem, testMysql
	public static final boolean ROLLBACK = true;

	private ApplicationConstants(){
		// do nothing
	}
}
