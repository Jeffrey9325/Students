package com.everis.exception;

public class ProductNotFoundException extends RuntimeException {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(final String message) {
		    super(String.format("product[%s].notFound", message));
		  }

}
