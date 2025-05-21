package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		// Use transaction to ensure all three inserts succeed or all fail
		try {
			dbConn.setAutoCommit(false); // Start transaction
			
			// Step 1: Create a new role in the roles table
			int newRoleId = createNewCustomerRole(customerModel.getCustomer_Username());
			
			// Step 2: Insert customer
			String insertCustomerQuery = "INSERT INTO customers (Customer_FirstName,Customer_LastName,Customer_ProfilePicture,Customer_DoB,Customer_Username,Customer_EmailAddress,Customer_Password,Customer_PhoneNumber) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			int generatedCustomerId = 0;
			
			try (PreparedStatement insertStmt = dbConn.prepareStatement(insertCustomerQuery, Statement.RETURN_GENERATED_KEYS)) {
	            
	            insertStmt.setString(1, customerModel.getCustomer_FirstName());
	            insertStmt.setString(2, customerModel.getCustomer_LastName());
	            insertStmt.setString(3, customerModel.getCustomer_ProfilePicture());
	            insertStmt.setString(4, customerModel.getCustomer_DoB());
	            insertStmt.setString(5, customerModel.getCustomer_Username());
	            insertStmt.setString(6, customerModel.getCustomer_EmailAddress());
	            insertStmt.setString(7, customerModel.getCustomer_Password());
	            insertStmt.setString(8, customerModel.getCustomer_PhoneNumber());
	            
	            System.out.println("Executing customer insert statement");
	            int rowsAffected = insertStmt.executeUpdate();
	            System.out.println("Customer rows affected: " + rowsAffected);
	            
	            if (rowsAffected > 0) {
	            	// Get the generated customer ID
	            	try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
	            		if (generatedKeys.next()) {
	            			generatedCustomerId = generatedKeys.getInt(1);
	            			System.out.println("Generated Customer ID: " + generatedCustomerId);
	            		} else {
	            			throw new SQLException("Failed to retrieve generated customer ID");
	            		}
	            	}
	            } else {
	            	throw new SQLException("Failed to insert customer");
	            }
	        }
			
			// Step 3: Link the new role to the customer in role_customer table
			String insertRoleCustomerQuery = "INSERT INTO role_customer (RoleID, CustomerID) VALUES (?, ?)";
			try (PreparedStatement roleStmt = dbConn.prepareStatement(insertRoleCustomerQuery)) {
				roleStmt.setInt(1, newRoleId);
				roleStmt.setInt(2, generatedCustomerId);
				
				System.out.println("Executing role_customer insert statement with RoleID: " + newRoleId);
				int roleRowsAffected = roleStmt.executeUpdate();
				System.out.println("Role_customer rows affected: " + roleRowsAffected);
				
				if (roleRowsAffected <= 0) {
					throw new SQLException("Failed to insert into role_customer table");
				}
			}
			
			// Commit transaction
			dbConn.commit();
			System.out.println("Transaction committed successfully");
			return true;
			
		} catch (SQLException e) {
			// Rollback transaction on error
			try {
				dbConn.rollback();
				System.out.println("Transaction rolled back due to error");
			} catch (SQLException rollbackEx) {
				System.err.println("Error during rollback: " + rollbackEx.getMessage());
			}
			
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
		} finally {
			// Reset auto-commit to true
			try {
				dbConn.setAutoCommit(true);
			} catch (SQLException ex) {
				System.err.println("Error resetting auto-commit: " + ex.getMessage());
			}
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
	
	/**
	 * Creates a new role for the customer in the roles table
	 * Returns the generated RoleID
	 */
	private int createNewCustomerRole(String username) throws SQLException {
		String insertRoleQuery = "INSERT INTO roles (RoleType) VALUES (?)";
		int newRoleId = 0;
		
		// Create a unique role type for this customer
		String roleType = "customer_" + username.toLowerCase();
		
		try (PreparedStatement roleStmt = dbConn.prepareStatement(insertRoleQuery, Statement.RETURN_GENERATED_KEYS)) {
			roleStmt.setString(1, roleType);
			
			System.out.println("Creating new role with type: " + roleType);
			int rowsAffected = roleStmt.executeUpdate();
			System.out.println("Role rows affected: " + rowsAffected);
			
			if (rowsAffected > 0) {
				try (ResultSet generatedKeys = roleStmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						newRoleId = generatedKeys.getInt(1);
						System.out.println("Created new role with ID: " + newRoleId + " and type: " + roleType);
					} else {
						throw new SQLException("Failed to retrieve generated role ID");
					}
				}
			} else {
				throw new SQLException("Failed to create new role");
			}
		}
		
		return newRoleId;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}