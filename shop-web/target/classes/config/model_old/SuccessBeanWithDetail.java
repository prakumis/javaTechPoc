package com.nyp.shopping.web.exception.model_old;

/**
 * Created by francesco on 2/11/14.
 */
public class SuccessBeanWithDetail<T> {

    private String code;
    private String message;
    private T detailObject;

    public SuccessBeanWithDetail() {
    }

    public SuccessBeanWithDetail( final String code, final String message ) {
        this.code = code;
        this.message = message;
    }
    public SuccessBeanWithDetail( final String code, final String message, final T detailObject ) {
        this.code = code;
        this.message = message;
        this.detailObject = detailObject;
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
    public T getDetailObject() {
        return detailObject;
    }
    public void setDetailObject( final T detailObject ) {
        this.detailObject = detailObject;
    }

	public SuccessBeanWithDetail<T> code( final String code ) {
		this.code = code;
		return this;
	}
	public SuccessBeanWithDetail<T> message( final String message ) {
		this.message = message;
		return this;
	}
	public SuccessBeanWithDetail<T> detailObject( final T detailObject ) {
		this.detailObject = detailObject;
		return this;
	}

}
