package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.config.DbConfig;
import com.nepestate.model.CustomerModel;

public class CustomerService {

    private Connection dbConn;
    private boolean isConnectionError = false;
    private String errorMessage = null;

    public CustomerService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public List<CustomerModel> getAllCustomers() {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<CustomerModel> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                CustomerModel customer = mapResultSetToCustomerModel(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }
    
    private CustomerModel mapResultSetToCustomerModel(ResultSet rs) throws SQLException {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerID(rs.getInt("CustomerID"));
        customer.setCustomer_FirstName(rs.getString("Customer_FirstName"));
        customer.setCustomer_LastName(rs.getString("Customer_LastName"));
        customer.setCustomer_Username(rs.getString("Customer_Username"));
        customer.setCustomer_EmailAddress(rs.getString("Customer_EmailAddress"));
        customer.setCustomer_ProfilePicture(rs.getString("Customer_ProfilePicture"));
        customer.setCustomer_DoB(rs.getString("Customer_DoB"));
        customer.setCustomer_PhoneNumber(rs.getString("Customer_PhoneNumber"));
        customer.setCustomer_Description(rs.getString("Customer_Description"));
        return customer;
    }
    
    public CustomerModel getCustomerById(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return null;	
        }

        String query = "SELECT * FROM customers WHERE CustomerID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToCustomerModel(rs);
            }
        } catch (SQLException e) {
            System.out.println("Database error retrieving customer: "+ e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean updateCustomer(CustomerModel customerModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        String query = "UPDATE Customers SET Customer_Username = ?, Customer_EmailAddress = ?, " +
                "Customer_DoB = ?, Customer_PhoneNumber = ?, Customer_Description = ? WHERE CustomerID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, customerModel.getCustomer_Username());
            stmt.setString(2, customerModel.getCustomer_EmailAddress());
            stmt.setString(3, customerModel.getCustomer_DoB());
            stmt.setString(4, customerModel.getCustomer_PhoneNumber());
            stmt.setString(5, customerModel.getCustomer_Description());
            stmt.setInt(6, customerModel.getCustomerID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updatePassword(int customerId, String newPassword) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        String query = "UPDATE Customers SET Customer_Password = ? WHERE CustomerID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, customerId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateProfilePicture(int customerId, String profilePictureName) {
        if (isConnectionError) {  
            System.out.println("Database connection error!");
            return false;
        }

        String query = "UPDATE Customers SET Customer_ProfilePicture = ? WHERE CustomerID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {  
            stmt.setString(1, profilePictureName);
            stmt.setInt(2, customerId);

            int rowsAffected = stmt.executeUpdate();  
            return rowsAffected > 0;  
        } catch (SQLException e) {
            System.out.println("SQL Exception updating profile picture: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean saveInterestedCustomer(CustomerModel customer, int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }

        String query = "INSERT INTO customers_buyers (CustomerID, Customer_FirstName, Customer_Username, Customer_PhoneNumber, Customer_ProfilePicture, PropertyID) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(2, customer.getCustomer_FirstName());
            stmt.setString(3, customer.getCustomer_Username());
            stmt.setString(4, customer.getCustomer_PhoneNumber());
            stmt.setString(5, customer.getCustomer_ProfilePicture());
            stmt.setInt(6, propertyId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception saving interested customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<CustomerModel> getAllInterestedCustomers() {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        String query = "SELECT * FROM customers_buyers";
        List<CustomerModel> interestedCustomers = new ArrayList<>();

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomer_FirstName(rs.getString("Customer_FirstName"));
                customer.setCustomer_Username(rs.getString("Customer_Username"));
                customer.setCustomer_PhoneNumber(rs.getString("Customer_PhoneNumber"));
                customer.setCustomer_ProfilePicture(rs.getString("Customer_ProfilePicture"));
                interestedCustomers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception retrieving interested customers: " + e.getMessage());
            e.printStackTrace();
        }

        return interestedCustomers;
    }
    
    public boolean deleteInterestedCustomer(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }

        String query = "DELETE FROM customers_buyers WHERE CustomerID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception deleting interested customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteCustomer(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }

        String query = "DELETE FROM role_customer WHERE CustomerID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception deleting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public boolean saveInterestedCustomer(int customerId, int propertyId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbConfig.getDbConnection();

            String sql = "INSERT INTO customers_buyers (Customer_ID, Property_ID, Purchase_Date) VALUES (?, ?, NOW())";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, propertyId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean assignRoleToCustomer(int customerId, int roleId) {
        if (isConnectionError || dbConn == null) {
            System.err.println("Database connection error!");
            return false;
        }
        
        try {
            String query = "INSERT INTO role_customer (RoleID, CustomerID) VALUES (?, ?)";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            pstmt.setInt(2, customerId);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception assigning role to customer: " + e.getMessage());
            e.printStackTrace();
            errorMessage = "Failed to assign role to customer: " + e.getMessage();
            return false;
        }
    }
    /**
     * Assigns a role to a customer
     * 
     * @param customerId the ID of the customer
     * @param roleId the ID of the role to assign
     * @return true if the role was successfully assigned, false otherwise
     */
    
    
    /**
     * Gets the last inserted customer ID
     * 
     * @return the last inserted customer ID, or -1 if an error occurs
     */
    public int getLastInsertedCustomerId() {
        if (isConnectionError || dbConn == null) {
            System.err.println("Database connection error!");
            return -1;
        }
        
        try {
            String query = "SELECT MAX(CustomerID) as LastID FROM customers";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("LastID");
            }
            
            return -1;
        } catch (SQLException e) {
            System.err.println("SQL Exception getting last customer ID: " + e.getMessage());
            e.printStackTrace();
            errorMessage = "Failed to get last customer ID: " + e.getMessage();
            return -1;
        }
    }
    /**
     * Gets the role ID for a specific customer
     * 
     * @param customerId the ID of the customer
     * @return the role ID associated with the customer, or -1 if not found
     */
    public int getRoleIdByCustomerId(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return -1;
        }
        
        try {
            String query = "SELECT RoleID FROM role_customer WHERE CustomerID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, customerId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int roleId = rs.getInt("RoleID");
                System.out.println("Found role ID " + roleId + " for customer ID " + customerId);
                return roleId;
            } else {
                System.out.println("No role found for customer ID: " + customerId);
                return -1;
            }
            
        } catch (SQLException e) {
            System.out.println("SQL Exception getting role ID by customer ID: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    public int getRoleIdByAdminId(int adminId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return -1;
        }
        
        try {
            // Option 1: If you have a role_admin table
            String query = "SELECT RoleID FROM role_admin WHERE AdminID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, adminId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int roleId = rs.getInt("RoleID");
                System.out.println("Found role ID " + roleId + " for admin ID " + adminId);
                return roleId;
            } else {
                System.out.println("No role found for admin ID: " + adminId);
                return -1;
            }
            
        } catch (SQLException e) {
            System.out.println("SQL Exception getting role ID by admin ID: " + e.getMessage());
            e.printStackTrace();
            
            // Option 2: If there's no role_admin table, you might want to return a default admin role ID
            // You could query the roles table to get the admin role ID by role type
            try {
                String fallbackQuery = "SELECT RoleID FROM roles WHERE RoleType = 'Admin' LIMIT 1";
                PreparedStatement fallbackStmt = dbConn.prepareStatement(fallbackQuery);
                ResultSet fallbackRs = fallbackStmt.executeQuery();
                
                if (fallbackRs.next()) {
                    int roleId = fallbackRs.getInt("RoleID");
                    System.out.println("Using default admin role ID: " + roleId);
                    return roleId;
                }
            } catch (SQLException ex) {
                System.out.println("Fallback query also failed: " + ex.getMessage());
            }
            
            return -1;
        }
    }
    
}
