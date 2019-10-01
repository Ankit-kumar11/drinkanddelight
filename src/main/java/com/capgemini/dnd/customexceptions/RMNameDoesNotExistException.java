package com.capgemini.dnd.customexceptions;

public class RMNameDoesNotExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9100811868683285755L;

	public RMNameDoesNotExistException() {
	}

	public RMNameDoesNotExistException(String message) {
		super(message);
	}
}
