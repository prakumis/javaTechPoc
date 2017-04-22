package com.nyp.shopping.web.exception;

import java.util.List;

/**
 * Created by francesco on 2/11/14.
 */
public class ApplicationValidationException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 277697923635048585L;
	private List<String> validationErrors;

	public ApplicationValidationException(String exceptionMessage) {

		super(exceptionMessage);
	}

	public ApplicationValidationException(String exceptionMessage, List<String> validationErrors) {
		super(exceptionMessage);
		this.validationErrors = validationErrors;
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<String> validationError) {
		this.validationErrors = validationError;
	}

}
