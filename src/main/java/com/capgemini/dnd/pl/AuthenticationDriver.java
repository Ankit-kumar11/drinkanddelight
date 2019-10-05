package com.capgemini.dnd.pl;

import java.io.File;
import java.sql.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.dnd.customexceptions.EmptyInputException;
import com.capgemini.dnd.customexceptions.InvalidEmailIdException;
import com.capgemini.dnd.customexceptions.InvalidGenderException;
import com.capgemini.dnd.customexceptions.PhoneNoException;
import com.capgemini.dnd.dto.Employee;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.service.EmployeeServiceImpl;
import com.capgemini.dnd.util.InputValidator;
import com.capgemini.dnd.util.Log4JManager;

public class AuthenticationDriver {
	static Logger logger=Logger.getRootLogger();
	public AuthenticationDriver() {
		System.out.println("PWD = " + new File(".").getAbsolutePath());
		//Log4JManager.initProps();
	}
	public static void viewMenu() {
		System.out.println("Drink and Delight Login/Register Page ----------------------");
		System.out.println("Press the number listed against your desired option: ");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Change Password");
		System.out.println("0. Exit");
		System.out.println("Please enter your choice : ");
	}
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeServiceImpl();
		String DIGITS_ONLY_REGEX = "[0-9]+";
        while (true) {
            AuthenticationDriver.viewMenu();
            int choice = 0;
            String stringChoice;
            boolean validChoiceType = false;
            while (!validChoiceType) {
                stringChoice=scanner.nextLine();
                if(stringChoice.matches(DIGITS_ONLY_REGEX)) {
                    validChoiceType = true;
                    choice=Integer.parseInt(stringChoice);
                }
                else {
                    System.err.println("Type of choice entered is wrong!!! Enter a valid number!!!");
                    AuthenticationDriver.viewMenu();
                }
            }
            if(choice == 0) {
				employeeService.logoutAllUsersOnExit();
				System.out.println("Thank You for using our application");
				break;
			}else if (choice == 1) {
				Employee employee = new Employee();
				
				String employeeName;
				boolean filledInput=false;
				while(!filledInput) {
					try {
						System.out.println("Type employee name : ");
						employeeName=scanner.nextLine();
						InputValidator.notEmptyValidator(employeeName);
						filledInput=true;
						employee.setEmpName(employeeName);
					}catch(EmptyInputException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String employeeDesignation;
				filledInput=false;
				while(!filledInput) {
					try {
						System.out.println("Enter Designation : ");
						employeeDesignation=scanner.nextLine();
						InputValidator.notEmptyValidator(employeeDesignation);
						filledInput=true;
						employee.setDesignation(employeeDesignation);
					}catch(EmptyInputException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String gender;
				boolean validGender=false;
				while(!validGender) {
					try {
						System.out.println("Enter Gender : ");
						gender=scanner.nextLine();
						InputValidator.genderValidator(gender);
						validGender=true;
						employee.setGender(gender);
					}catch(InvalidGenderException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				boolean correctDateFormat=false;
				while(!correctDateFormat) {
					try {
						System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
						employee.setDob(Date.valueOf(scanner.nextLine()));
						correctDateFormat=true;
					}catch(IllegalArgumentException exception) {
						System.err.println("Invalid format of date!!!");
					}
				}
				
				String emailId;
				boolean validEmailId=false;
				while(!validEmailId) {
					try {
						System.out.println("Enter Email id : ");
						emailId=scanner.nextLine();
						InputValidator.emailIdValidator(emailId);
						validEmailId=true;
						employee.setEmailId(emailId);
					}catch(InvalidEmailIdException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String phoneNo;
				boolean validPhoneNo=false;
				while(!validPhoneNo) {
					try {
						System.out.println("Enter Phone no : ");
						phoneNo=scanner.nextLine();
						InputValidator.phoneNoValidator(phoneNo);
						validPhoneNo=true;
						employee.setPhoneNo(phoneNo);
					}catch(PhoneNoException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String userName;
				filledInput=false;
				while(!filledInput) {
					try {
						System.out.println("Enter Username : ");
						userName=scanner.nextLine();
						InputValidator.notEmptyValidator(userName);
						filledInput=true;
						employee.setUsername(userName);
					}catch(EmptyInputException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String password;
				filledInput=false;
				while(!filledInput) {
					try {
						System.out.println("Enter Password : ");
						password=scanner.nextLine();
						InputValidator.notEmptyValidator(password);
						filledInput=true;
						employee.setPassword(password);
					}catch(EmptyInputException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String securityQuestion;
				filledInput=false;
				while(!filledInput) {
					try {
						System.out.println("Enter Security Question : ");
						securityQuestion=scanner.nextLine();
						InputValidator.notEmptyValidator(securityQuestion);
						filledInput=true;
						employee.setSecurityQuestion(securityQuestion);
					}catch(EmptyInputException exception) {
						System.err.println(exception.getMessage());
					}
				}
				
				String securityAnswer;
				filledInput=false;
				while(!filledInput) {
					try {
						System.out.println("Enter Security Answer : ");
						securityAnswer=scanner.nextLine();
						InputValidator.notEmptyValidator(securityAnswer);
						filledInput=true;
						employee.setSecurityAnswer(securityAnswer);
					}catch(EmptyInputException exception) {
						System.err.println(exception.getMessage());
					}
				}
								
				try {
					if(employeeService.register(employee))
						System.out.println("Employee registered succesfully.");
				}catch(Exception exception) {
					System.err.println(exception.getMessage());
					continue;
				}
			} else if (choice == 2) {
				System.out.println("Enter Username : ");
				String userName = scanner.nextLine();
				System.out.println("Enter password : ");
				String password = scanner.nextLine();
				Employee employee = new Employee();
				employee.setUsername(userName);
				employee.setPassword(password);
				try{
					if(employeeService.login(employee)) {
						MainMenuDriver.displayScreenAfterLogin(employee);
						try {
							if(employeeService.logout(employee))
								System.out.println("Logged out succesfully.");
						}catch(Exception exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}
				}catch(Exception exception) {
					System.err.println(exception.getMessage());
					continue;
				}	
			} else if (choice == 3) {
				System.out.println("Enter Username");
				Employee employee = new Employee();
				String userName = scanner.nextLine();
				employee.setUsername(userName);
				try {
					if(employeeService.changePassword(employee,scanner))
						System.out.println("Password changed successfully.");
				}catch(Exception exception) {
					System.err.println(exception.getMessage());
					continue;
				}
			}  else {
				System.err.println("Wrong choice!!! Try again!!!");
			}
		}
		scanner.close();
	}

}