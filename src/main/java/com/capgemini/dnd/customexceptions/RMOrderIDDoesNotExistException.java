package com.capgemini.dnd.customexceptions;

public class RMOrderIDDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4737565497574590483L;

	public RMOrderIDDoesNotExistException() {
	}

	public RMOrderIDDoesNotExistException(String message) {
		super(message);
	}

}
