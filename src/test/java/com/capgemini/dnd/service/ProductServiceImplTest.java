package com.capgemini.dnd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.dto.RawMaterialStock;

class ProductServiceImplTest {

	@Test
	void testUpdateProductStock() throws ParseException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("Data inserted", ps
				.updateProductStock(new ProductStock("7", sdf.parse("2019-08-10"), sdf.parse("2020-02-25"), "PASSED")));
	}

	@Test
	void testExitDateCheck() throws ParseException, ExitDateException, SQLException, ConnectionException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(ps.exitDateCheck(new ProductStock("7", sdf.parse("2019-10-25"))));
	}

	@Test
	void testExitDateCheck2() throws ParseException, ExitDateException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertThrows(ExitDateException.class, () -> {
			ps.exitDateCheck(new ProductStock("7", sdf.parse("2025-05-06")));
		});
	}

	void testIsDateValid2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		ProductService psi = new ProductServiceImpl();

		assertThrows(ParseException.class, () -> {
			psi.displayProductOrderbetweenDetails(sdf.parse("2019-08/17"), sdf.parse("2019-08-17"));
			;
		});
	}

	@Test
	void testUpdateExitDateinStock() throws ParseException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("Data inserted", ps.updateExitDateinStock(new ProductStock("7", sdf.parse("2019-09-25"))));
	}

	@Test
	void testTrackProductOrder() {
		ProductService ps = new ProductServiceImpl();
		assertEquals(
				"The order ID had been in the warehouse with warehouseID = w01 from 2019-07-16 to 2019-07-30(14 days)",
				ps.trackProductOrder(new ProductStock("1")));
	}

	@Test
	void testTrackProductOrder2() {
		ProductService ps = new ProductServiceImpl();
		assertEquals("Could not connect to the database", ps.trackProductOrder(new ProductStock("100")));
	}

	@Test
	void testValidateManufacturingDate() throws ManufacturingDateException, ParseException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(ps.validateManufacturingDate(sdf.parse("2019-09-25")));
	}

	@Test
	void testValidateManufacturingDate2() throws ManufacturingDateException, ParseException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertThrows(ManufacturingDateException.class, () -> {
			ps.validateManufacturingDate(sdf.parse("2019-12-25"));
		});
	}

	@Test
	void testValidateExpiryDate() throws ExpiryDateException, ParseException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(ps.validateExpiryDate(sdf.parse("2019-09-25"), sdf.parse("2020-02-10")));
	}

	@Test
	void testValidateExpiryDate2() throws ExpiryDateException, ParseException {
		ProductService ps = new ProductServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertThrows(ExpiryDateException.class, () -> {
			ps.validateExpiryDate(sdf.parse("2019-12-25"), sdf.parse("2019-11-30"));
		});
	}

}
