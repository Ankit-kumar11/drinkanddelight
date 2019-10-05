package com.capgemini.dnd.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayAllRawMaterialOrdersServlet
 */
public class DisplayAllRawMaterialOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisplayAllRawMaterialOrdersServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.getWriter().write("Ankit Gupta");
//		response.getWriter().write("<h1>Ankit Gupta</h1>");
		response.getStatus();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().append("handling post");
		String typedMyName = request.getParameter("myname");
		response.getWriter().write("<h1>Entered name is"+typedMyName+"</h1>");
		
	}

}
