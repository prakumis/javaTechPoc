package com.nyp.shopping.web.model;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * Created by francesco on 2/11/14.
 */
public class ValidationBean extends ErrorBean {

	private Map<String, List<String>> validationErrors;

	public ValidationBean() {
		// no arg constructor, actually not required
	}

	public ValidationBean(String code, String message, Map<String, List<String>> validationErrors) {
		super(HttpStatus.BAD_REQUEST.value(), code, message);
		this.validationErrors = validationErrors;
	}

	public Map<String, List<String>> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Map<String, List<String>> validationErrors) {
		this.validationErrors = validationErrors;
	}

}
