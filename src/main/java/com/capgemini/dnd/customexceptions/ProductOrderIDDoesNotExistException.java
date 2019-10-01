package com.capgemini.dnd.customexceptions;

public class ProductOrderIDDoesNotExistException extends Exception {

	private static final long serialVersionUID = 9082771261037594457L;

	public ProductOrderIDDoesNotExistException() {
	}

	public ProductOrderIDDoesNotExistException(String message) {
		super(message);
	}

}
