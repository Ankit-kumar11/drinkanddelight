package com.capgemini.dnd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dto.ProductOrder;

import com.capgemini.dnd.service.ProductService;
import com.capgemini.dnd.service.ProductServiceImpl;


public class PlaceProductOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1028319732394752L;

	public PlaceProductOrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//        response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
		ProductService psi_obj = new ProductServiceImpl();
		String errorMessage = "";
		PrintWriter out = res.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String productName = req.getParameter("ProductName");
		String pid = req.getParameter("ProductID");
		String distributorId = req.getParameter("DistributorID ");
		double quantityValue = Double.parseDouble(req.getParameter("Quantity"));
		String quantityUnit = req.getParameter("QuantityUnit");
		LocalDate localDate = LocalDate.now();
		Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Date expectedDateofDelivery = null;
		try {
			expectedDateofDelivery = sdf.parse(req.getParameter("expectedDateofDelivery"));
		} catch (ParseException e) {
			errorMessage += "<br>" + Constants.PARSE_EXCEPTION_INVALID_FORMAT;
		}
		double price_per_unit = Double.parseDouble(req.getParameter("price_per_unit"));
		String warehouseId = req.getParameter("warehouseId");

		ProductOrder psi = new ProductOrder(productName, pid, distributorId, quantityValue, quantityUnit,
				today,expectedDateofDelivery, price_per_unit, warehouseId);

		try {
			if (errorMessage.equals(""))
				psi_obj.placeProductOrder(psi);

		} catch (Exception e) {
			errorMessage += "<br>" + e.getMessage();
		}
		res.getWriter().write(errorMessage);
	}
}
