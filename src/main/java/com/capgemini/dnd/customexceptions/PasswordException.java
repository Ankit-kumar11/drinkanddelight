package com.capgemini.dnd.customexceptions;

public class PasswordException extends Exception {	
	private static final long serialVersionUID = -6376771602396460160L;

	public PasswordException(String message) {
		super(message);
	}
}
