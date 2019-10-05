package com.capgemini.dnd.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DistributorIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProductIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.ProductStock;

public interface ProductService {
	public String updateStatusProductOrder(String oid, String newStatus) throws Exception;

	public List<ProductOrder> displayProductOrderDetails() throws Exception ;

	public List<ProductOrder> displayPendingProductOrderDetails() throws Exception;

	public List<ProductOrder> displayCancelledProductOrderDetails() throws Exception ;
	
	public List<ProductOrder> displayReceivedProductOrderDetails() throws Exception ;
	
    public String placeProductOrder(ProductOrder ProductOrderobject) throws Exception;

//	public String updateQAStatusProductLot(String plid, String newStatus);

//	public String trackProduct(String lotId);

//	public boolean viewProductSpec(String name);
	
//    public void displayDeliveredProductOrderDetails() throws Exception ;
	
    public List<ProductOrder> displayDispatchedProductOrderDetails() throws Exception;
	
//    public long timeTakenForDelivery(String plotid);

	public List<ProductOrder> displayProductOrderbetweenDetails(Date dt1, Date dt2) throws Exception;

	public List<ProductOrder> displayOrdersFromDistributor(String distid) throws Exception;

	public boolean doesProductNameExist(String name) throws ProductNameDoesNotExistException, ConnectionException, SQLException;

	public boolean doesProductOrderIdExist(String pid) throws ProductOrderIDDoesNotExistException, ConnectionException, SQLException;

	public boolean doesDistributorIdExist(String distributorId) throws DistributorIDDoesNotExistException, ConnectionException, SQLException;

	

	

	

	public String trackProductOrder(ProductStock productStock);

	public boolean doesWIdExist(String WId) throws WIdDoesNotExistException, ConnectionException, SQLException;

	public boolean doesProductIdExist(String pid, String name) throws ConnectionException, SQLException, ProductIDDoesNotExistException;

	public boolean exitDateCheck(ProductStock productStock) throws ExitDateException, SQLException, ConnectionException;

	public String updateExitDateinStock(ProductStock productStock);

	public String updateProductStock(ProductStock productStock);

	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException;

	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException;

}
