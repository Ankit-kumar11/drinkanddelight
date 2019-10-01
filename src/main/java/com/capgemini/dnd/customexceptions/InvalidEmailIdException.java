package com.capgemini.dnd.customexceptions;

public class InvalidEmailIdException extends Exception {
	
	private static final long serialVersionUID = -2193751761039566293L;

	public InvalidEmailIdException(String message) {
		super(message);
	}
}
