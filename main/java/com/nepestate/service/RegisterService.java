package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.nepestate.model.CustomerModel;
import com.nepestate.config.DbConfig;

public class RegisterService {
	private Connection dbConn;
	private boolean isConnectionError = false;
	private String errorMessage = null;
	
	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
			isConnectionError = true;
			errorMessage = "Database connection failed. Please try again later.";
		}
	}
	
	public Boolean addCustomer(CustomerModel customerModel){
		if (isConnectionError) {
			System.err.println("Database connection is not available while adding customer.");
			return null;
		}
		
		// First check if the email or username already exists
		if (isEmailAlreadyRegistered(customerModel.getCustomer_EmailAddress())) {
			errorMessage = "This email address is already registered. Please use a different email.";
			return false;
		}
		
		if (isUsernameAlreadyTaken(customerModel.getCustomer_Username())) {
			errorMessage = "This username is already taken. Please choose a different username.";
			return false;
		}
		
		if (isPhoneNumberAlreadyRegistered(customerModel.getCustomer_PhoneNumber())) {
			errorMessage = "This phone number is already registered. Please use a different phone number.";
			return false;
		}
		
		String insertQuery = "INSERT INTO customers (Customer_FirstName,Customer_LastName,Customer_ProfilePicture,Customer_DoB,Customer_Username,Customer_EmailAddress,Customer_Password,Customer_PhoneNumber) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            
            insertStmt.setString(1, customerModel.getCustomer_FirstName());
            insertStmt.setString(2, customerModel.getCustomer_LastName());
            insertStmt.setString(3, customerModel.getCustomer_ProfilePicture());
            insertStmt.setString(4, customerModel.getCustomer_DoB());
            insertStmt.setString(5, customerModel.getCustomer_Username());
            insertStmt.setString(6, customerModel.getCustomer_EmailAddress());
            insertStmt.setString(7, customerModel.getCustomer_Password());
            insertStmt.setString(8, customerModel.getCustomer_PhoneNumber());
            
            System.out.println("Executing insert statement");
            int rowsAffected = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
            return rowsAffected > 0;
        } catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'Cust_EmailAddress'")) {
				errorMessage = "This email address is already registered. Please use a different email.";
			} 
			// Check for duplicate username error
			else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'Cust_Username'")) {
				errorMessage = "This username is already taken. Please choose a different username.";
			}
			// Check for duplicate phone number error
			else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'Cust_PhoneNumber'")) {
				errorMessage = "This phone number is already registered. Please use a different phone number.";
			}
			else {
				errorMessage = "Registration failed due to a database error: " + e.getMessage();
			}
			
			return false;
        }
	}
	
	private boolean isEmailAlreadyRegistered(String email) {
		try {
			String query = "SELECT COUNT(*) FROM customers WHERE Customer_EmailAddress = ?";
			try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
				stmt.setString(1, email);
				var rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isUsernameAlreadyTaken(String username) {
		try {
			String query = "SELECT COUNT(*) FROM customers WHERE Customer_Username = ?";
			try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
				stmt.setString(1, username);
				var rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean isPhoneNumberAlreadyRegistered(String phoneNumber) {
		try {
			String query = "SELECT COUNT(*) FROM customers WHERE Customer_PhoneNumber = ?";
			try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
				stmt.setString(1, phoneNumber);
				var rs = stmt.executeQuery();
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}