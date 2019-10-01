package com.capgemini.dnd.customexceptions;

public class UnregisteredEmployeeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1852265435902429720L;

	public UnregisteredEmployeeException(String message) {
		super(message);
	}
}
