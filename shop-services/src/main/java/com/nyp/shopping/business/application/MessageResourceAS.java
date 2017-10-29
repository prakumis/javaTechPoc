/**
 * 
 */
package com.nyp.shopping.business.application;

import java.util.List;
import java.util.Map;

import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.vo.LanguageBean;

/**
 * @author pmis30
 *
 */
public interface MessageResourceAS {

	public Map<String, MessageResourceLocale> getMessageResourceMap();

	List<LanguageBean> getSupportedLanguages();

}