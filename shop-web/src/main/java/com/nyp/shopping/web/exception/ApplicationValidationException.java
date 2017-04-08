package com.nyp.shopping.web.exception;

import java.util.List;
import java.util.Map;

/**
 * Created by francesco on 2/11/14.
 */
public class ApplicationValidationException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = 277697923635048585L;
	private Map<String, List<String>> validationErrors;

	public ApplicationValidationException(String exceptionMessage,
			Map<String, List<String>> validationErrors) {
		super(exceptionMessage);
		this.validationErrors = validationErrors;
	}

	public Map<String, List<String>> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Map<String, List<String>> validationError) {
		this.validationErrors = validationError;
	}

}
