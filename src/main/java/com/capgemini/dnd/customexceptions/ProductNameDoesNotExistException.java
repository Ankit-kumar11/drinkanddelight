package com.capgemini.dnd.customexceptions;

public class ProductNameDoesNotExistException extends Exception {

	private static final long serialVersionUID = 5846788608948880268L;

	public ProductNameDoesNotExistException() {
	}

	public ProductNameDoesNotExistException(String message) {
		super(message);
	}

}
