package com.everis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

public class NameRequiredException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NameRequiredException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
		// TODO Auto-generated constructor stub
	}



}
