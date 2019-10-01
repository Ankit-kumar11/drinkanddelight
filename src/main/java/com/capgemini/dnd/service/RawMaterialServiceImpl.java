
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
//import com.capgemini.dnd.model.RawMaterialSpecs;
import com.capgemini.dnd.dao.RawMaterialDAO;
import com.capgemini.dnd.dao.RawMaterialDAOImpl;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;

public class RawMaterialServiceImpl implements RawMaterialService {

	RawMaterialDAO rmdi = new RawMaterialDAOImpl();

	public void displayRawMaterialOrderDetails() throws Exception {
		rmdi.displayRawMaterialOrderDetails();
	}

	public void displayPendingRawMaterialOrderDetails() throws Exception {
		rmdi.displayPendingRawMaterialOrderDetails();
	}

	public void displayCancelledRawMaterialOrderDetails() throws Exception {
		rmdi.displayCancelledRawMaterialOrderDetails();
	}

	public void displayReceivedRawMaterialOrderDetails() throws Exception {
		rmdi.displayReceivedRawMaterialOrderDetails();
	}

	public String placeRawMaterialOrder(RawMaterialOrder newRawMaterialOrder) throws Exception {
		if (rmdi.addRawMaterialOrder(newRawMaterialOrder))
			return ("Raw Material Order placed successfully");
		return ("0 rows updated");
	}

	public String updateStatusRawMaterialOrder(String orderId, String newStatus) throws Exception {
		return (rmdi.updateStatusRawMaterialOrder(orderId, newStatus));
	}

	public void displayOrdersFromSupplier(String supid) throws Exception {
		rmdi.displayOrdersFromSupplier(supid);
	}

	public void displayRawmaterialOrdersbetweenDetails(Date dt1, Date dt2) throws Exception {
		rmdi.displayRawmaterialOrdersbetweenDetails(dt1, dt2);
	}

	public boolean doesRawMaterialOrderIdExist(String orderId)
			throws RMOrderIDDoesNotExistException, ConnectionException, SQLException {
		return (rmdi.doesRawMaterialOrderIdExist(orderId));
	}

	public String doesRMNameExist(String name) throws RMNameDoesNotExistException, ConnectionException, SQLException {
		if (rmdi.doesRMNameExist(name))
			return ("RM Name found");
		return ("RM not found");
	}

	public boolean doesSupplierIdExist(String suppId)
			throws SupplierIDDoesNotExistException, ConnectionException, SQLException {
		return (rmdi.doesSupplierIdExist(suppId));
	}

	@Override
	public boolean doesWIdExist(String WId) throws WIdDoesNotExistException, ConnectionException, SQLException {
		return (rmdi.doesWIdExist(WId));
	}

	@Override
	public boolean doesRawMaterialIdExist(String rmId, String name)
			throws RMIDDoesNotExistException, ConnectionException, SQLException {
		return (rmdi.doesRawMaterialIdExist(rmId, name));
	}

	@Override
	public String updateRMStock(RawMaterialStock rawMaterialStock) {
		try {
			return rmdi.updateRMStock(rawMaterialStock);
		} catch (SQLException | ConnectionException e) {

			return e.getMessage();
		}

	}

	@Override
	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws ProcessDateException {
		try {
			return rmdi.processDateCheck(rawMaterialStock);
		} catch (ProcessDateException e) {
			throw new ProcessDateException("Process date cannot be before its arrival(date of delivery)");
		} catch (SQLException | ConnectionException e) {

			return false;
		}
	}

	@Override
	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock) {
		return rmdi.updateProcessDateinStock(rawMaterialStock);

	}

	@Override
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock) {
		return rmdi.trackRawMaterialOrder(rawMaterialStock);
	}

	@Override
	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException {
		Date today = new Date();
		if (manufacturing_date.before(today)) {
			return true;
		}
		throw new ManufacturingDateException("Can't enter a future manufacturing date");

	}

	@Override
	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException {
		if (expiry_date.after(manufacturing_date))
			return true;
		throw new ExpiryDateException("You cant enter expiry date before manufacturing date");
	}
}
