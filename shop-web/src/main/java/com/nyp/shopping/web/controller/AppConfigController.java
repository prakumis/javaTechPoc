package com.nyp.shopping.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyp.shopping.business.service.I18nService;
import com.nyp.shopping.business.service.SystemService;
import com.nyp.shopping.business.service.model.MessageResourceLocale;
import com.nyp.shopping.common.vo.LanguageBean;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

@Controller
@RequestMapping("/secure")
public class AppConfigController extends BaseController {

	@Autowired
	private I18nService i18nService;

	@Autowired
	private SystemService systemService;

	@RequestMapping(method = RequestMethod.GET)
	public String init(HttpServletRequest request) {

		Map<String, MessageResourceLocale> messageResources = i18nService.getMessageResourceMap();
		request.setAttribute("messageResources", messageResources);

		request.setAttribute("configNames", i18nService.getAppConfigsMap().keySet());
		return "homeTiles";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/system/status")
	public String getSystemStatus(HttpServletRequest request) {

		Map<String, String> systemStatus = systemService.getSystemStatus();
		request.setAttribute("systemStatus", systemStatus);
		return "showSystemStatusAjax";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logger")
	public String getLogger(HttpServletRequest request) {
		return "setLogAjax";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/logger")
	public String setLogger(HttpServletRequest request) {

		final String levels = request.getParameter("levels");
		final String loggerName = request.getParameter("logger");
		logger.info("setting new level.................. {}", levels);
		setLevel(levels, loggerName);
		return getLogger(request);
	}

	public static void setLevel(String level, String loggerName) {
		Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
		logger.setLevel(Level.valueOf(level));
	}

	List<ch.qos.logback.classic.Logger> findNamesOfConfiguredLoggers() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		List<ch.qos.logback.classic.Logger> loggerList = new ArrayList<>();
		for (ch.qos.logback.classic.Logger log : lc.getLoggerList()) {
			if (log.getLevel() != null || hasAppenders(log)) {
				loggerList.add(log);
			}
		}
		return loggerList;
	}

	boolean hasAppenders(ch.qos.logback.classic.Logger logger) {
		Iterator<Appender<ILoggingEvent>> it = logger.iteratorForAppenders();
		return it.hasNext();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/languages")
	public String getLanguages(HttpServletRequest request) {
		List<LanguageBean> languages = i18nService.retrieveSupportedLanguages();
		request.setAttribute("languages", languages);
		request.setAttribute("messageType", request.getParameter("messageType"));
		return "languagesAjax";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/messages/{messageType}/{language}")
	public String getMessagesFromTypeAndLocale(HttpServletRequest request, @PathVariable String messageType,
			@PathVariable String language) {

		Map<String, String> messages = i18nService.getMessageProperties(language, messageType);
		request.setAttribute("messages", messages);
		return "messagesAjax";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/messages/{messageType}/{language}")
	public String updateMessagesForTypeAndLocale(HttpServletRequest request, @PathVariable String messageType,
			@PathVariable String language) {

		i18nService.updateMessageProperties(language, messageType, request.getParameter("key"),
				request.getParameter("value"));
		return getMessagesFromTypeAndLocale(request, messageType, language);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/config/{configName}")
	public String getConfig(HttpServletRequest request, @PathVariable String configName, Model model) {

		model.addAttribute("configName", configName);
		model.addAttribute("configTemp", i18nService.getAppConfigsMap().get(configName));
		model.addAttribute("config", i18nService.getAppConfigsMap().get(configName));
		return "configAjax";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/config/{configName}")
	public String updateConfig(HttpServletRequest request, @PathVariable String configName, Model model) {

		i18nService.updateConfigProperties(configName, request.getParameter("key"), request.getParameter("value"));
		return getConfig(request, configName, model);
	}

}
