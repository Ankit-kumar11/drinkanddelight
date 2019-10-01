package com.capgemini.dnd.validator;

import java.util.Calendar;
import java.util.Date;

//import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitializationValidator {

	enum RawMaterialDeliveryStatuses {
		RECEIVED, CANCELLED, PENDING
	};

	enum ProductDeliveryStatuses {
		received, pending, cancelled, dispatched
	};

	enum ProductQAStauses {
		PASSED, FAILED
	};

	public boolean contactValidator(String phoneNo) {
		int len = phoneNo.length();
		if (len != 10)
			return false;
		for (int i = 0; i < len; i++) {
			if (phoneNo.charAt(i) < InitializationValidatorConstants.CONTACT_VALIDATION_CHECK_DIGIT_ZERO || phoneNo.charAt(i) > InitializationValidatorConstants.CONTACT_VALIDATION_CHECK_DIGIT_NINE) {
				return false;
			}
		}
		return true;
	}

	public boolean emailValidator(String emailId) {
		String regex =InitializationValidatorConstants.EMAILVALIDATOR_PATTERN_CHECK;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

	public boolean pincodeValidator(String pincode) {
		int len = pincode.length();
		if (len != 6) // in the case study, the business is limited to India only, can be modified
						// later according to requirement
			return false;
		for (int i = 0; i < len; i++) {
			if (pincode.charAt(i) < InitializationValidatorConstants.PINCODE_VALIDATOR_CHECK_DIGIT_ZERO || pincode.charAt(i) > InitializationValidatorConstants.PINCODE_VALIDATOR_CHECK_DIGIT_NINE)
				return false;
		}
		return true;
	}

	public boolean maleValidator(String gender) {
		gender = gender.toLowerCase();
		if (gender.equals(InitializationValidatorConstants.GENDER_VALIDATOR_CHECK_M) || gender.equals(InitializationValidatorConstants.GENDER_VALIDATOR_CHECK_MALE))
			return true;
		return false;
	}

	public boolean femaleValidator(String gender) {
		gender = gender.toLowerCase();
		if (gender.equals(InitializationValidatorConstants.GENDER_VALIDATOR_CHECK_F) || gender.equals(InitializationValidatorConstants.GENDER_VALIDATOR_CHECK_FEMALE))
			return true;
		return false;
	}

	public boolean dobValidator(Date dob) {
		Calendar calendar = Calendar.getInstance();
		int presentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(dob);
		int dobYear = calendar.get(Calendar.YEAR);
		if (presentYear - dobYear < 19)
			return false;
		return true;
	}

	public boolean IDValidator(String number) {
		try {
			int value = Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			// throw
			return false;
		}
	}

//	public static boolean contains(String test) {
//
//	    for (Choice c : Choice.values()) {
//	        if (c.name().equals(test)) {
//	            return true;
//	        }
//	    }
//
//	    return false;
//	}

	public static boolean deliveryRawMaterialOrderStatusValidator(String newStatus) {
		for (RawMaterialDeliveryStatuses status : RawMaterialDeliveryStatuses.values()) {
			if (status.name().equalsIgnoreCase(newStatus)) {
				return true;
			}
		}

		return false;
	}

	public static boolean deliveryProductOrderStatusValidator(String newStatus) {
		for (ProductDeliveryStatuses status : ProductDeliveryStatuses.values()) {
			if (status.name().equalsIgnoreCase(newStatus)) {
				return true;
			}
		}

		return false;
	}

	public static boolean ProductQAStatusValidator(String newStatus) {
		for (ProductQAStauses status : ProductQAStauses.values()) {
			if (status.name().equalsIgnoreCase(newStatus)) {
				return true;
			}
		}

		return false;
	}

}
