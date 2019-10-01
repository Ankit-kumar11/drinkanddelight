package com.capgemini.dnd.service;

import java.util.Scanner;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.NotLoggedInException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.RowNotAddedException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dto.Employee;

public interface EmployeeService {
	public boolean register(Employee employee) throws RowNotAddedException, BackEndException;
	
	public boolean login(Employee employee) throws UnregisteredEmployeeException, WrongPasswordException, BackEndException;
	
	public boolean logout(Employee employee) throws NotLoggedInException, BackEndException;
	
	public boolean changePassword(Employee employee, Scanner scanner) throws UnregisteredEmployeeException, WrongSecurityAnswerException, PasswordException, BackEndException;

	public void logoutAllUsersOnExit() throws BackEndException;
}
