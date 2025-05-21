package com.nepestate.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nepestate.config.DbConfig;
import com.nepestate.model.PropertyModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReportGenerationService {
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection.
	 */
	public ReportGenerationService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	 		public void generateAdminPropertyStatistics(Map<String, Object> reportData) {
	 		PropertyService propertyService = new PropertyService();
	        // Get all properties
	        List<PropertyModel> allProperties = propertyService.getAllProperties();
	        
	        // 1. Total Properties Listed
	        reportData.put("totalProperties", allProperties.size());
	        
	        // 2. Properties by Status (Available, Sold, etc.)
	        Map<String, Integer> statusCounts = new HashMap<>();
	        
	        // 3. Properties by Type/Category
	        Map<String, Integer> typeCounts = new HashMap<>();
	        
	        // 4. Properties by City
	        Map<String, Integer> cityCounts = new HashMap<>();
	        
	        // 5. Total Value of all Properties
	        float totalPropertyValue = 0.0f;
	        
	        // Iterate through properties and aggregate data
	        for (PropertyModel property : allProperties) {
	            // Status counts
	            String status = property.getProperty_Status();
	            statusCounts.put(status, statusCounts.getOrDefault(status, 0) + 1);
	            
	            // Type counts
	            String type = property.getProperty_Type();
	            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
	            
	            // City counts
	            String city = property.getProperty_City();
	            cityCounts.put(city, cityCounts.getOrDefault(city, 0) + 1);
	            
	            // Total value
	            totalPropertyValue += property.getProperty_Price();
	        }
	        
	        // Add all data to report
	        reportData.put("statusCounts", statusCounts);
	        reportData.put("typeCounts", typeCounts);
	        reportData.put("cityCounts", cityCounts);
	        reportData.put("totalPropertyValue", totalPropertyValue);
	 		}

	    
	    /**
	     * Generate property statistics for customer-specific reports
	     * 
	     * @param reportData Map to store the report data
	     * @param customerId The ID of the customer
	     */
	    	public void generateCustomerPropertyStatistics(Map<String, Object> reportData, int customerId) throws SQLException {
	        PropertyService propertyService = new PropertyService();
	    		// Get customer properties
	        List<PropertyModel> customerProperties = propertyService.getPropertyByCustomerId(customerId);
	        
	        // 1. Total Properties Listed by this customer
	        reportData.put("totalProperties", customerProperties.size());
	        
	        // 2. Properties by Status (Available, Sold, etc.)
	        Map<String, Integer> statusCounts = new HashMap<>();
	        
	        // 3. Properties by Type/Category
	        Map<String, Integer> typeCounts = new HashMap<>();
	        
	        // 4. Properties by City
	        Map<String, Integer> cityCounts = new HashMap<>();
	        
	        // 5. Total Value of Customer's Properties
	        float totalPropertyValue = 0.0f;
	        
	        // Iterate through properties and aggregate data
	        for (PropertyModel property : customerProperties) {
	            // Status counts
	            String status = property.getProperty_Status();
	            statusCounts.put(status, statusCounts.getOrDefault(status, 0) + 1);
	            
	            // Type counts
	            String type = property.getProperty_Type();
	            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
	            
	            // City counts
	            String city = property.getProperty_City();
	            cityCounts.put(city, cityCounts.getOrDefault(city, 0) + 1);
	            
	            // Total value
	            totalPropertyValue += property.getProperty_Price();
	        }
	        
	        // Add all data to report
	        reportData.put("statusCounts", statusCounts);
	        reportData.put("typeCounts", typeCounts);
	        reportData.put("cityCounts", cityCounts);
	        reportData.put("totalPropertyValue", totalPropertyValue);
}
}
