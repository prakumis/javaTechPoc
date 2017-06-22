package com.nyp.shopping.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/l10n")
public class L10NController extends BaseController {

	@RequestMapping(method = RequestMethod.PUT, path = "/switchLocale")
	public String switchLocale(final HttpSession session, String language) {

		logger.debug("Change locale in : [{}]", language);
		return language;
	}

}
