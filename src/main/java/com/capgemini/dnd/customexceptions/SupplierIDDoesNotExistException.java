package com.capgemini.dnd.customexceptions;

public class SupplierIDDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5359211717507039610L;

	public SupplierIDDoesNotExistException() {
	}

	public SupplierIDDoesNotExistException(String message) {
		super(message);
	}
}
