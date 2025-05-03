package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.model.CustomerModel;
import com.nepestate.model.PropertyModel;

public class CustomerService {

	private Connection dbConn;
	private boolean isConnectionError = false;
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
                PropertyModel customer = mapResultSetToCustomerModel(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }
	
	private PropertyModel mapResultSetToCustomerModel(ResultSet rs) throws SQLException {
        PropertyModel property = new PropertyModel();
        property.setPropertyID(rs.getInt("PropertyID"));
        property.setProperty_Title(rs.getString("Property_Title"));
        property.setProperty_Type(rs.getString("Property_Type"));
        property.setProperty_Price(rs.getFloat("Property_Price"));
        property.setProperty_Area(rs.getFloat("Property_Area"));
        property.setProperty_Address(rs.getString("Property_Address"));
        property.setProperty_City(rs.getString("Property_City"));
        property.setProperty_Status(rs.getString("Property_Status"));
        property.setProperty_Description(rs.getString("Property_Description"));
        property.setProperty_Amentities(rs.getString("Property_Amentities"));
        property.setProperty_DateAdded(rs.getDate("Property_DateAdded"));
        property.setProperty_Photos(rs.getString("Property_Photos"));
        return property;
    }
}
