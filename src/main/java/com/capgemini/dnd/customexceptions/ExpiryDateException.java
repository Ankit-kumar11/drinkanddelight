package com.capgemini.dnd.customexceptions;

public class ExpiryDateException extends Exception {

	private static final long serialVersionUID = -7922965129298450621L;


	public ExpiryDateException() {
	}

	public ExpiryDateException(String message) {
		super(message);
	}

}
