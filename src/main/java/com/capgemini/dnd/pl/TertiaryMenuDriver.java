package com.capgemini.dnd.pl;

import java.sql.SQLException;

//import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.SupplierIDDoesNotExistException;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.dto.RawMaterialStock;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.service.EmployeeServiceImpl;
import com.capgemini.dnd.service.ProductService;
import com.capgemini.dnd.service.ProductServiceImpl;
import com.capgemini.dnd.service.RawMaterialService;
import com.capgemini.dnd.service.RawMaterialServiceImpl;
import com.capgemini.dnd.validator.InitializationValidator;
//import com.sun.media.jfxmedia.logging.Logger;
import org.apache.log4j.Logger;

public class TertiaryMenuDriver {

	static Logger logger = Logger.getRootLogger();

	public TertiaryMenuDriver() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	public static void displayRawMaterialOrders() throws Exception {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeService employeeService = new EmployeeServiceImpl();
		RawMaterialService rawMaterialServiceObject = new RawMaterialServiceImpl();
		String DIGITS_ONLY_REGEX = "[0-9]+";
		while (true) {
			System.out.println("-----------DISPLAY RAW MATERIALS MENU---------- ");
			System.out.println("Choose one of the following: ");
			System.out.println("1. All orders ");
			System.out.println("2. Pending orders");
			System.out.println("3. Cancelled orders");
			System.out.println("4. Received orders");
			System.out.println("5. display orders between two dates");
			System.out.println("6. display orders from a supplier");
			System.out.println("7. Go to secondary menu");
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
					System.err.println("Type of choice entered is wrong!!! Enter a valid number!!!");
					System.out.println("-----------DISPLAY RAW MATERIALS MENU---------- ");
					System.out.println("Choose one of the following: ");
					System.out.println("1. All orders ");
					System.out.println("2. Pending orders");
					System.out.println("3. Cancelled orders");
					System.out.println("4. Received orders");
					System.out.println("5. display orders between two dates");
					System.out.println("6. display orders from a supplier");
					System.out.println("7. Go to secondary menu");
					System.out.println("0. Exit");
				}
			}

			if (choice == 1) {
				rawMaterialServiceObject.displayRawMaterialOrderDetails();
			}

