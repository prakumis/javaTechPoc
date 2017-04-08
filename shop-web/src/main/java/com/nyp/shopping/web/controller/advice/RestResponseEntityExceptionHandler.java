package com.nyp.shopping.web.controller.advice;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.ServiceNotFoundException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nyp.shopping.web.exception.ApplicationValidationException;
import com.nyp.shopping.web.exception.CradleDashboardException;
import com.nyp.shopping.web.exception.ErrorCode;
import com.nyp.shopping.web.exception.KeywordNotFoundException;
import com.nyp.shopping.web.model.ErrorBean;
import com.nyp.shopping.web.model.ResponseBean;
import com.nyp.shopping.web.model.ValidationBean;

/**
 * For more details on this class, please read:
 * http://www.concretepage.com/spring/spring-mvc/spring-mvc-exception-handling-with-exceptionhandler-responseStatus-handlerexceptionresolver-example-global-exception
 * http://www.concretepage.com/spring/spring-mvc/spring-mvc-controlleradvice-annotation-example
 * http://www.concretepage.com/spring/spring-mvc/spring-mvc-validator-with-initbinder-webdatabinder-registercustomeditor-example
 * 
 * @author pmis30
 *
 */
@ControllerAdvice()
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
	public void dataBindingGlobal(WebDataBinder binder) {
		logger.debug("RestResponseEntityExceptionHandler.dataBindingGlobal()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
	}

	/**
	 * The below @ModelAttribute annotated method is not required in case of
	 * REST service. It may be useful in case there is a view/jsp associated.
	 * 
2q	1	 * @param model
	 */
	@ModelAttribute
	public void globalAttributes(Model model) {
		model.addAttribute("msg", "Welcome to My World!");
	}

	@ResponseBody
	@ExceptionHandler(value = { ApplicationValidationException.class, KeywordNotFoundException.class })
	protected ErrorBean handleValidationErrors(ApplicationValidationException ex, WebRequest request) {

		HttpServletResponse response = ((ServletWebRequest) request).getResponse();
		response.setStatus(HttpStatus.BAD_REQUEST.value());

		// ideally ValidationBean should be returned from controller after
		// validation
		return new ValidationBean(ex.getLocalizedMessage(), ex.getLocalizedMessage(), ex.getValidationErrors());
	}

	@ResponseBody
	@ExceptionHandler(value = { CradleDashboardException.class })
	protected ErrorBean handleConflict(CradleDashboardException ex, WebRequest request) {

		HttpServletResponse response = ((ServletWebRequest) request).getResponse();
		if (ex.getHttpCode() != null) {
			response.setStatus(ex.getHttpCode());
		} else {
			response.setStatus(500);
		}
		return new ErrorBean(ex.getExceptionCode(), ex.getExceptionMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { ServiceNotFoundException.class })
	protected ResponseBean<Object> handleServiceNotFoundException(Exception ex, WebRequest request) {

		logger.error("Unable to find service 1", ex);
		return new ResponseBean<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseBean<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {

		logger.error("Unable to find service 2", ex);
		return new ResponseBean<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { PersistenceException.class })
	protected ErrorBean handleEntityNotFoundException2(Exception ex, WebRequest request) {
		logger.error("Unable to find service 3", ex);
		return new ErrorBean(ErrorCode.ENTITY_NOT_FOUND, ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.warn(ex.getMessage(), ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);

	}

}