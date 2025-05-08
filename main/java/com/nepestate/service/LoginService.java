package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.nepestate.config.DbConfig;
import com.nepestate.model.AdminModel;
import com.nepestate.model.CustomerModel;
import com.nepestate.util.PasswordUtil;

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
        System.out.println("Attempting to login admin: " + adminModel.getAdmin_Username());
        String query = "SELECT Admin_Username, Admin_Password FROM admins WHERE Admin_Username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, adminModel.getAdmin_Username());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return validatePasswordForAdmin(rs, adminModel);
            } else {
                System.out.println("No admin found with username: " + adminModel.getAdmin_Username());
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during admin login: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return false;
    }

    public Boolean loginUser(CustomerModel customerModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        System.out.println("Attempting to login user: " + customerModel.getCustomer_Username());
        String query = "SELECT Customer_Username, Customer_Password FROM customers WHERE Customer_Username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, customerModel.getCustomer_Username());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return validatePasswordForCustomer(rs, customerModel);
            } else {
                System.out.println("No user found with username: " + customerModel.getCustomer_Username());
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during user login: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return false;
    }

    public CustomerModel loginCustomer(CustomerModel customerModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        System.out.println("Attempting to login customer: " + customerModel.getCustomer_Username());
        String query = "SELECT CustomerID, Customer_Username, Customer_Password FROM customers WHERE Customer_Username = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, customerModel.getCustomer_Username());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String dbUsername = rs.getString("Customer_Username");
                String dbPassword = rs.getString("Customer_Password");

                System.out.println("DB Username: " + dbUsername);
                System.out.println("DB Password (encrypted): " + dbPassword);
                System.out.println("Input Password: " + customerModel.getCustomer_Password());

                // Try multiple authentication methods (fallback strategy)
                boolean authSuccess = false;
                
                // Method 1: Try standard decryption first
                try {
                    String decryptedDbPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
                    System.out.println("Decryption successful");
                    System.out.println("Decrypted DB Password: " + decryptedDbPassword);
                    
                    // Check if passwords match
                    if (Objects.equals(decryptedDbPassword, customerModel.getCustomer_Password())) {
                        authSuccess = true;
                        System.out.println("Password validation successful via decryption");
                    }
                } catch (RuntimeException ex) {
                    // Decryption failed, try alternative methods
                    System.err.println("Decryption error: " + ex.getMessage());
                    
                    // Method 2: Try direct password comparison as fallback
                    if (Objects.equals(dbPassword, customerModel.getCustomer_Password())) {
                        authSuccess = true;
                        System.out.println("Password validation successful via direct comparison");
                    } else {
                        System.out.println("Direct password comparison also failed");
                    }
                }
                
                if (authSuccess) {
                    CustomerModel loggedInCustomer = new CustomerModel();
                    loggedInCustomer.setCustomerID(rs.getInt("CustomerID"));
                    loggedInCustomer.setCustomer_Username(dbUsername);
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

    private boolean validatePasswordForCustomer(ResultSet rs, CustomerModel customerModel) throws SQLException {
        String dbUsername = rs.getString("Customer_Username");
        String dbPassword = rs.getString("Customer_Password");

        // Try multiple authentication methods
        // Method 1: Try standard decryption
        try {
            String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
            return Objects.equals(customerModel.getCustomer_Password(), decryptedPassword);
        } catch (RuntimeException ex) {
            System.err.println("Decryption failed in validatePasswordForCustomer: " + ex.getMessage());
            
            // Method 2: Try direct comparison as fallback
            return Objects.equals(dbPassword, customerModel.getCustomer_Password());
        }
    }

    private boolean validatePasswordForAdmin(ResultSet rs, AdminModel adminModel) throws SQLException {
        String dbUsername = rs.getString("Admin_Username");
        String dbPassword = rs.getString("Admin_Password");

        // Plain comparison for admin
        return Objects.equals(dbUsername, adminModel.getAdmin_Username())
            && Objects.equals(dbPassword, adminModel.getAdmin_Password());
    }
}