			else if (choice == 2) {

				rawMaterialServiceObject.displayPendingRawMaterialOrderDetails();
			} else if (choice == 3) {

				rawMaterialServiceObject.displayCancelledRawMaterialOrderDetails();

			} else if (choice == 4) {

				rawMaterialServiceObject.displayReceivedRawMaterialOrderDetails();

			} else if (choice == 5) {
				Date dt1 = null, dt2 = null;
				System.out.println("enter date 1");
				String s1 = scanner.next();
				try {
					dt1 = sdf.parse(s1);
				} catch (ParseException e) {
					System.err.println(e.getMessage());
				}

				String s2 = scanner.next();
				;
				try {
					dt2 = sdf.parse(s2);
				} catch (ParseException e) {
					System.err.println(e.getMessage());
				}

				rawMaterialServiceObject.displayRawmaterialOrdersbetweenDetails(dt1, dt2);
			} else if (choice == 6) {
				while (true) {
					System.out.print("enter supplier id: ");
					String supid = scanner.next();

					try {
						rawMaterialServiceObject.displayOrdersFromSupplier(supid);
						break;
					} catch (SupplierIDDoesNotExistException e) {
						System.err.println(e.getMessage());
					}

				}
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

	public static void displayProductOrders() throws Exception {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EmployeeService employeeService = new EmployeeServiceImpl();
		ProductService productServiceObject = new ProductServiceImpl();
		String DIGITS_ONLY_REGEX = "[0-9]+";
        while (true) {
        	System.out.println("-----------DISPLAY PRODUCTS MENU---------- ");
			System.out.println("Choose one of the following: ");
			System.out.println("1. All orders ");
			System.out.println("2. Dispatched orders");
			System.out.println("3. Pending orders");
			System.out.println("4. Received orders");
			System.out.println("5. Cancelled orders");
			System.out.println("6. display orders between two dates");
			System.out.println("7. display orders from a distributor");
			System.out.println("8. Go to secondary menu");
			System.out.println("0. Exit");
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
                    System.out.println("-----------DISPLAY PRODUCTS MENU---------- ");
        			System.out.println("Choose one of the following: ");
        			System.out.println("1. All orders ");
        			System.out.println("2. Dispatched orders");
        			System.out.println("3. Pending orders");
        			System.out.println("4. Received orders");
        			System.out.println("5. Cancelled orders");
        			System.out.println("6. display orders between two dates");
        			System.out.println("7. display orders from a distributor");
        			System.out.println("8. Go to secondary menu");
        			System.out.println("0. Exit");
                }
            }
			if (choice == 1) {

				productServiceObject.displayProductOrderDetails();
			} else if (choice == 2) {

				productServiceObject.displayDispatchedProductOrderDetails();

			} else if (choice == 3) {

				productServiceObject.displayPendingProductOrderDetails();
			} else if (choice == 4) {

				productServiceObject.displayReceivedProductOrderDetails();

			} else if (choice == 5) {
				productServiceObject.displayCancelledProductOrderDetails();
			} else if (choice == 6) {
				Date dt1 = null, dt2 = null;
				System.out.println("enter date 1");
				String s1 = scanner.next();
				try {
					dt1 = sdf.parse(s1);
				} catch (ParseException e) {
					System.err.println(e.getMessage());
				}

				String s2 = scanner.next();
				;
				try {
					dt2 = sdf.parse(s2);
				} catch (ParseException e) {
					System.err.println(e.getMessage());
				}

				productServiceObject.displayProductOrderbetweenDetails(dt1, dt2);
			} else if (choice == 7) {
				System.out.println("enter distributor id");
				String distId = scanner.next();
				productServiceObject.displayOrdersFromDistributor(distId);

			} else if (choice == 8) {

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

	/*
	 * Update Raw Material Delivery Delivery Status
	 */
	public static void updateRawMaterialOrder() throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("-----------UPDATE RAW MATERIAL ORDER---------- ");
		while (true) {
			System.out.println("Enter Order ID ");
			String orderId = scanner.next();
			String newdeliverystatus = " ";
			while (true) {
				System.out.println("Enter new Delivery Status");
				newdeliverystatus = scanner.next();
				if (InitializationValidator.deliveryRawMaterialOrderStatusValidator(newdeliverystatus))
					break;
			}
			RawMaterialService rawMaterialServiceObject = new RawMaterialServiceImpl();
			try {
				String message = rawMaterialServiceObject.updateStatusRawMaterialOrder(orderId, newdeliverystatus);
				System.out.println(message);
				break;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				continue;
			}
		}
	}

	public static void updateProductOrder() throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("-----------UPDATE PRODUCT DELIVERY STATUS---------- ");
		while (true) {
			System.out.println("Enter Order ID ");
			String OrderId = scanner.next();
			String newdeliverystatus = "";
			while (true) {
				System.out.println("Enter new Delivery Status");
				newdeliverystatus = scanner.next();
				if (InitializationValidator.deliveryProductOrderStatusValidator(newdeliverystatus))
					break;
			}

			ProductService productServiceObject = new ProductServiceImpl();
			try {
				String message = productServiceObject.updateStatusProductOrder(OrderId, newdeliverystatus);
				System.out.println(message);
				break;
			} catch (Exception e) {
				System.err.println(e.getMessage());
				continue;

			}

		}

	}

	public static void updateRawMaterialStock() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialService rawMaterialServiceObject = new RawMaterialServiceImpl();

