package com.nyp.shopping.web.exception.model_old;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by francesco on 2/11/14.
 */
public class ErrorBean {
	private String status;
	private String message;
	private Map<String, String> additionalParams;

	public ErrorBean() {
	}

	public void addAdditionalParam(String key, String value) {
		if (additionalParams == null) {
			additionalParams = new HashMap<String, String>();
		}
		additionalParams.put(key, value);
	}

	public ErrorBean(final String status, final String message) {
		this.status = status;
		this.message = message;
	}

	public ErrorBean(final String status, final String message, final Map<String, String> additionalParams) {
		this.status = status;
		this.message = message;
		this.additionalParams = additionalParams;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}
}
