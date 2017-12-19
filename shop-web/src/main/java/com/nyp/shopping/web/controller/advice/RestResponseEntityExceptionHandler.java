package com.nyp.shopping.web.controller.advice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;
import javax.management.ServiceNotFoundException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nyp.shopping.business.application.MessageResourceAS;
import com.nyp.shopping.common.constants.ApplicationConstants;
import com.nyp.shopping.common.exception.AuthenticationException;
import com.nyp.shopping.web.exception.ApplicationValidationException;
import com.nyp.shopping.web.exception.DashboardException;
import com.nyp.shopping.web.exception.ErrorCode;
import com.nyp.shopping.web.exception.KeywordNotFoundException;
import com.nyp.shopping.web.model.ErrorBean;
import com.nyp.shopping.web.model.ResponseBean;

/**
 * For more details on this class, please read:
 * http://www.concretepage.com/spring/spring-mvc/spring-mvc-exception-handling-with-exceptionhandler-responseStatus-handlerexceptionresolver-example-global-exception
 * http://www.concretepage.com/spring/spring-mvc/spring-mvc-controlleradvice-annotation-example
 * http://www.concretepage.com/spring/spring-mvc/spring-mvc-validator-with-initbinder-webdatabinder-registercustomeditor-example
 * 
 * This class should return only ErrorBean populated with necessary info, which
 * will be converted into {@link ResponseBean} in {@link RestResponseBodyAdvice}
 * 
 * @author pmis30
 *
 */
//@ControllerAdvice("com.nyp.shopping.web.controller.rest")
@ControllerAdvice(annotations=RestController.class)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	//@Inject
	//@Autowired
	//private MessageSource messageSource;

	@Inject
	private MessageResourceAS messageResourceAS;

	@InitBinder
	public void dataBindingGlobal(WebDataBinder binder) {
		log.debug("RestResponseEntityExceptionHandler.dataBindingGlobal()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
	}

	/**
	 * The below @ModelAttribute annotated method is not required in case of
	 * REST service. It may be useful in case there is a view/jsp associated.
	 * 
	 * 2q 1 * @param model
	 */
	@ModelAttribute
	public void globalAttributes(Model model) {
		model.addAttribute("msg", "Welcome to My World!");
	}

	/**
	 * Probably this method is not used as handleMethodArgumentNotValid() is
	 * taking care of validation error.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = { ApplicationValidationException.class, KeywordNotFoundException.class })
	protected ErrorBean handleValidationErrors(ApplicationValidationException ex, WebRequest request) {

		HttpServletResponse response = ((ServletWebRequest) request).getResponse();
		response.setStatus(HttpStatus.BAD_REQUEST.value());

		// ideally ValidationBean should be returned from controller after
		// validation
		return new ErrorBean(ErrorCode.VALIDATION_ERROR, ex.getMessage(), ex.getValidationErrors());
	}

	@ResponseBody
	@ExceptionHandler(value = { DashboardException.class })
	protected ErrorBean handleConflict(DashboardException ex, WebRequest request) {

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
	protected ErrorBean handleServiceNotFoundException(Exception ex, WebRequest request) {

		log.error("Unable to find service 1", ex);
		return new ErrorBean(ErrorCode.SERVICE_NOT_FOUND, ex.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ErrorBean handleEntityNotFoundException(Exception ex, WebRequest request) {

		log.error("Unable to find service 2", ex);
		return new ErrorBean(ErrorCode.ENTITY_NOT_FOUND, ex.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { PersistenceException.class })
	protected ErrorBean handleEntityNotFoundException2(Exception ex, WebRequest request) {
		log.error("Unable to find service 3", ex);
		return new ErrorBean(ErrorCode.ENTITY_NOT_FOUND, ex.getMessage());
	}

	/**
	 * This method handles any Database error (i.e. DataAccessException) and
	 * converts the same into 500 internal server error. Ideally there should be
	 * no such instance, and all DataAccessException should be handled at
	 * service layer only. This method here is to track and insure that this
	 * exception is not propagated to this level.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { DataAccessException.class })
	protected ErrorBean handleExceptionInternal(Exception ex, WebRequest request) {
		log.error("Internal Exception caught: " + ex.getMessage(), ex);
		return new ErrorBean(ErrorCode.VALIDATION_ERROR, ex.getMessage());
	}

	/**
	 * This method handles the client error: like any input data validation
	 * http://www.baeldung.com/global-error-handler-in-a-spring-rest-api
	 * <p>
	 * {@inheritDoc}
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(error);
			errors.add(error.getField() + ": " + localizedErrorMessage);
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		ErrorBean apiError =  new ErrorBean(ErrorCode.VALIDATION_ERROR, ex.getMessage(), errors);
		apiError.setErrorMessage("The request from client has some validation error in input data");
		return new ResponseEntity<>(apiError, headers, status);
	}

	/**
	 * This is able to resolve the localized error message only from
	 * ValidationMessage.properties files. This is not able to resolve the
	 * localized error message from other resource bundle even if it is
	 * configured as a bean through JavaConfig or Xml Config.
	 * 
	 * @param fieldError
	 * @return
	 */
    private String resolveLocalizedErrorMessage(FieldError fieldError) {

    	Locale currentLocale = LocaleContextHolder.getLocale();

        //Read more: http://mrbool.com/how-to-implement-internationalization-and-localization-in-java/31035#ixzz4jlrS9wN0
    	//return messageSource.getMessage(fieldError, currentLocale);

		Properties localeErrorProperties = messageResourceAS.getMessageResourceMap()
				.get(ApplicationConstants.MESSAGE_TYPE_ERRORS).getPropertiesMap().get(currentLocale.getLanguage().toUpperCase());

		/*
		 * Build the errorMessage based on the VO's annotation message
		 * [@NotNull(message="{NotNull.name}")] i.e. "NotNull.name". 
		 */
		String errorMessage = localeErrorProperties.getProperty(fieldError.getDefaultMessage());
		//return localeErrorProperties.getProperty(fieldError.getCode()+"."+fieldError.getField());
		if(null == errorMessage) {
			// If its's value in errorMessage Property file is null, then
			errorMessage = localeErrorProperties.getProperty(fieldError.getCode()+"."+fieldError.getField());
		}
		if(null == errorMessage) {
			// If its's value in errorMessage Property file is null, then
			errorMessage = localeErrorProperties.getProperty(fieldError.getCode());
		}
		return errorMessage;
    }

	/**
	 * This method here is to log any such error for log analysis.
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.warn(ex.getMessage(), ex);
		return super.handleExceptionInternal(ex, body, headers, status, request);

	}

	/**
	 * This method here is to log any such error for log analysis.
	 * <p>
	 * {@inheritDoc}
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = { AuthenticationException.class })
	protected ErrorBean handleAuthenticationException(AuthenticationException ex) {
		log.warn(ex.getMessage(), ex);
		return new ErrorBean(ErrorCode.AUTHENTICATION_ERROR, ex.getMessage());
	}

}