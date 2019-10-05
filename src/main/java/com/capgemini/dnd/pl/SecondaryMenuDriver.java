package com.capgemini.dnd.pl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.capgemini.dnd.customexceptions.DistributorIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.SupplierIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.service.RawMaterialServiceImpl;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.service.EmployeeServiceImpl;
import com.capgemini.dnd.service.ProductService;
import com.capgemini.dnd.service.ProductServiceImpl;
import com.capgemini.dnd.service.RawMaterialService;

public class SecondaryMenuDriver {

	public static void displayRawMaterialMenu() throws Exception {
		Scanner scanner = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeServiceImpl();
		
		String DIGITS_ONLY_REGEX = "[0-9]+";
		while(true) {
		System.out.println("-----------RAW MATERIALS MENU---------- ");
		System.out.println("What would you like to do: ");
		System.out.println("1. Display all orders ");
		System.out.println("2. Place an Order");
		System.out.println("3. Update raw material order");
		System.out.println("4. Update stock of raw materials");
		System.out.println("5. Set process Date for Raw Material order");
		System.out.println("6. Track Raw material Order");
		System.out.println("7. Go to main menu");
		System.out.println("0. Exit");
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
				System.out.println("-----------RAW MATERIALS MENU---------- ");
				System.out.println("What would you like to do: ");
				System.out.println("1. Display all orders ");
				System.out.println("2. Place an Order");
				System.out.println("3. Update raw material order");
				System.out.println("4. Update stock of raw materials");
				System.out.println("5. Set process Date for Raw Material order");
				System.out.println("6. Track Raw material Order");
				System.out.println("7. Go to main menu");
				System.out.println("0. Exit");
			}
		}

