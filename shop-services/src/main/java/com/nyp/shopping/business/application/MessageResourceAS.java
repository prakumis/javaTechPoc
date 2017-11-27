/**
 * 
 */
package com.nyp.shopping.business.application;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.vo.LanguageBean;

/**
 * @author pmis30
 *
 */
public interface MessageResourceAS {

	public Map<String, MessageResourceLocale> getMessageResourceMap();

	List<LanguageBean> getSupportedLanguages();

	Map<String, Properties> getAppConfigsMap();

	public void updateMessageProperties(String language, String messageType, String key, String value);

	public void updateConfigProperties(String configName, String key, String value);

}
