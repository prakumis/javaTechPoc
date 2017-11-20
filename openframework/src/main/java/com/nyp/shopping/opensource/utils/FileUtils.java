package com.nyp.shopping.opensource.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

	public static Properties loadPropFromFile(File innerFile) {
		Properties props = new Properties();
		try {
			props.load(new FileReader(innerFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