		while (true) {
			System.out.println("Enter order ID: ");
			String id = scanner.next();

			boolean doesRMOrderIDexist = false;

			try {
				doesRMOrderIDexist = rawMaterialServiceObject.doesRawMaterialOrderIdExist(id);
			} catch (RMOrderIDDoesNotExistException | ConnectionException | SQLException e) {
				logger.error(e.getMessage());
				
			}

			Date manufacturing_date = null;
			Date expiry_date = null;
			String manufacturingDate;
			String expiryDate;
			String qaStatus;
			if (doesRMOrderIDexist == true) {
				System.out.println("Enter manufacturing date in yyyy-mm-dd: ");
				manufacturingDate = scanner.next();
				try {
					manufacturing_date = sdf.parse(manufacturingDate);
					rawMaterialServiceObject.validateManufacturingDate(manufacturing_date);
				} catch (ParseException e) {
					logger.error(e.getMessage());
					System.err.println("Please enter date in valid format");
					continue;
				} catch (ManufacturingDateException e) {
					logger.error(e.getMessage());
					System.err.println(e.getMessage());
					continue;
				}

				System.out.println("Enter expiry date in yyyy-mm-dd: ");
				expiryDate = scanner.next();
				try {
					expiry_date = sdf.parse(expiryDate);
					rawMaterialServiceObject.validateExpiryDate(manufacturing_date, expiry_date);
				} catch (ParseException e) {
					System.err.println("Please enter date in valid format");
					continue;
				}

				catch (ExpiryDateException e) {
					System.err.println("You cant enter expiry date before manufacturing date");
					logger.error(e.getMessage());
					continue;
				}

				System.out.println("Enter the QA status: ");
				qaStatus = scanner.next();
				if (qaStatus.equalsIgnoreCase("passed") || qaStatus.equalsIgnoreCase("failed")) {
					String message = rawMaterialServiceObject
							.updateRMStock(new RawMaterialStock(id, manufacturing_date, expiry_date, qaStatus));
					System.out.println(message);
					break;
				} else {
					System.err.println("Please enter either PASSED or FAILED");
					continue;
				}

			}

			else {
				System.err.println("Order ID doesnt exist");
				continue;
			}
		}

	}

	public static void setRMProcessDate() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialService rawMaterialServiceObject = new RawMaterialServiceImpl();

		while (true) {
			System.out.println("Enter order ID: ");
			String id = scanner.next();
			String processDate;
			Date process_date;
			boolean rmOrderIdFound = false;
			try {
				rmOrderIdFound = rawMaterialServiceObject.doesRawMaterialOrderIdExist(id);
			} catch (RMOrderIDDoesNotExistException | ConnectionException | SQLException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
				continue;
			}
			if (rmOrderIdFound == true) {
				System.out.println("Please enter the process date in yyyy-mm-dd: ");
				processDate = scanner.next();
				try {
					process_date = sdf.parse(processDate);
					boolean dateCheck = rawMaterialServiceObject
							.processDateCheck(new RawMaterialStock(id, process_date));
					if (dateCheck == true) {
						String message = rawMaterialServiceObject
								.updateProcessDateinStock(new RawMaterialStock(id, process_date));
						System.out.println(message);
						break;
					}

				} catch (ParseException e) {
					System.err.println("Please enter date in valid format");
					continue;

				} catch (ProcessDateException e) {
					logger.error(e.getMessage());
					System.err.println(e.getMessage());
					continue;

				}

			} else {
				System.err.println("Order Id doesnt exist");
				continue;
			}
		}

	}

	public static void trackRMOrder() {
		Scanner scanner = new Scanner(System.in);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialService rawMaterialServiceObject = new RawMaterialServiceImpl();

		while (true) {
			System.out.println("Enter order ID: ");
			String id = scanner.next();
			boolean rmOrderIdFound = false;
			try {
				rmOrderIdFound = rawMaterialServiceObject.doesRawMaterialOrderIdExist(id);
			} catch (RMOrderIDDoesNotExistException | ConnectionException | SQLException e) {

				logger.error(e.getMessage());
				System.err.println(e.getMessage());
			}
			if (rmOrderIdFound == true) {

				String value = rawMaterialServiceObject.trackRawMaterialOrder(new RawMaterialStock(id));
				if (value != null)
					System.out.println(value);
				break;

			}

			else {
				System.err.println("Order ID doesnt exist");
				continue;
			}
		}
	}

	public static void updateProductStock() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductService productServiceObject = new ProductServiceImpl();

		while (true) {

			System.out.println("Enter order ID: ");
			String id = scanner.next();
			boolean rmOrderIdFound = false;
			try {
				rmOrderIdFound = productServiceObject.doesProductOrderIdExist(id);
			} catch (ProductOrderIDDoesNotExistException | ConnectionException | SQLException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
			}

			Date manufacturing_date = null;
			Date expiry_date = null;
			String qaStatus;
			if (rmOrderIdFound == true) {
				System.out.println("Enter manufacturing date in yyyy-mm-dd: ");
				String manufacturingDate = scanner.next();
				try {
					manufacturing_date = sdf.parse(manufacturingDate);
					productServiceObject.validateManufacturingDate(manufacturing_date);
				} catch (ParseException e) {
					logger.info(e.getMessage());
					System.err.println("Please enter date in valid format");
					continue;

				} catch (ManufacturingDateException e) {
					logger.error(e.getMessage());
					System.err.println(e.getMessage());
					continue;

				}

				System.out.println("Enter expiry date in yyyy-mm-dd: ");
				String expiryDate = scanner.next();
				try {
					expiry_date = sdf.parse(expiryDate);
					productServiceObject.validateExpiryDate(manufacturing_date, expiry_date);
				} catch (ParseException e) {
					System.err.println("Please enter date in valid format");
					continue;

				}

				catch (ExpiryDateException e) {
					logger.error(e.getMessage());
					System.err.println("You cant enter expiry date before manufacturing date");
					continue;

				}

				System.out.println("Enter the QA status: ");
				qaStatus = scanner.next();
				if (qaStatus.equalsIgnoreCase("passed") || qaStatus.equalsIgnoreCase("failed")) {
					String message = productServiceObject
							.updateProductStock(new ProductStock(id, manufacturing_date, expiry_date, qaStatus));
					System.out.println(message);
					break;
				}

			}

			else {
				System.err.println("Order ID doesnt exist");
				continue;
			}
		}

	}

	public static void setExitDate() {
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductService productServiceObject = new ProductServiceImpl();
		while (true) {

			System.out.println("Enter order ID: ");
			String id = scanner.next();
			String exitDate;
			Date exit_date;
			boolean rmOrderIdFound = false;
			try {
				rmOrderIdFound = productServiceObject.doesProductOrderIdExist(id);
			} catch (ProductOrderIDDoesNotExistException | ConnectionException | SQLException e) {

				logger.error(e.getMessage());
				System.err.println(e.getMessage());
				;
			}
			if (rmOrderIdFound == true) {
				System.out.println("Please enter the exit date in yyyy-mm-dd: ");
				exitDate = scanner.next();
				try {
					exit_date = sdf.parse(exitDate);
					boolean dateCheck = productServiceObject.exitDateCheck(new ProductStock(id, exit_date));
					if (dateCheck == true) {
						String message = productServiceObject.updateExitDateinStock(new ProductStock(id, exit_date));
						System.out.println(message);
						break;
					}

				} catch (ParseException e) {
					logger.error(e.getMessage());
					System.err.println("Please enter date in valid format");
					continue;

				} catch (ExitDateException e) {
					logger.error(e.getMessage());
					System.err.println(e.getMessage());
					continue;
				}

				catch (ConnectionException | SQLException e) {
					System.err.println(e.getMessage());
				}

			} else {
				System.err.println("Order Id doesnt exist");
				continue;
			}
		}
	}

	public static void trackProductOrder() {

		Scanner scanner = new Scanner(System.in);
		ProductService productServiceObject = new ProductServiceImpl();
		while (true) {
			System.out.println("Enter order ID: ");
			String id = scanner.next();
			boolean rmOrderIdFound = false;
			try {
				rmOrderIdFound = productServiceObject.doesProductOrderIdExist(id);
			} catch (ProductOrderIDDoesNotExistException | ConnectionException | SQLException e) {
				logger.error(e.getMessage());
				System.err.println(e.getMessage());
				continue;
			}
			if (rmOrderIdFound == true) {

				String message = productServiceObject.trackProductOrder(new ProductStock(id));
				System.out.println(message);
				break;
			}

			else {
				System.err.println("Order ID doesnt exist");
				continue;
			}

		}

	}

}