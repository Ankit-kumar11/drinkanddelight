package com.capgemini.dnd.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.dto.Employee;
import com.capgemini.dnd.pl.MainMenuDriver;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.service.EmployeeServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getRootLogger();
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Logger level = " + logger.getLevel());
		logger.info("Inside doPost of LoginServlet");

		doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		EmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword(password);
		try{
			if(employeeService.login(employee)) {
				response.getWriter().write("Welcome " + username);
//				MainMenuDriver.displayScreenAfterLogin(employee);
//				try {
//					if(employeeService.logout(employee))
//						response.getWriter().write("Logged out succesfully.");
//				}catch(Exception exception) {
//					response.getWriter().write(exception.getMessage());
//				}
				response.sendRedirect("http://www.google.com");
			}
		}catch(Exception exception) {
			response.getWriter().write(exception.getMessage());
		}	
	  	
	}

}
