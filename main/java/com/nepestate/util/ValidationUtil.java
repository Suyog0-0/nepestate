package com.nepestate.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;


public class ValidationUtil {
	public boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
	public boolean isAlphabetic(String value) {
		return !isNullOrEmpty(value) && value.matches("^[a-zA-Z]+$"); //
	}
	public boolean isAlphanumericStartingWithLetter(String value) {
		return !isNullOrEmpty(value) && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}
	public boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return !isNullOrEmpty(email) && Pattern.matches(emailRegex, email);
	}
	public boolean isValidPhoneNumber(String number) {
		return !isNullOrEmpty(number) && number.matches("^98\\d{8}$");
	}
	public boolean isValidPassword(String password) {
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return !isNullOrEmpty(password) && password.matches(passwordRegex);
	}
	public boolean doPasswordsMatch(String password, String retypePassword) {
		return !isNullOrEmpty(password) && !isNullOrEmpty(retypePassword) && password.equals(retypePassword);
	}
	public boolean isAgeAtLeast16(LocalDate dob) {
		if (dob == null) {
			return false;
		}
		LocalDate today = LocalDate.now();
		return Period.between(dob, today).getYears() >= 16;
	}
	
}
