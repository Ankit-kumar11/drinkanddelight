package com.capgemini.dnd.customexceptions;

public class WIdDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5558408452472468518L;

	public WIdDoesNotExistException() {
	}

	public WIdDoesNotExistException(String message) {
		super(message);
	}

}
