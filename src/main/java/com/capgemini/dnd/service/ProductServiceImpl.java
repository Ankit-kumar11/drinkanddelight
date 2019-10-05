package com.capgemini.dnd.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DistributorIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProductIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dao.ProductDAO;
import com.capgemini.dnd.dao.ProductDAOImpl;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.ProductStock;

public class ProductServiceImpl implements ProductService {

	Scanner scn = new Scanner(System.in);

	ProductDAO pdi = new ProductDAOImpl();

	public String updateStatusProductOrder(String oid, String newStatus) throws Exception {
		return pdi.updateStatusProductOrder(oid, newStatus);
	}

	public List<ProductOrder> displayProductOrderDetails() throws Exception {
		return pdi.displayProductOrderDetails();
	}

	public List<ProductOrder> displayPendingProductOrderDetails() throws Exception {
		return pdi.displayPendingProductOrderDetails(); 
	}

	public List<ProductOrder> displayReceivedProductOrderDetails() throws Exception {
		return pdi.displayReceivedProductOrderDetails();
	}

	public  List<ProductOrder> displayCancelledProductOrderDetails() throws Exception {
		return pdi.displayCancelledProductOrderDetails();
	}

	public List<ProductOrder> displayDispatchedProductOrderDetails() throws Exception {
		return pdi.displayDispatchedProductOrderDetails();
	}

	public List<ProductOrder> displayProductOrderbetweenDetails(Date dt1, Date dt2) throws Exception {
		return pdi.displayProductOrderbetweenDetails(dt1,dt2);
	}

	public String placeProductOrder(ProductOrder newProductOrder) throws Exception {
		return (pdi.addProductOrder(newProductOrder));
	}

	public List<ProductOrder> displayOrdersFromDistributor(String distId) throws Exception {
		return pdi.displayOrdersFromDistributor(distId);
	}

	public boolean doesProductNameExist(String name)
			throws ProductNameDoesNotExistException, ConnectionException, SQLException {
		return (pdi.doesProductNameExist(name));
	}

	public boolean doesProductOrderIdExist(String orderId)
			throws ProductOrderIDDoesNotExistException, ConnectionException, SQLException {
		return (pdi.doesProductOrderIdExist(orderId));
	}

	public boolean doesProductIdExist(String orderId, String name)
			throws ProductIDDoesNotExistException, ConnectionException, SQLException {
		return (pdi.doesProductIdExist(orderId, name));
	}

	public boolean doesDistributorIdExist(String distId)
			throws DistributorIDDoesNotExistException, ConnectionException, SQLException {
		return (pdi.doesDistributorIdExist(distId));
	}

	@Override
	public boolean doesWIdExist(String WId) throws WIdDoesNotExistException, ConnectionException, SQLException {
		return (pdi.doesWIdExist(WId));
	}

	@Override
	public String trackProductOrder(ProductStock productStock) {
		return pdi.trackProductOrder(productStock);
	}

	@Override
	public boolean exitDateCheck(ProductStock productStock)
			throws ExitDateException, SQLException, ConnectionException {
		return pdi.exitDateCheck(productStock);
	}

	@Override
	public String updateExitDateinStock(ProductStock productStock) {
		return pdi.updateExitDateinStock(productStock);

	}

	@Override
	public String updateProductStock(ProductStock productStock) {
		return pdi.updateProductStock(productStock);
	}

	@Override
	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException {
		Date today = new Date();
		if (manufacturing_date.before(today)) {
			return true;
		}
		throw new ManufacturingDateException("You cant enter a future manufacturing date");
	}

	@Override
	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException {
		if (expiry_date.after(manufacturing_date))
			return true;
		throw new ExpiryDateException("You cant enter expiry date before manufacturing date");

	}
}
