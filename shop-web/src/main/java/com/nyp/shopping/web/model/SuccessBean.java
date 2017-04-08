package com.nyp.shopping.web.model;

/**
 * Created by francesco on 2/11/14.
 */
public class SuccessBean extends ResponseBean<Object> {

    private String code;
    private String message;

    public SuccessBean() {
    	// no arg constructor
    }

    public SuccessBean(String success, String string) {

    	this.code=success;
    	this.message = string;
    }

	public String getCode() {
        return code;
    }
    public void setCode( final String code ) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage( final String message ) {
        this.message = message;
    }
	public SuccessBean code( final String code ) {
		this.code = code;
		return this;
	}
	public SuccessBean message( final String message ) {
		this.message = message;
		return this;
	}


}
