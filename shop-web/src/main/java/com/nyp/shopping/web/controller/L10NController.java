package com.nyp.shopping.web.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import com.nyp.shopping.business.service.I18nService;
import com.nyp.shopping.common.vo.LanguageBean;

@RestController
@RequestMapping("/l10n")
public class L10NController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(L10NController.class);

	@Autowired
	private I18nService i18nService;

	@Autowired
	private LocaleResolver localeResolver;

	@Value("${language.resources}")
	private String prefixes;

    @RequestMapping ( method = RequestMethod.GET, value = "/languages" )
    public @ResponseBody List<LanguageBean> getLanguages() {
        logger.debug( "Retrieve all available languages" );
        return i18nService.retrieveSupportedLanguages();
    }

	@RequestMapping(method = RequestMethod.PUT, path = "/switchLocale")
	public String switchLocale(final HttpSession session, String language) {

		logger.debug("Change locale in : [{}]", language);
		return language;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/messages")
	public @ResponseBody Map<String, String> getMessages(HttpServletRequest request) {
		final Locale locale = localeResolver.resolveLocale(request);
		return getMessages(locale.getLanguage());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/messages/{language}")
	public @ResponseBody Map<String, String> getMessages(@PathVariable String language) {

		return i18nService.getMessageProperties(language);
	}

}
