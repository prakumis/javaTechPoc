package com.nyp.shopping.web.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by francesco on 2/11/14.
 */
public class DashboardException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String exceptionCode;
    private String exceptionMessage;
    private boolean error = true;
    private Integer httpCode;

    public DashboardException() {
    }

    public DashboardException( final String message ) {
        super( message );
    }

    public DashboardException( final String message, final Throwable cause ) {
        super( message, cause );
    }

    public DashboardException( final Throwable cause ) {
        super( cause );
    }

    public DashboardException( final String exceptionCode, final String message, final Throwable cause ) {
        super( message, cause );
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = message;
    }

    public DashboardException( final String exceptionCode, final String exceptionMessage ) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public DashboardException( final String exceptionCode, final String exceptionMessage, final boolean error ) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        this.error = error;
    }

    public DashboardException( final String exceptionCode, final String exceptionMessage, final boolean error, final Integer httpCode ) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        this.error = error;
        this.httpCode = httpCode;
    }

	public DashboardException( final String exceptionCode, final String exceptionMessage, final boolean error, final HttpStatus httpCode ) {
		this( exceptionCode, exceptionMessage, error, httpCode != null ? httpCode.value() : null );
	}


	public String getExceptionCode() {
        return exceptionCode;
    }
    public void setExceptionCode( final String exceptionCode ) {
        this.exceptionCode = exceptionCode;
    }
    public String getExceptionMessage() {
        return exceptionMessage;
    }
    public void setExceptionMessage( final String exceptionMessage ) {
        this.exceptionMessage = exceptionMessage;
    }
    public boolean isError() {
        return error;
    }
    public void setError( final boolean error ) {
        this.error = error;
    }
    public Integer getHttpCode() {
        return httpCode;
    }
    public void setHttpCode( final Integer httpCode ) {
        this.httpCode = httpCode;
    }
}
