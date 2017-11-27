package com.nyp.shopping.business.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.vo.LanguageBean;

public interface I18nService {

	String DEFAULT = "DEFAULT";

	Map<String, MessageResourceLocale> getMessageResourceMap();

	Map<String, String> getMessageProperties(String language);

	List<LanguageBean> retrieveSupportedLanguages();

	Map<String, String> getMessageProperties(String language, String messageType);

	Map<String, String> getMessageProperties(String language, String messageType, boolean inheritanceRequired);

	void updateMessageProperties(String language, String messageType, String key, String value);

	Map<String, Properties> getAppConfigsMap();

	void updateConfigProperties(String configName, String key, String value);

}
