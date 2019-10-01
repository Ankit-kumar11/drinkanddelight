package com.capgemini.dnd.service;


import java.sql.SQLException;
import java.util.Date;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.SupplierIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;

public interface RawMaterialService {
//	public void viewRMspec(String name);

	public void displayRawMaterialOrderDetails() throws Exception;

	public void displayPendingRawMaterialOrderDetails() throws  Exception;

	public void displayCancelledRawMaterialOrderDetails() throws Exception;

	public void displayReceivedRawMaterialOrderDetails() throws Exception;

	public String placeRawMaterialOrder(RawMaterialOrder newRawMaterialOrder) throws Exception;

	public String updateStatusRawMaterialOrder(String oid, String newStatus) throws Exception;

	public void displayOrdersFromSupplier(String supid) throws Exception ;
	
	public void displayRawmaterialOrdersbetweenDetails(Date dt1, Date dt2) throws Exception;

	public boolean doesRawMaterialOrderIdExist(String id) throws RMOrderIDDoesNotExistException, ConnectionException, SQLException;

	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock);

	public String doesRMNameExist(String name) throws RMNameDoesNotExistException, ConnectionException, SQLException;

	public boolean doesSupplierIdExist(String name) throws SupplierIDDoesNotExistException, ConnectionException, SQLException;

	public boolean doesWIdExist(String warehouseId) throws WIdDoesNotExistException, ConnectionException, SQLException;

	public boolean doesRawMaterialIdExist(String rmId, String name) throws RMIDDoesNotExistException, ConnectionException, SQLException;

	public String updateRMStock(RawMaterialStock rawMaterialStock);

	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws ProcessDateException;

	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock);

	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException;

	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException;
	
}

