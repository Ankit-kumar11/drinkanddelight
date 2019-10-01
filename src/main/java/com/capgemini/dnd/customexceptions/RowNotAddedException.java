package com.capgemini.dnd.customexceptions;

import java.sql.SQLException;

public class RowNotAddedException extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3374854250145709669L;

	public RowNotAddedException(String s) 
    {  
        super(s); 
    } 
}
