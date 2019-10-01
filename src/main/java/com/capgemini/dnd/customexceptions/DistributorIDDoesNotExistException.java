package com.capgemini.dnd.customexceptions;

public class DistributorIDDoesNotExistException extends Exception {

	private static final long serialVersionUID = -355970015433248671L;

	public DistributorIDDoesNotExistException() {
	}

	public DistributorIDDoesNotExistException(String message) {
		super(message);
	}
}
