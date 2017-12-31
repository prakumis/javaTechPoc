package com.nyp.shopping.web.config;

import java.io.File;
import java.util.Properties;

import javax.servlet.FilterRegistration;
import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.nyp.shopping.business.config.ApplicationConfig;
import com.nyp.shopping.common.constants.ApplicationConstants;
import com.nyp.shopping.opensource.utils.YamlUtils;
import com.nyp.shopping.web.filter.TracerFilter;
import com.nyp.shopping.web.servlet.SecurityServlet;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;

/**
 * https://stackoverflow.com/questions/28877982/spring-java-config-with-multiple-dispatchers
 * 
 * @author Java Developer
 *
 */
public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println("Inside SpringWebApplicationInitializer.onStartup()");

		servletContext.setInitParameter("spring.profiles.active", "production");

		// root context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfig.class); // configuration class for root context
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// security servlet
		configureSecurityServlet(servletContext);

		// dispatcher servlet for springRestWebApplicationContext
		configureSpringRestWebApplicationContext(servletContext, rootContext);

		// dispatcher servlet for springMvcWebApplicationContext
		configureSpringMvcWebApplicationContext(servletContext, rootContext);

		// configure logback filters
		configureLoggingFilters(servletContext);
	}

	private void configureLoggingFilters(ServletContext servletContext) {

		FilterRegistration.Dynamic mdcInsertingServletFilter = servletContext.addFilter("MDCInsertingServletFilter",
				new MDCInsertingServletFilter());
		mdcInsertingServletFilter.addMappingForUrlPatterns(null, false, "/*");

		FilterRegistration.Dynamic tracerFilter = servletContext.addFilter("TracerFilter", new TracerFilter());
		tracerFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	private void configureSpringMvcWebApplicationContext(ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootContext) {

		AnnotationConfigWebApplicationContext springMvcWebApplicationContext = new AnnotationConfigWebApplicationContext();
		springMvcWebApplicationContext.setParent(rootContext);
		springMvcWebApplicationContext.register(SpringMvcWebConfig.class);
		ServletRegistration.Dynamic webSocketEntryPoint = servletContext.addServlet("dispatcherMvc",
				new DispatcherServlet(springMvcWebApplicationContext));
		webSocketEntryPoint.setLoadOnStartup(2);
		webSocketEntryPoint.addMapping("/");
	}

	private void configureSpringRestWebApplicationContext(ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootContext) {

		AnnotationConfigWebApplicationContext springRestWebApplicationContext = new AnnotationConfigWebApplicationContext();
		springRestWebApplicationContext.setParent(rootContext);
		springRestWebApplicationContext.register(SpringRestWebConfig.class, SwaggerConfig.class);
		ServletRegistration.Dynamic restEntryPoint = servletContext.addServlet("dispatcherRest",
				new DispatcherServlet(springRestWebApplicationContext));
		restEntryPoint.setLoadOnStartup(1);
		restEntryPoint.addMapping("/api/*");
	}

	private void configureSecurityServlet(ServletContext servletContext) {

		String fileName = System.getProperty(ApplicationConstants.CONFIG_PATH_PROPERTY_NAME).concat(File.separator)
				.concat(ApplicationConstants.APP_INITI_FILE);
		Properties appInitializationProp = YamlUtils.loadYamlProperty(fileName);
		String securityConfig = appInitializationProp.getProperty(ApplicationConstants.SECURITY_CONFIG);

		// create the security servlet

		// apply security to the servlet
		if (Boolean.valueOf(securityConfig)) {
			ServletRegistration.Dynamic securityServletConfig = servletContext
					.addServlet(SecurityServlet.class.getName(), SecurityServlet.class);
			securityServletConfig.addMapping(ApplicationConstants.SECURITY_SERVICE_APP_URL,
					ApplicationConstants.SECURITY_CONFIG_MAPPING);
			securityServletConfig.setLoadOnStartup(0);
			HttpConstraintElement httpConstraintElement = new HttpConstraintElement(TransportGuarantee.NONE,
					ApplicationConstants.TOMCAT_ROLE_ROLE1);
			ServletSecurityElement servletSecurityElement = new ServletSecurityElement(httpConstraintElement);
			securityServletConfig.setServletSecurity(servletSecurityElement);
			System.out.println("Tomcat Security Enabled");
		} else {
			ServletRegistration.Dynamic securityServletConfig = servletContext
					.addServlet(SecurityServlet.class.getName(), SecurityServlet.class);
			securityServletConfig.addMapping(ApplicationConstants.SECURITY_CONFIG_MAPPING);
			securityServletConfig.setLoadOnStartup(0);
			System.out.println("Tomcat Security NOT Enabled");
		}

	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// return new Class<?>[] { AppConfig.class };
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

}