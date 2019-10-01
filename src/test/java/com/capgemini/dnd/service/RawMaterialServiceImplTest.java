package com.capgemini.dnd.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.dto.RawMaterialStock;

class RawMaterialServiceImplTest {

	@Test
	void testUpdateRMStock() throws ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("Data inserted", rms.updateRMStock(new RawMaterialStock("7",sdf.parse("2019-08-10"), sdf.parse("2020-02-25"), "PASSED")));
	}
	
	

	@Test
	void testProcessDateCheck() throws ParseException, ProcessDateException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(rms.processDateCheck(new RawMaterialStock("7", sdf.parse("2019-10-25"))));
	}
	
	@Test
	void testProcessDateCheck2() throws ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertThrows(ProcessDateException.class, () -> {
			rms.processDateCheck(new RawMaterialStock("7", sdf.parse("2022-01-09")));
		});
	}

	



	@Test
	void testUpdateProcessDateinStock() throws ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("Data inserted", rms.updateProcessDateinStock(new RawMaterialStock("7", sdf.parse("2019-10-25"))));
	}

	@Test
	void testTrackRawMaterialOrder() {
		RawMaterialService rms = new RawMaterialServiceImpl();
		assertEquals("The order ID had been in the warehouse with warehouseID = w01 from 2019-08-01 to 2019-09-02(32 days)", rms.trackRawMaterialOrder(new RawMaterialStock("1")));
	}
	
	@Test
	void testTrackRawMaterialOrder2() {
		RawMaterialService rms = new RawMaterialServiceImpl();
		assertEquals("Could not connect to the database", rms.trackRawMaterialOrder(new RawMaterialStock("100")));
	}

	@Test
	void testValidateManufacturingDate() throws ManufacturingDateException, ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(rms.validateManufacturingDate(sdf.parse("2019-09-25")));
	}
	
	
	@Test
	void testValidateManufacturingDate2() throws ManufacturingDateException, ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertThrows(ManufacturingDateException.class, () -> {
			rms.validateManufacturingDate(sdf.parse("2019-12-25")); });
	}
	

	@Test
	void testValidateExpiryDate() throws ExpiryDateException, ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertTrue(rms.validateExpiryDate(sdf.parse("2019-09-25"), sdf.parse("2020-02-10")));
	}
	
	@Test
	void testValidateExpiryDate2() throws ExpiryDateException, ParseException {
		RawMaterialService rms = new RawMaterialServiceImpl();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertThrows(ExpiryDateException.class, () -> {
			rms.validateExpiryDate(sdf.parse("2019-12-25"), sdf.parse("2019-11-30")); });
	}
	
	
	}


