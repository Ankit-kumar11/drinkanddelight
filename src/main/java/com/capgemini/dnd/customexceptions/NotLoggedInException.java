package com.capgemini.dnd.customexceptions;

public class NotLoggedInException extends Exception {
	private static final long serialVersionUID = 6677211328283405215L;

	public NotLoggedInException(String message) {
		super(message);
	}

}
