package com.capgemini.dnd.customexceptions;

public class WrongPasswordException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4903499573764711693L;

	public WrongPasswordException(String message) {
		super(message);
		
	}
}
