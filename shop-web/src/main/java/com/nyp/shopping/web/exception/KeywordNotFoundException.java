package com.nyp.shopping.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nyp.shopping.web.controller.advice.RestResponseEntityExceptionHandler;
import com.nyp.shopping.web.model.ResponseBean;

/**
 * There is no handler method here to catch this exception, wrap it in
 * {@link ResponseBean} and to send it to client. The better approach is to have an
 * handlerxxxxxx() method in a @ControllerAdvice, as in {@link RestResponseEntityExceptionHandler},
 * which wraps the exception in {@link ResponseBean} along with statusCode and
 * errorMessage and returns the same to client.
 * 
 * @author pmis30
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This error should not happen")
public class KeywordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public KeywordNotFoundException(String key) {
		super(key + " not available");
	}
}