package com.capgemini.dnd.customexceptions;

public class InvalidGenderException extends Exception {
	private static final long serialVersionUID = 7621892872341029140L;

	public InvalidGenderException(String message) {
		super(message);
	}
}
