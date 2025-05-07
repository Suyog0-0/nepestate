package com.nepestate.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nepestate.model.CustomerModel;
import com.nepestate.config.DbConfig;

public class RegisterService {
	private Connection dbConn;
	private boolean isConnectionError = false;

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
		}
	}
	
	public Boolean addCustomer(CustomerModel customerModel){
		if ( isConnectionError) {
			System.err.println("Database connection is not available while adding customer.");
			return null;
		}
		String insertQuery = "INSERT INTO customers (Customer_FirstName,Customer_LastName,Customer_ProfilePicture,Customer_DoB,Customer_Username,Customer_EmailAddress,Customer_Password,Customer_PhoneNumber) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            
            insertStmt.setString(1, customerModel.getCustomer_FirstName());
            insertStmt.setString(2,  customerModel.getCustomer_LastName());
            insertStmt.setString(3,  customerModel.getCustomer_ProfilePicture());
            insertStmt.setString(4,  customerModel.getCustomer_DoB());
            insertStmt.setString(5,  customerModel.getCustomer_Username());
            insertStmt.setString(6,  customerModel.getCustomer_EmailAddress());
            insertStmt.setString(7,  customerModel.getCustomer_Password());
            insertStmt.setString(8,  customerModel.getCustomer_PhoneNumber());
            
            System.out.println("Executing insert statement");
            int rowsAffected = insertStmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            
            return rowsAffected > 0;
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
        }

	}
}
