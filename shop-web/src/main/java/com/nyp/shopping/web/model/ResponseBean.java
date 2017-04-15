package com.nyp.shopping.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by francesco on 2/11/14.
 */
//@JsonSerialize(include=Inclusion.NON_NULL) // Jackson 1.9 or lower
@JsonInclude(Include.NON_NULL)             // Jackson 2 or higher
public class ResponseBean<T> {

	private Long totalResponseTime;
	private String status;
	@JsonIgnore
	private int httpStatucCode;
	private String message;
	private String developerMessage;
	private T data;

	public ResponseBean() {
		// do nothing
	}

	public ResponseBean(int httpStatucCode) {
		this.httpStatucCode = httpStatucCode;
	}

	public ResponseBean(String message) {
		this.message = message;
	}

	public ResponseBean(int httpStatucCode, String message) {
		this.httpStatucCode = httpStatucCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Long getTotalResponseTime() {
		return totalResponseTime;
	}

	public void setTotalResponseTime(Long totalResponseTime) {
		this.totalResponseTime = totalResponseTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getHttpStatucCode() {
		return httpStatucCode;
	}

	public void setHttpStatucCode(int httpStatucCode) {
		this.httpStatucCode = httpStatucCode;
	}

}
