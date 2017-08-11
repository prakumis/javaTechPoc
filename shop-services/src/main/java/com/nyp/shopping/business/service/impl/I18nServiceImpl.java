/**
 * 
 */
package com.nyp.shopping.business.service.impl;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nyp.shopping.business.application.MessageResourceAS;
import com.nyp.shopping.business.service.I18nService;
import com.nyp.shopping.business.service.model.LanguageBean;
import com.nyp.shopping.business.service.model.MessageResourceLocale;

/**
 * @author pmis30
 *
 */
@Service
public class I18nServiceImpl implements I18nService {

	@Value("${language.resources}")
	private String prefixes;

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
			final Map<String, String> result, final List<String> prefixes) {

		for (Map.Entry<String, MessageResourceLocale> entry : messageResource.entrySet()) {
			final String prefix = entry.getKey();

			// If the prefix is NOT found in the list of prefixes (dashboard, catalog), then
			// skip
			if (prefixes != null && !prefixes.contains(prefix)) {
				continue;
			}

			// get the MessageResourceLocal for the given prefix
			final MessageResourceLocale messageResourceLocale = entry.getValue();
			// get the properties for the given language in the prefix
			Properties properties = messageResourceLocale.getPropertiesMap().get(language.toUpperCase());
			if (properties != null) {
				// iterate and keep the properties (key, value) in the result map
				for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
					result.put(String.format("%s.%s", prefix, objectEntry.getKey()), "" + objectEntry.getValue());
				}
			}
			// get the properties which belongs to DEFAULT language
			properties = messageResourceLocale.getPropertiesMap().get(I18nService.DEFAULT);
			if (properties != null) {
				for (Map.Entry<Object, Object> objectEntry : properties.entrySet()) {
					final String key = String.format("%s.%s", prefix, objectEntry.getKey());
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

		Map<String, MessageResourceLocale> messageResource = getMessageResourceMap();

		List<String> prefixesList = null;
		if (prefixes != null) {
			prefixesList = Arrays.asList(prefixes.split(","));
		}

		final Map<String, String> result = new LinkedHashMap<String, String>();
		buildMessages(language, messageResource, result, prefixesList);
		return result;
	}

	@Override
	public List<LanguageBean> retrieveSupportedLanguages() {

		return messageResourceAS.getSupportedLanguages();
	}

}
