package com.nace.app.exception;

public class NaceLoadAllException extends RuntimeException {

	private static final long serialVersionUID = 1234545L;
	
	public NaceLoadAllException(String message) {
		super(message);
    }
	
	public NaceLoadAllException() {
		super();
    }

}
