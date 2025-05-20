package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.config.DbConfig;
import com.nepestate.model.CustomerModel;
import com.nepestate.model.PropertyModel;

public class CustomerService {

	private Connection dbConn;
	private boolean isConnectionError = false;
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
//        customer.setCustomer_Password(rs.getString("Customer_Password"));
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
	 
	 /**
	  * Updates profile picture for a customer
	  * 
	  * @param customerId ID of customer to update
	  * @param profilePictureName Name of the profile picture file
	  * @return true if update successful, false otherwise
	  */
	 public boolean updateProfilePicture(int customerId, String profilePictureName) {
	        if (isConnectionError) {  // Connection check
	            System.out.println("Database connection error!");
	            return false;
	        }

	        String query = "UPDATE Customers SET Customer_ProfilePicture = ? WHERE CustomerID = ?";
	        
	        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {  // Prepared statement
	            stmt.setString(1, profilePictureName);
	            stmt.setInt(2, customerId);

	            int rowsAffected = stmt.executeUpdate();  // Execute update
	            return rowsAffected > 0;  // Success check
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

}