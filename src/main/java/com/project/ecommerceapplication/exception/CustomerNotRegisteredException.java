package com.project.ecommerceapplication.exception;

public class CustomerNotRegisteredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public CustomerNotRegisteredException(String message) {
        super(message);
    }
}
