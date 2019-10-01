package com.capgemini.dnd.util;

import java.util.regex.Pattern;

import com.capgemini.dnd.customexceptions.EmptyInputException;
import com.capgemini.dnd.customexceptions.InvalidEmailIdException;
import com.capgemini.dnd.customexceptions.InvalidGenderException;
import com.capgemini.dnd.customexceptions.PhoneNoException;

public class InputValidator {


	public InputValidator() {
	
	}
	
	public static void phoneNoValidator(String phoneNo) throws PhoneNoException {
		int phoneNoLength=phoneNo.length();
		for(int index=0;index<phoneNoLength;index++) {
			if(phoneNo.charAt(index)<InputValidatorConstants.PHONENO_VALIDATION_CHECK_DIGIT_ZERO || phoneNo.charAt(index)>InputValidatorConstants.PHONENO_VALIDATION_CHECK_DIGIT_NINE)
				throw new PhoneNoException(InputValidatorConstants.PHONENO_EXCEPTION_MESSAGE1);
		}
		if(phoneNoLength!=10)
			throw new PhoneNoException(InputValidatorConstants.PHONENO_EXCEPTION_MESSAGE2);
	}
	
	public static void emailIdValidator(String emailId) throws InvalidEmailIdException{
		String regex = InputValidatorConstants.EMAILVALIDATOR_PATTERN_CHECK;
		if(!Pattern.compile(regex).matcher(emailId).matches())
			throw new InvalidEmailIdException(InputValidatorConstants.INVALID_EMAILID_EXCEPTION_MESSAGE);
	}
	
	public static void notEmptyValidator(String input) throws EmptyInputException {
		input=input.trim();
		if(input.isEmpty())
			throw new EmptyInputException(InputValidatorConstants.EMPTY_INPUT_EXCEPTION_MESSAGE);
	}
	
	public static void genderValidator(String gender) throws InvalidGenderException {
		gender = gender.toLowerCase();
		if (gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_M) || gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_MALE) || gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_F) || gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_FEMALE) 
				|| gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_O) || gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_OTHERS)) {}
		else
			throw new InvalidGenderException(InputValidatorConstants.INVALID_GENDER_EXCEPTION_MESSAGE);
	}
	
	
}
