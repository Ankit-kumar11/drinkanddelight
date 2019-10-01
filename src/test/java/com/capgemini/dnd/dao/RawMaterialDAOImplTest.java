package com.capgemini.dnd.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DistributorIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.SupplierIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;

class RawMaterialDAOImplTest {

	@Test
	void testDoesRawMaterialIdExist1() throws RMIDDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();

		assertTrue(rawMaterialDao.doesRawMaterialIdExist("RM1", "Sugar"));
	}

	@Test
	void testDoesRawMaterialIdExist2() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertThrows(RMIDDoesNotExistException.class, () -> {
			rawMaterialDao.doesRawMaterialIdExist("RM2", "Sugar");
		});
	}

	@Test
	void testDoesRawMaterialOrderIdExist1() throws RMOrderIDDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertTrue(rawMaterialDao.doesRawMaterialOrderIdExist("1"));

	}

	@Test
	void testDoesRawMaterialOrderIdExist2() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertThrows(RMOrderIDDoesNotExistException.class, () -> {
			rawMaterialDao.doesRawMaterialOrderIdExist("100");
		});
	}

	@Test
	void testDoesRMNameExist1() throws RMNameDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertTrue(rawMaterialDao.doesRMNameExist("sugar"));
	}

	@Test
	void testDoesRMNameExist2() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertThrows(RMNameDoesNotExistException.class, () -> {
			rawMaterialDao.doesRMNameExist("100");
		});
	}

	@Test
	void testDoesWIdExist1() throws WIdDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertTrue(rawMaterialDao.doesWIdExist("w01"));
	}

	@Test
	void testDoesWIdExist2() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertThrows(WIdDoesNotExistException.class, () -> {
			rawMaterialDao.doesWIdExist("w10");
		});
	}

	@Test
	void testDoesRawMaterialOrderIdExistInStock1() throws RMOrderIDDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertTrue(rawMaterialDao.doesRawMaterialOrderIdExistInStock("1"));
	}

	@Test
	void testDoesRawMaterialOrderIdExistInStock2() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertThrows(RMOrderIDDoesNotExistException.class, () -> {
			rawMaterialDao.doesRawMaterialOrderIdExistInStock("20");
		});
	}

	@Test
	void testDisplayOrdersFromSupplier() throws SupplierIDDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rmdi = new RawMaterialDAOImpl();
		assertTrue(rmdi.doesSupplierIdExist("SUP3"));

	}

	@Test
	void testExceptionDisplayOrdersFromSupplier()
			throws DistributorIDDoesNotExistException, ConnectionException, SQLException {
		RawMaterialDAO rmdi = new RawMaterialDAOImpl();
		assertThrows(SupplierIDDoesNotExistException.class, () -> {
			rmdi.doesSupplierIdExist("SUP3WEFW");
		});

	}
}
