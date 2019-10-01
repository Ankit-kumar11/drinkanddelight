package com.capgemini.dnd.pl;

import java.util.Scanner;

import com.capgemini.dnd.dto.Employee;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.service.EmployeeServiceImpl;

public class MainMenuDriver {

	public static void displayScreenAfterLogin(Employee employee) throws Exception {
		Scanner scanner = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeServiceImpl();
		System.out.println("Welcome " + employee.getUsername() + "!!!");
		String DIGITS_ONLY_REGEX = "[0-9]+";
		while (true) {
			System.out.println("Press the number listed against your desired option: ");
			System.out.println("1 Raw Materials");
			System.out.println("2 Products");

			System.out.println("3 Logout");
			System.out.println("0 Exit");
			System.out.print("Please enter your choice: ");
			int choice = 0;
			String stringChoice;
			boolean validChoiceType = false;
			while (!validChoiceType) {
				stringChoice = scanner.nextLine();
				if (stringChoice.matches(DIGITS_ONLY_REGEX)) {
					validChoiceType = true;
					choice = Integer.parseInt(stringChoice);
				} else {
					System.out.println("Type of choice entered is wrong!!! Enter a valid number!!!");
					System.out.println("Press the number listed against your desired option: ");
					System.out.println("1 Raw Materials");
					System.out.println("2 Products");
					System.out.println("3 Logout");
					System.out.println("0 Exit");
					System.out.print("Please enter your choice: ");
				}
			}

			if (choice == 1) {
				SecondaryMenuDriver.displayRawMaterialMenu();
			} else if (choice == 2) {
				SecondaryMenuDriver.displayProductMenu();
			} else if (choice == 3) {
				break;
			} else if (choice == 0) {
				employeeService.logoutAllUsersOnExit();
				System.out.println("Thank You for using our Application");
				System.exit(0);
			} else {
				System.out.println("Invalid Choice!!! Try again!!!");

			}
		}
	}



}