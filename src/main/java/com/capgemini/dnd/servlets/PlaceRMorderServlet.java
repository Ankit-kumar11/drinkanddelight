package com.capgemini.dnd.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.dnd.customexceptions.*;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.service.RawMaterialService;
import com.capgemini.dnd.service.RawMaterialServiceImpl;


public class PlaceRMorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1246354653523L;
	
	public PlaceRMorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		doGet(req, res);
		RawMaterialService rmsi_obj = new RawMaterialServiceImpl();
		String errorMessage = "";	
		doGet(req, res);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PrintWriter out = res.getWriter();
		String rawMaterialName = req.getParameter("RawMaterialName");
		String rmId = req.getParameter("RMID");
		String supplierId = req.getParameter("SUPID");
		double quantity = Double.parseDouble(req.getParameter("Quantity"));
		String quantityUnit = req.getParameter("QuantityUnit");
		LocalDate localDate = LocalDate.now();

	
		Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		Date expectedDateofDelivery = null;
		try {
			expectedDateofDelivery = sdf.parse(req.getParameter("expectedDateofDelivery"));
		} catch (ParseException e) {
			errorMessage += "<br>" +Constants.PARSE_EXCEPTION_INVALID_FORMAT;
		}

		double price_per_unit = Double.parseDouble(req.getParameter("price_per_unit"));
		String warehouseId = req.getParameter("warehouseId");

		
		/////////TRYCATCH
		
		
		
		
//		try {
//			rmsi_obj.doesRMNameExist(rawMaterialName);
//		}
//		catch (RMNameDoesNotExistException | ConnectionException | SQLException e) {
//			
//			errorMessage += "<br>" + e.getMessage();
//		}
//		try {
//			rmsi_obj.doesRawMaterialIdExist(rmId, rawMaterialName);
//		} catch (RMIDDoesNotExistException | ConnectionException | SQLException e) {
//			errorMessage += "<br>" + e.getMessage();
//
//		}
//		try {
//			rmsi_obj.doesSupplierIdExist(supplierId);
//		} catch (SupplierIDDoesNotExistException | ConnectionException | SQLException e) {
//		
//			errorMessage += "<br>" + e.getMessage();
//		}
//
//		try {
//			rmsi_obj.doesWIdExist(warehouseId);
//		} catch (WIdDoesNotExistException | ConnectionException | SQLException e) {
//			errorMessage += "<br>" + e.getMessage();
//		}
	
		
		RawMaterialOrder rmo = new RawMaterialOrder(rawMaterialName, rmId, supplierId, quantity, quantityUnit, today,
				expectedDateofDelivery, price_per_unit, warehouseId);

		try {
			if(errorMessage.equals("")) 
				rmsi_obj.placeRawMaterialOrder(rmo);
			res.getWriter().write("successful");
			
			} catch (Exception e) {
				errorMessage += "<br>" + e.getMessage();
			}
		res.getWriter().write(errorMessage);
	
	}


}
