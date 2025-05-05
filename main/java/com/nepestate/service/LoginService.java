package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nepestate.model.CustomerModel;
import com.nepestate.util.PasswordUtil;
import com.nepestate.config.DbConfig;
import com.nepestate.model.AdminModel;

public class LoginService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

public Boolean loginAdmin(AdminModel adminModel) {
	if (isConnectionError) {
		System.out.println("Connection Error!");
		return null;
	}
	 System.out.println("Attempting to login with username: " + adminModel.getAdmin_Username());
	String query = "SELECT Admin_Username, Admin_Password FROM admins WHERE Admin_Username = ?";
	try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
		stmt.setString(1, adminModel.getAdmin_Username());
		ResultSet resultAdmin = stmt.executeQuery();
		System.out.println(resultAdmin);

		if (resultAdmin.next()) {
			System.out.println("User found in the database");
			return validatePasswordForAdmin(resultAdmin, adminModel);
		}else {
            System.out.println("No user found with username in Admin table: " + adminModel.getAdmin_Username());
        }
	} catch (SQLException e) {
		System.out.println("SQL Exception occurred: " + e.getMessage());
		e.printStackTrace();
		System.out.println("2");
		return null;
	}

	return false;
	}
public Boolean loginUser(CustomerModel customerModel) {
	if (isConnectionError) {
		System.out.println("Connection Error!");
		return null;
	}
	 System.out.println("Attempting to login with username: " + customerModel.getCustomer_Username());
	String query = "SELECT Customer_Username, Customer_Password FROM customers WHERE Customer_Username = ?";
	try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
		stmt.setString(1, customerModel.getCustomer_Username());
		ResultSet result = stmt.executeQuery();


		if (result.next()) {
			System.out.println("User found in the database");
			return validatePasswordForCustomer(result, customerModel);
		}else {
            System.out.println("No user found with username in customer table: " + customerModel.getCustomer_Username());
        }
	} catch (SQLException e) {
		System.out.println("SQL Exception occurred: " + e.getMessage());
		e.printStackTrace();
		System.out.println("2");
		return null;
	}

	return false;
	}
public CustomerModel loginCustomer(CustomerModel customerModel) {
    if (isConnectionError) {
        System.out.println("Connection Error!");
        return null;
    }
    
    System.out.println("Attempting to login with username: " + customerModel.getCustomer_Username());
    String query = "SELECT CustomerID, Customer_Username, Customer_Password FROM customers WHERE Customer_Username = ?";
    
    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
        stmt.setString(1, customerModel.getCustomer_Username());
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            System.out.println("User found in the database");
            String dbUsername = rs.getString("Customer_Username");
            String dbPassword = rs.getString("Customer_Password");
            
            System.out.println("DB Username: " + dbUsername);
            System.out.println("DB Password (encrypted): " + dbPassword);
            
            // Debug input values
            System.out.println("Input Username: " + customerModel.getCustomer_Username());
            System.out.println("Input Password: " + customerModel.getCustomer_Password());
            
            // Debug decryption process
            String decryptedDbPassword = "";
            try {
                decryptedDbPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
                System.out.println("Decrypted DB Password: " + decryptedDbPassword);
            } catch (Exception e) {
                System.out.println("Error during password decryption: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
            
            // Debug individual validation steps
            boolean usernameMatch = dbUsername.equals(customerModel.getCustomer_Username());
            boolean passwordMatch = decryptedDbPassword.equals(customerModel.getCustomer_Password());
            System.out.println("Username match: " + usernameMatch);
            System.out.println("Password match: " + passwordMatch);
            
            boolean passwordValid = usernameMatch && passwordMatch;
            System.out.println("Overall validation result: " + passwordValid);
            
            if (passwordValid) {
                CustomerModel loggedInCustomer = new CustomerModel();
                loggedInCustomer.setCustomerID(rs.getInt("CustomerID"));
                loggedInCustomer.setCustomer_Username(rs.getString("Customer_Username"));
                System.out.println("Password validation successful for user: " + dbUsername);
                return loggedInCustomer;
            } else {
                System.out.println("Password validation failed for user: " + dbUsername);
                return null;
            }
        } else {
            System.out.println("No user found with username: " + customerModel.getCustomer_Username());
            return null;
        }
    } catch (SQLException e) {
        System.err.println("Database error during customer login: " + e.getMessage());
        e.printStackTrace();
        return null;
    } catch (Exception e) {
        System.err.println("Unexpected error during customer login: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}


private boolean validatePasswordForCustomer(ResultSet result, CustomerModel customerModel) throws SQLException {
	String dbUsername = result.getString("Customer_Username");
	System.out.println(dbUsername);
	String dbPassword = result.getString("Customer_Password");
	System.out.println(dbPassword);
	System.out.println(PasswordUtil.decrypt(dbPassword, dbUsername));
	return dbUsername.equals(customerModel.getCustomer_Username()) 
			&& PasswordUtil.decrypt(dbPassword, dbUsername).equals(customerModel.getCustomer_Password());
			
	}        

private boolean validatePasswordForAdmin(ResultSet result, AdminModel adminModel) throws SQLException {
	String dbUsername = result.getString("Admin_Username");
	System.out.println(dbUsername);
	String dbPassword = result.getString("Admin_Password");
	System.out.println(dbPassword);

	return dbUsername.equals(adminModel.getAdmin_Username()) &&
	dbPassword.equals(adminModel.getAdmin_Password());
		}        
	}


