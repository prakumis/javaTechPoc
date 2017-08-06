package com.nyp.shopping.business.service;

import java.util.Map;

import com.nyp.shopping.business.service.model.MessageResourceLocale;

public interface I18nService {

	String DEFAULT = "DEFAULT";

	Map<String, MessageResourceLocale> getMessageResourceMap();

	Map<String, String> getMessageProperties(String language);

}
