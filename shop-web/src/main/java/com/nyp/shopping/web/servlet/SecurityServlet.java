package com.nyp.shopping.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nyp.shopping.common.constants.ApplicationConstants;

/**
 * Servlet implementation class SecurityServlet
 */
//@WebServlet(urlPatterns = {"/app", "/app/*"}, loadOnStartup=1)
//@ServletSecurity(@HttpConstraint(rolesAllowed = { ApplicationConstants.TOMCAT_ROLE }))
public class SecurityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init() throws ServletException {

		System.out.println("SecurityServlet.init()");
		super.init();
		System.out.println("SecurityServlet.init(2)");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	logger.warn("App Settings page accessd by User");
		String appUrl = request.getContextPath().concat(ApplicationConstants.SECURITY_SERVICE_APP_URL);
        RequestDispatcher rd = null;
        if (appUrl.equals(request.getRequestURI())) {
            rd = request.getRequestDispatcher("/secure/");
        } else {
            rd = request.getRequestDispatcher(request.getRequestURI().replaceAll(appUrl,
            		ApplicationConstants.SECURITY_SECURE_MAPPING));
        }
        request.setAttribute(ApplicationConstants.SECURITY_SECURE_MAPPING, true);
        rd.forward(request, response);
	}

}
