package com.capgemini.dnd.customexceptions;

public class EmptyInputException extends Exception {
	private static final long serialVersionUID = 3029052430323443363L;

	public EmptyInputException(String message) {
		super(message);
	}
}
