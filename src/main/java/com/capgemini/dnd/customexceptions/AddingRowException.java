package com.capgemini.dnd.customexceptions;

import java.sql.SQLException;

public class AddingRowException extends SQLException {
	private static final long serialVersionUID = -3374854250145709669L;

	public AddingRowException(String s) {
		super(s);
	}
}
