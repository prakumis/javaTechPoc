package com.nyp.shopping.business.service;

import java.util.List;
import java.util.Map;

import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.vo.LanguageBean;

public interface I18nService {

	String DEFAULT = "DEFAULT";

	Map<String, MessageResourceLocale> getMessageResourceMap();

	Map<String, String> getMessageProperties(String language);

	List<LanguageBean> retrieveSupportedLanguages();

}
