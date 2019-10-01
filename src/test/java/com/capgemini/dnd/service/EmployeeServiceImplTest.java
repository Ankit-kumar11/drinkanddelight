package com.capgemini.dnd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.text.ParseException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.NotLoggedInException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.RowNotAddedException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dto.Employee;

class EmployeeServiceImplTest {
	// -------------------------------------------------------------------------------------------------------------------------------

	@Test
	void registerTest1() throws ParseException, RowNotAddedException, BackEndException { // Successful registration
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee("Ankit Madha", "Manager", "Male", Date.valueOf("1997-03-10"),
				"ankit420@gmail.com", "1100076800", "anky11", "passworrd42", "passworrd42", false,
				"What is your nickname?", "Anky");

		assertEquals(true, es.register(employee));

	}
	
	

	@Test
	void registerTest2() throws RowNotAddedException, BackEndException { // With similar/wrong data
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee("Ankit Madha", "Manager", "Male", Date.valueOf("1997-03-10"),
				"ankit420@gmail.com", "1100076800", "anky11", "passworrd42", "passworrd42", false,
				"What is your nickname?", "Anky");

		// assertEquals(false, es.register(employee));

		Throwable exception = assertThrows(RowNotAddedException.class, () -> {
			es.register(employee);
		});
		// assertEquals("You are not a registered user!!!", exception.getMessage());

	}

	// --------------------------------------------------------------------------------------------------------------------------------

	@Test
	void loginTest1() throws ParseException, UnregisteredEmployeeException, WrongPasswordException, BackEndException { // Successful
																														// login
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("ankit40");
		employee.setPassword("ankit501");

		assertEquals(true, es.login(employee));
	}

	@Test
	void loginTest2() throws ParseException { // Incorrect password for login
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("adas38");
		employee.setPassword("chameleonn");

		assertThrows(WrongPasswordException.class, () -> {
			es.login(employee);
		});
	}

	@Test
	void loginTest3() throws ParseException { // Unregistered users can't login
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("ashmita40");
		employee.setPassword("pass123");

		assertThrows(UnregisteredEmployeeException.class, () -> {
			es.login(employee);
		});
	}

	// ------------------------------------------------------------------------------------------------------------------------------

	@Test
	void forgotPasswordTest1()
			throws UnregisteredEmployeeException, WrongSecurityAnswerException, PasswordException, BackEndException { // password
		// change
		// successful
		System.out.println("---------------------Successful change---------------------");

		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("ankit40");

		Scanner scanner = new Scanner(System.in);
		es.changePassword(employee, scanner);

	}

	@Test
	void forgotPasswordTest2() { // password is same as before
		System.out.println("---------------Password is same as before-----------------");

		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("ankit40");

		Scanner scanner = new Scanner(System.in);
		Throwable exception = assertThrows(PasswordException.class, () -> {
			es.changePassword(employee, scanner);
		});
		assertEquals("New password matches with old password!!!", exception.getMessage());
	}

	@Test
	void forgotPasswordTest3() { // passwords do not match
		System.out.println("------------------Passwords do not match------------------");

		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("ankit40");

		Scanner scanner = new Scanner(System.in);
		Throwable exception = assertThrows(PasswordException.class, () -> {
			es.changePassword(employee, scanner);
		});
		assertEquals("Passwords do not match!!!", exception.getMessage());
	}

	@Test
	void forgotPasswordTest4() { // wrong answer given to security question
		System.out.println("----------Wrong answer given to security question-----------");

		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("ankit40");

		Scanner scanner = new Scanner(System.in);
		Throwable exception = assertThrows(WrongSecurityAnswerException.class, () -> {
			es.changePassword(employee, scanner);
		});
		assertEquals("Answer to security question is wrong!!!", exception.getMessage());
	}

	@Test
	void forgotPasswordTest5() throws ParseException { // username does not exist while changing the password
		System.out.println("-------Username does not exist while changing the password------");

		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("abcdef");

		Scanner scanner = new Scanner(System.in);
		Throwable exception = assertThrows(UnregisteredEmployeeException.class, () -> {
			es.changePassword(employee, scanner);
		});
		assertEquals("You are not a registered user!!!", exception.getMessage());
	}

	// -------------------------------------------------------------------------------------------------------------------------------

	@Test
	void logoutTest1()
			throws UnregisteredEmployeeException, WrongPasswordException, NotLoggedInException, BackEndException { // successful
		// logout
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		employee.setUsername("akash420");
		employee.setPassword("adkeaesph");

		es.login(employee);
		assertEquals(es.logout(employee), true);
	}

	@Test
	void logoutTest2() throws ParseException { // logout only possible for those who login.
		EmployeeService es = new EmployeeServiceImpl();
		Employee employee = new Employee();

		Throwable exception = assertThrows(NotLoggedInException.class, () -> {
			es.logout(employee);
		});
		assertEquals("You are not logged in!!!", exception.getMessage());
	}

	// --------------------------------------------------------------------------------------------------------------------------------

}