package com.capgemini.dnd.customexceptions;

public class WrongSecurityAnswerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6003126059373687675L;

	public WrongSecurityAnswerException(String message) {
		super(message);
	}
}