		if (choice == 1) {
			
			TertiaryMenuDriver.displayRawMaterialOrders();
		} else if (choice == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			boolean correct = false;

			String name = "";
			String rmId = "";
			String supplierId = "";
			double quantityValue = 0;
			String quantityUnit = "";
			Date today = new Date();
			String strDateOfDelivery = "";
			Date dateOfDelivery = null;
			double pricePerUnit = 0;
			String warehouseId = "";
			RawMaterialService rmsi_obj = new RawMaterialServiceImpl();
			do {
				System.out.print("Enter name:");
				name = scanner.next();
				try {
					rmsi_obj.doesRMNameExist(name);
				} catch (RMNameDoesNotExistException e) {
					System.err.println(e.getMessage());
					continue;
				}
				System.out.print("Enter Raw Material ID:");
				rmId = scanner.next();
				try {
					rmsi_obj.doesRawMaterialIdExist(rmId, name);
				} catch (RMIDDoesNotExistException e) {
					System.err.println(e.getMessage());
					continue;
				}
				System.out.print("Enter Supplier ID:");

				supplierId = scanner.next();
				try {
					rmsi_obj.doesSupplierIdExist(supplierId);
				} catch (SupplierIDDoesNotExistException e) {
					System.err.println(e.getMessage());
					continue;
				}
				System.out.print("Enter quantity:");
				try {
					quantityValue = scanner.nextDouble();
				} catch (InputMismatchException e) {
					System.err.println("Enter value in decimal only");
					continue;
				}
				System.out.print("Enter quantity unit:");
				quantityUnit = scanner.next();
				today = new Date();
				System.out.print("Enter expected date of delivery (in yyyy-mm-dd):");
				strDateOfDelivery = scanner.next();
				dateOfDelivery = null;
				try {
					dateOfDelivery = sdf.parse(strDateOfDelivery);
				} catch (ParseException e) {
					System.err.println("Please enter in given format");
					continue;
				}
				System.out.print("Enter Price per Unit:");
				try {
					pricePerUnit = scanner.nextDouble();
				} catch (InputMismatchException e) {
					System.err.println("Enter value in decimal only");
					continue;
				}

				System.out.print("Enter Warehouse ID:");
				warehouseId = scanner.next();
				try {
					rmsi_obj.doesWIdExist(warehouseId);
				} catch (WIdDoesNotExistException e) {
					System.err.println(e.getMessage());
					continue;
				}
				correct = true;
			} while (!correct);

			RawMaterialOrder rmo = new RawMaterialOrder(name, rmId, supplierId, quantityValue, quantityUnit, today,
					dateOfDelivery, pricePerUnit, warehouseId);
			try {
				System.out.println(rmsi_obj.placeRawMaterialOrder(rmo));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			
		}

		else if (choice == 3) {
			TertiaryMenuDriver.updateRawMaterialOrder();
		}

		else if (choice == 4) {
			TertiaryMenuDriver.updateRawMaterialStock();

		}

		else if (choice == 5) {
			TertiaryMenuDriver.setRMProcessDate();
		}

		else if (choice == 6) {
			TertiaryMenuDriver.trackRMOrder();
		}

		else if (choice == 7) {
			break;
		} else if (choice == 0) {
			employeeService.logoutAllUsersOnExit();
			System.out.println("Thank You for using our application");
			System.exit(0);
		} else {
			System.err.println("Invalid Choice!!! Try again!!!");
		}
		}

	}

	public static void displayProductMenu() throws Exception {
		Scanner scanner = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeServiceImpl();
		String DIGITS_ONLY_REGEX = "[0-9]+";
		while (true) {
			System.out.println("-----------PRODUCT MENU---------- ");
			System.out.println("What would you like to do: ");
			System.out.println("1. Display all orders ");
			System.out.println("2. Place an Order");
			System.out.println("3. Update Product delivery status");
			System.out.println("4. Update stock of products");
			System.out.println("5. Set Exit Date for Product order");
			System.out.println("6. Track Product order");
			System.out.println("7. Go to main menu");
			System.out.println("0. Exit");
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
					System.out.println("-----------PRODUCT MENU---------- ");
					System.out.println("What would you like to do: ");
					System.out.println("1. Display all orders ");
					System.out.println("2. Place an Order");
					System.out.println("3. Update Product delivery status");
					System.out.println("4. Update stock of products");
					System.out.println("5. Set Exit Date for Product order");
					System.out.println("6. Track Product order");
					System.out.println("7. Go to main menu");
					System.out.println("0. Exit");
				}
			}

			if (choice == 1) {
				TertiaryMenuDriver.displayProductOrders();
			} else if (choice == 2) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				boolean correct = false;
				String name = "";
				String pid = "";
				String distributorId = "";
				double quantityValue = 0;
				String quantityUnit = "";
				Date today = new Date();
				String strDateOfDelivery = "";
				Date dateOfDelivery = null;
				String warehouseId = "";
				double pricePerUnit = 0;

				ProductService psi_obj = new ProductServiceImpl();
				do {
					System.out.print("Enter name:");
					name = scanner.next();
					try {
						psi_obj.doesProductNameExist(name);
					} catch (ProductNameDoesNotExistException e) {
						System.err.println(e.getMessage());
						continue;
					}
					System.out.print("Enter Product ID:");
					pid = scanner.next();
					try {
						psi_obj.doesProductIdExist(pid, name);
					} catch (ProductIDDoesNotExistException e) {
						System.err.println(e.getMessage());
						continue;
					}
					System.out.print("Enter Distributor ID:");
					distributorId = scanner.next();
					try {
						psi_obj.doesDistributorIdExist(distributorId);
					} catch (DistributorIDDoesNotExistException e) {
						System.err.println(e.getMessage());
						continue;
					}

					System.out.print("Enter quantity:");
					try {
						quantityValue = scanner.nextDouble();
					} catch (InputMismatchException e) {
						System.err.println("Enter value in decimal only");
						continue;
					}
					System.out.print("Enter quantity unit:");
					quantityUnit = scanner.next();
					System.out.print("Enter expected date of delivery (in yyyy-mm-dd):");
					strDateOfDelivery = scanner.next();
					dateOfDelivery = null;
					try {
						dateOfDelivery = sdf.parse(strDateOfDelivery);
					} catch (ParseException e) {
						System.err.println("Please enter in given format");
						continue;
					}
					System.out.print("Enter Price per Unit:");
					try {
						pricePerUnit = scanner.nextDouble();
					} catch (InputMismatchException e) {
						System.err.println("Enter value in decimal only");
						continue;
					}
					System.out.print("Enter Warehouse ID:");
					warehouseId = scanner.next();
					try {
						psi_obj.doesWIdExist(warehouseId);
					} catch (WIdDoesNotExistException e) {
						System.err.println(e.getMessage());
						continue;
					}
					correct = true;
				} while (!correct);
				ProductOrder po = new ProductOrder(name, pid, distributorId, quantityValue, quantityUnit, today,
						dateOfDelivery, pricePerUnit, warehouseId);

				try {
					System.out.println(psi_obj.placeProductOrder(po));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			} else if (choice == 3) {
				TertiaryMenuDriver.updateProductOrder();
			}

			else if (choice == 4) {
				TertiaryMenuDriver.updateProductStock();
			} else if (choice == 5) {
				TertiaryMenuDriver.setExitDate();
			}

			else if (choice == 6) {
				TertiaryMenuDriver.trackProductOrder();
			} else if (choice == 7) {
				break;
			} else if (choice == 0) {
				employeeService.logoutAllUsersOnExit();
				System.out.println("Thank You for using our application");
				System.exit(0);
			} else {
				System.out.println("Invalid Choice!!! Try again!!!");
			}

		}
	}
}
