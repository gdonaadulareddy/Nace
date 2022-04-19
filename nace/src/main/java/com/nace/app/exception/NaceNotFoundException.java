package com.nace.app.exception;

public class NaceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1234555L;
	
	public NaceNotFoundException(String message) {
		super(message);
    }
	
	public NaceNotFoundException() {
		super();
    }

}
