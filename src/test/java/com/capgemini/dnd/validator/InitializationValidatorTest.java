package com.capgemini.dnd.validator;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.dnd.validator.InitializationValidator;

class InitializationValidatorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		InitializationValidator iv=new InitializationValidator();
		assertTrue(iv.contactValidator("9431878665"));	
	}
	
	@Test
	void test2() {
		InitializationValidator iv=new InitializationValidator();
		assertTrue(iv.emailValidator("amar420@gmail.co.in"));
	}
	
	@Test
	void test3() {
		InitializationValidator iv=new InitializationValidator();
		assertTrue(iv.pincodeValidator("700150"));
	}
	
	@Test
	
	void test4() {
		InitializationValidator iv=new InitializationValidator();
		assertTrue(iv.dobValidator(Date.valueOf("11-12-1997")));
	}

}
