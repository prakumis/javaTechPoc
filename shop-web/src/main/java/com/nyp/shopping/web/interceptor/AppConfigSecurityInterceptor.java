package com.nyp.shopping.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nyp.shopping.common.constants.ApplicationConstants;

@Component
public class AppConfigSecurityInterceptor extends HandlerInterceptorAdapter {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Object secureStatus = request.getAttribute(ApplicationConstants.SECURITY_SECURE_MAPPING);
		if (null != secureStatus && (boolean) secureStatus) {
			return true;
		}
		logger.error("Application Settings page invaded by User for the URI: " + request.getRequestURI());
		return false;
	}
}
