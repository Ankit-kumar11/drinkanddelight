package com.capgemini.dnd.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DistributorIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductNameDoesNotExistException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.UpdateException;
import com.capgemini.dnd.customexceptions.WIdDoesNotExistException;

class ProductDAOImplTest {

	@Test
	void testDisplayOrdersFromDistributor1()
			throws DistributorIDDoesNotExistException, ConnectionException, SQLException {

		ProductDAO productDao = new ProductDAOImpl();
		assertTrue(productDao.doesDistributorIdExist("d001"));
	}

	@Test
	void testDisplayOrdersFromDistributor2()
			throws DistributorIDDoesNotExistException, ConnectionException, SQLException {
		ProductDAO productDao = new ProductDAOImpl();
		assertThrows(DistributorIDDoesNotExistException.class, () -> {
			productDao.doesDistributorIdExist("d10nxs");
		});
	}

	@Test
	void testDoesProductIdExist1() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertTrue(productDao.doesProductIdExist("PSID1", "JUICE"));
		} catch (ConnectionException | SQLException | ProductIDDoesNotExistException e) {
		}
	}

	@Test
	void testDoesProductIdExist2() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertFalse(productDao.doesProductIdExist("PSID100", "MOCKTAIL"));
		} catch (ConnectionException | SQLException | ProductIDDoesNotExistException e) {
		}
	}

	@Test
	void testDoesProductIdExist3() {
		ProductDAO productDao = new ProductDAOImpl();
		assertThrows(ProductIDDoesNotExistException.class, () -> {
			productDao.doesProductIdExist("PSID100", "MOCKTAIL");
		});
	}

	@Test
	void testDoesProductOrderIdExistInStock1() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertTrue(productDao.doesProductOrderIdExistInStock("3"));
		} catch (ConnectionException | SQLException | ProductOrderIDDoesNotExistException e) {
		}
	}

	@Test
	void testDoesProductOrderIdExistInStock2() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertFalse(productDao.doesProductOrderIdExistInStock("100"));
		} catch (ConnectionException | SQLException | ProductOrderIDDoesNotExistException e) {
		}
	}

	@Test
	void testDoesProductOrderIdExistInStock3() {
		ProductDAO productDao = new ProductDAOImpl();
		assertThrows(ProductOrderIDDoesNotExistException.class, () -> {
			productDao.doesProductOrderIdExistInStock("1000");
		});
	}

	@Test
	void testDoesDistributorIdExist1() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertTrue(productDao.doesDistributorIdExist("d001"));
		} catch (ConnectionException | SQLException | DistributorIDDoesNotExistException e) {
		}
	}

	@Test
	void testDoesDistributorIdExist2() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertFalse(productDao.doesDistributorIdExist("d100"));
		} catch (ConnectionException | SQLException | DistributorIDDoesNotExistException e) {
		}
	}

	@Test
	void testDoesDistributorIdExist3() {
		ProductDAO productDao = new ProductDAOImpl();
		assertThrows(DistributorIDDoesNotExistException.class, () -> {
			productDao.doesDistributorIdExist("d100");
		});
	}

	@Test
	void testDoesProductNameExist1() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertTrue(productDao.doesProductNameExist("mocktail"));
		} catch (ConnectionException | SQLException | ProductNameDoesNotExistException e) {
		}
	}

	@Test
	void testDoesProductNameExist2() {
		ProductDAO productDao = new ProductDAOImpl();
		try {
			assertFalse(productDao.doesProductNameExist("sugar"));
		} catch (ConnectionException | SQLException | ProductNameDoesNotExistException e) {
		}
	}

	@Test
	void testDoesProductNameExist3() {
		ProductDAO productDao = new ProductDAOImpl();
		assertThrows(ProductNameDoesNotExistException.class, () -> {
			productDao.doesProductNameExist("sugar");
		});
	}

	@Test
	void testDoesWIdExist1() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		try {
			assertTrue(rawMaterialDao.doesWIdExist("w01"));
		} catch (ConnectionException | SQLException | WIdDoesNotExistException e) {
		}
	}

	@Test
	void testDoesWIdExist2() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		try {
			assertFalse(rawMaterialDao.doesWIdExist("w10"));
		} catch (ConnectionException | SQLException | WIdDoesNotExistException e) {
		}
	}

	@Test
	void testDoesWIdExist3() {
		RawMaterialDAO rawMaterialDao = new RawMaterialDAOImpl();
		assertThrows(WIdDoesNotExistException.class, () -> {
			rawMaterialDao.doesWIdExist("w10");
		});
	}

	@Test
	void testDisplayOrdersFromDistributor()
			throws DistributorIDDoesNotExistException, ConnectionException, SQLException {

		ProductDAO pdi = new ProductDAOImpl();

		assertTrue(pdi.doesDistributorIdExist("d003"));
		assertEquals(true, pdi.doesDistributorIdExist("d003"));

	}

	@Test
	void testUpdateStatusProductOrder() throws Exception {
		ProductDAOImpl productdao_obj = new ProductDAOImpl();
		assertEquals("Updated succesfully", productdao_obj.updateStatusProductOrder("1", "recieved"));

	}

	@Test
	void testUpdateStatusProductOrder1() throws Exception {
		ProductDAOImpl productdao_obj = new ProductDAOImpl();
		assertThrows(UpdateException.class, () -> {
			productdao_obj.updateStatusProductOrder("22", "recieved");
		});

	}

}
