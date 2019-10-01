package com.capgemini.dnd.customexceptions;

public class BackEndException extends Exception {
	private static final long serialVersionUID = -4498041019255092964L;

	public BackEndException(String message) {
		super(message);
	}
}
