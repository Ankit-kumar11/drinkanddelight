package com.capgemini.dnd.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationPageServlet
 */
public class RegistrationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationPageServlet() {
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
		doGet(request, response);
		String employeeName=request.getParameter("EmployeeName");
		String designation =request.getParameter("Designation");
		String emailId=request.getParameter("EmailID");
		String phoneNumber=request.getParameter("PhoneNumber");
		String dobStr=request.getParameter("DOB");
		String gender=request.getParameter("gender");
		String userName=request.getParameter("UserName");
		String password=request.getParameter("Password");
		String confirmPwd=request.getParameter("ConfirmPwd");
		response.getWriter().write(employeeName + " " + designation + " " + emailId + " " + phoneNumber + " " + dobStr + " " + gender + " " + userName + " " + password + " " + confirmPwd);
		
	}

}
