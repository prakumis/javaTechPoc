/**
 * 
 */
package com.nyp.shopping.business.application.impl;


import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.MessageResourceAS;
import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.constants.ApplicationConstants;

/**
 * @author its_me
 *
 */
@Service
public class MessageResourceASImpl implements MessageResourceAS {

	private static final Logger logger = LoggerFactory.getLogger(MessageResourceASImpl.class);

    private Map<String, MessageResourceLocale> messageResourceMap;

    /**
     * Service Initialization
     */
    @PostConstruct
    public void init() {

        Map<String, MessageResourceLocale> messageResourceMapTemp = new HashMap<>();
        final File[] propertyFileList = getPropertyFileList();

        for (File file : propertyFileList) {
			if (file.isDirectory()) {
				MessageResourceLocale messageResourceLocale = new MessageResourceLocale();
				Collection<File> listFiles = FileUtils.listFiles(file, new RegexFileFilter("^.+\\.properties"), DirectoryFileFilter.DIRECTORY);
				for (File innerFile : listFiles) {
					Properties props = new Properties();
					try {
						props.load(new FileReader(innerFile));
					} catch (IOException e) {
						e.printStackTrace();
					}
					messageResourceLocale.getPropertiesMap().put(getLocale(innerFile.getName())[1], props);
				}
				messageResourceMapTemp.put(file.getName(), messageResourceLocale);
			}

            /*final String fileName = file.getName();
            final String languageName = StringUtils.substringBefore(StringUtils.substringAfter(fileName, "_"), ".");
            final Properties props = getConfigProperties(fileName, classLoader);
            messageResourceMap.put(languageName.toLowerCase(), props);*/
        }
        messageResourceMap = messageResourceMapTemp;
        logger.info("message resources initialization completed: {}", messageResourceMap);
    }

	@Override
	public Map<String, MessageResourceLocale> getMessageResourceMap() {
		return messageResourceMap;
	}

	/**
	 * This method return an array that contains in position 0 base filename, in
	 * position 1 locale code. For example getLocale(messages_IT.properties)
	 * return {messages,IT}
	 * 
	 * @param fileName
	 * @return
	 * @throws I18nServiceException
	 */
	public String[] getLocale(String fileName)  {
		try {
			logger.trace("Start getLocale");
			if (fileName == null || fileName.trim().isEmpty())
				throw new RuntimeException("wrong input parameter: " + fileName);
			String[] split = fileName.split(".properties");
			int fromIndex = split[0].indexOf("_");
			if (fromIndex == -1) {
				return new String[] { split[0], "DEFAULT" };
			}
			return new String[] { split[0].substring(0, fromIndex), (split[0].substring(++fromIndex).toUpperCase()) };
		} catch (Exception e) {
			logger.error("Unable to getLocale");
			throw new RuntimeException("Unable to getLocale", e);
		} finally {
			logger.trace("End getLocale");
		}
	}

    private File[] getPropertyFileList() {

    	String messageConfigDir = System.getProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME) + File.separator
				+ ApplicationConstants.APPLICATION_MESSAGE_DIR;
        final File dir = new File(messageConfigDir);
        return dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
				//return name.toLowerCase().endsWith(".properties");
            	return true;
            }
        });
	}
}
