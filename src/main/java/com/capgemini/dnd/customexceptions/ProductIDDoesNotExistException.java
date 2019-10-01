package com.capgemini.dnd.customexceptions;

public class ProductIDDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1334375959046035509L;

	public ProductIDDoesNotExistException() {
	}

	public ProductIDDoesNotExistException(String message) {
		super(message);
	}

}
