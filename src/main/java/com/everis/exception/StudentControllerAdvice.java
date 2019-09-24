package com.everis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sun.el.stream.Optional;

//@ControllerAdvice
public class StudentControllerAdvice {
	
//	@ExceptionHandler(ilega)
//	  public ResponseEntity<VndErrors> notFoundException(final PersonNotFoundException e) {
//	    return error(e, HttpStatus.NOT_FOUND, e.getId().toString());
//	  }
	
//	@ExceptionHandler(ProductNotFoundException.class)
//	  public ResponseEntity<String> handleNotFound(final ProductNotFoundException ex) {
//	    //log.error(ex.getMessage(), ex);
//	    //final ErrorResponse error = new ErrorResponse();
//	    //error.setMsg("not found");
//	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//	  }
	
//	@ExceptionHandler(IllegalArgumentException.class)
//	  public ResponseEntity<String> IllegalArgumentException(final IllegalArgumentException e) {
////	    return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
//		return error;
//	  }
//	
//	 private ResponseEntity<VndErrors> error(
//		      final Exception exception, final HttpStatus httpStatus, final String logRef) {
//		    final String message =
//		        Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
//		    return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
//		  }

}
