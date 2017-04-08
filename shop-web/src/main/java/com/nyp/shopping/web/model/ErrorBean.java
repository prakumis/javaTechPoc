package com.nyp.shopping.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by francesco on 2/11/14.
 */
@JsonInclude(Include.NON_NULL)             // Jackson 2 or higher
public class ErrorBean extends ResponseBean<Object> {

	private String errorCode;
	private String errorMessage;

	public ErrorBean() {
		// no arg constructor
	}

	public ErrorBean(String code, String message) {
		this.errorCode=code;
		this.errorMessage=message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
