package com.capgemini.dnd.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.SupplierIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;

public interface RawMaterialDAO {
//	public void viewRMspec(String name);



	public List<RawMaterialOrder> displayPendingRawMaterialOrderDetails() throws Exception;

	public List<RawMaterialOrder> displayCancelledRawMaterialOrderDetails() throws Exception;

	public List<RawMaterialOrder> displayReceivedRawMaterialOrderDetails() throws Exception;



	public boolean addRawMaterialOrder(RawMaterialOrder newRawMaterialOrder) throws Exception ;

	public String updateStatusRawMaterialOrder(String oid, String newStatus) throws Exception;

//	public void setProcessingDates(String lotId, Date dt);

//	public String getWarehouseId(String orderId);

//	public String trackRawmaterial(String lotId);
	
	public List<RawMaterialOrder> displayOrdersFromSupplier(String supid) throws Exception ;
	
	public List<RawMaterialOrder> displayRawmaterialOrdersbetweenDetails(Date dt1, Date dt2) throws Exception;

	public boolean doesRawMaterialOrderIdExist(String orderId) throws RMOrderIDDoesNotExistException, ConnectionException, SQLException;

	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock);
	
	public List<RawMaterialOrder> displayRawMaterialOrderDetails() throws Exception;

	public boolean doesRMNameExist(String name)throws RMNameDoesNotExistException, ConnectionException, SQLException;
	
	public boolean doesSupplierIdExist(String suppId) throws SupplierIDDoesNotExistException, ConnectionException, SQLException;

	public boolean doesWIdExist(String WId) throws WIdDoesNotExistException, ConnectionException, SQLException;

	public boolean doesRawMaterialIdExist(String rmId, String name)
			throws RMIDDoesNotExistException, ConnectionException, SQLException;

	public String updateRMStock(RawMaterialStock rawMaterialStock) throws SQLException, ConnectionException;

	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws SQLException, ConnectionException, ProcessDateException;

	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock);

	public boolean doesRawMaterialOrderIdExistInStock(String orderId)
			throws RMOrderIDDoesNotExistException, ConnectionException, SQLException;

}