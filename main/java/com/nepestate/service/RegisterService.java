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

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public Boolean addCustomer(CustomerModel customerModel){
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}
		String insertQuery = "INSERT INTO customers (Cust_FirstName,Cust_LastName,Cust_ProfilePicture,Cust_DOB,Cust_Username,Cust_EmailAddress,Cust_Password,Cust_PhoneNumber) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            // Insert student details
            insertStmt.setString(1, customerModel.getCust_FirstName());
            insertStmt.setString(2,  customerModel.getCust_LastName());
            insertStmt.setString(3,  customerModel.getCust_ProfilePicture());
            insertStmt.setString(4,  customerModel.getCust_DoB());
            insertStmt.setString(5,  customerModel.getCust_Username());
            insertStmt.setString(6,  customerModel.getCust_EmailAddress());
            insertStmt.setString(7,  customerModel.getCust_Password());
            insertStmt.setString(8,  customerModel.getCust_PhoneNumber());
            
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
