package com.capgemini.dnd.util;

public class InputValidatorConstants {
	public static final char PHONENO_VALIDATION_CHECK_DIGIT_ZERO = '0';
	public static final char PHONENO_VALIDATION_CHECK_DIGIT_NINE = '9';
	public static final String PHONENO_EXCEPTION_MESSAGE1 = "Phone number can only contain digits!!!";
	public static final String PHONENO_EXCEPTION_MESSAGE2 = "Phone number must have 10 digits!!!";
	public static final String EMAILVALIDATOR_PATTERN_CHECK = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String INVALID_EMAILID_EXCEPTION_MESSAGE = "E-mail ID has wrong format!!!";
	public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "This input cannot be empty!!!";

	public static final String GENDER_VALIDATOR_CHECK_M = "m";
	public static final String GENDER_VALIDATOR_CHECK_MALE = "male";
	public static final String GENDER_VALIDATOR_CHECK_F = "f";
	public static final String GENDER_VALIDATOR_CHECK_FEMALE = "female";
	public static final char GENDER_VALIDATOR_CHECK_O = 'o';
	public static final String GENDER_VALIDATOR_CHECK_OTHERS = "others";
	public static final String INVALID_GENDER_EXCEPTION_MESSAGE = "Invalid gender entered!!!";

}