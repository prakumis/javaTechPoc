/**
 * 
 */
package com.nyp.shopping.business.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.MessageResourceAS;
import com.nyp.shopping.business.service.I18nService;
import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.constants.ApplicationConstants;
import com.nyp.shopping.common.vo.LanguageBean;

/**
 * @author pmis30
 *
 */
@Service
public class I18nServiceImpl implements I18nService {

	//@Value("${language.resources}")
	//private String prefixes;

	@Inject
	MessageResourceAS messageResourceAS;

	@Override
	public Map<String, MessageResourceLocale> getMessageResourceMap() {

		return messageResourceAS.getMessageResourceMap();
	}

	/**
	 * Build the message (key, value) pair for the given language
	 * 
	 * @param language
	 * @param messageResource
	 * @param result
	 * @param prefixes
	 */
	protected void buildMessages(final String language, final Map<String, MessageResourceLocale> messageResource,
			final Map<String, String> result, String messageType) {

		if (messageResource.containsKey(messageType)) {

			// get the MessageResourceLocal for the given prefix
			final MessageResourceLocale messageResourceLocale = messageResource.get(messageType);
			// get the properties for the given language in the prefix
			Properties properties = messageResourceLocale.getPropertiesMap().get(language.toUpperCase());
			if (properties != null) {
				// iterate and keep the properties (key, value) in the result map
				for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
					result.put(String.format("%s.%s", messageType, objectEntry.getKey()), "" + objectEntry.getValue());
				}
			}
			// get the properties which belongs to DEFAULT language
			properties = messageResourceLocale.getPropertiesMap().get(I18nService.DEFAULT);
			if (properties != null) {
				for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
					final String key = String.format("%s.%s", messageType, objectEntry.getKey());
					// if the key of DEFAULT properties not already found in result map
					if (!result.containsKey(key)) {
						result.put(key, "" + objectEntry.getValue());
					}
				}
			}

		}
	}

	@Override
	public Map<String, String> getMessageProperties(String language) {

		return getMessageProperties(language, ApplicationConstants.MESSAGE_TYPE_DASHBOARD);
	}

	public Map<String, String> getMessageProperties(String language, String messageType) {

		Map<String, MessageResourceLocale> messageResource = getMessageResourceMap();

		final Map<String, String> result = new LinkedHashMap<>();
		buildMessages(language, messageResource, result, messageType);
		return result;
	}

	@Override
	public List<LanguageBean> retrieveSupportedLanguages() {

		return messageResourceAS.getSupportedLanguages();
	}

}
