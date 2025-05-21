package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nepestate.model.PropertyModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.nepestate.config.DbConfig;

public class PropertyService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection. Sets the connection error
     * flag if the connection fails.
     */
    public PropertyService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Adds a new property to the database
     * 
     * @param propertyModel The property details to be added
     * @return The ID of the newly added property, or -1 if an error occurred
     */
    public Boolean addProperty(PropertyModel propertyModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return null;
        }

     
        String query = "INSERT INTO property (Property_Title, Property_Type, Property_Price, " +
                "Property_Area, Property_Address, Property_City, " +
                "Property_Status, Property_Description, Property_Amentities, " +
                "Property_DateAdded, Property_Photos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = dbConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, propertyModel.getProperty_Title());
            stmt.setString(2, propertyModel.getProperty_Type());
            stmt.setFloat(3, propertyModel.getProperty_Price());
            stmt.setFloat(4, propertyModel.getProperty_Area());
            stmt.setString(5, propertyModel.getProperty_Address());
            stmt.setString(6, propertyModel.getProperty_City());
            stmt.setString(7, propertyModel.getProperty_Status());
            stmt.setString(8, propertyModel.getProperty_Description());
            stmt.setString(9, propertyModel.getProperty_Amentities());
            
          
            java.sql.Date sqlDate = new java.sql.Date(propertyModel.getProperty_DateAdded().getTime());
            stmt.setDate(10, sqlDate);
            
            stmt.setString(11, propertyModel.getProperty_Photos());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; 
            
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return null; 
        }
    }

    /**
     * Retrieves a property by its ID
     * 
     * @param propertyId The ID of the property to retrieve
     * @return The PropertyModel if found, null otherwise
     */
    public PropertyModel getPropertyById(int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return null;
        }

        String query = "SELECT * FROM property WHERE PropertyID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            
            System.out.println("Executing query: " + query + " with propertyId = " + propertyId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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
                
                // Check if CustomerID column exists in the result set
                try {
                    int customerId = rs.getInt("CustomerID");
                    property.setCustomerID(customerId);
                    System.out.println("CustomerID found in result set: " + customerId);
                } catch (SQLException e) {
                    System.out.println("CustomerID column not found in property table: " + e.getMessage());
                    // Set a default customerID or handle the issue accordingly
                    property.setCustomerID(0); // Default value
                }
                
                return property;
            } else {
                System.out.println("No property found with ID: " + propertyId);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred in getPropertyById: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    /**
     * Updates an existing property in the database
     * 
     * @param propertyModel The property details to be updated
     * @return true if update was successful, false otherwise
     */
    public boolean updateProperty(PropertyModel propertyModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        
        String query = "UPDATE property SET Property_Title = ?, Property_Type = ?, " +
                "Property_Price = ?, Property_Area = ?, Property_Address = ?, " +
                "Property_City = ?," +
                "Property_Status = ?, Property_Description = ?, Property_Amentities = ?, " +
                "Property_DateAdded = ?, Property_Photos = ? WHERE PropertyID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, propertyModel.getProperty_Title());
            stmt.setString(2, propertyModel.getProperty_Type());
            stmt.setFloat(3, propertyModel.getProperty_Price());
            stmt.setFloat(4, propertyModel.getProperty_Area());
            stmt.setString(5, propertyModel.getProperty_Address());
            stmt.setString(6, propertyModel.getProperty_City());
            stmt.setString(7, propertyModel.getProperty_Status());
            stmt.setString(8, propertyModel.getProperty_Description());
            stmt.setString(9, propertyModel.getProperty_Amentities());
   
            java.sql.Date sqlDate = new java.sql.Date(propertyModel.getProperty_DateAdded().getTime());
            stmt.setDate(10, sqlDate);
            
            stmt.setString(11, propertyModel.getProperty_Photos());
            stmt.setInt(12, propertyModel.getPropertyID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a property from the database
     * 
     * @param propertyId The ID of the property to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteProperty(int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        
        String query = "DELETE FROM property WHERE PropertyID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets all properties from the database
     * 
     * @return List of PropertyModel objects
     */
    public List<PropertyModel> getAllProperties() {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> properties = new ArrayList<>();
        String query = "SELECT * FROM property";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Helper method to map a ResultSet to a PropertyModel
     * 
     * @param rs The ResultSet containing property data
     * @return A PropertyModel object populated with data from the ResultSet
     * @throws SQLException If a database access error occurs
     */
    private PropertyModel mapResultSetToPropertyModel(ResultSet rs) throws SQLException {
        PropertyModel property = new PropertyModel();
        property.setPropertyID(rs.getInt("PropertyID"));
        property.setProperty_Title(rs.getString("Property_Title"));
        property.setProperty_Type(rs.getString("Property_Type"));
        property.setProperty_Price(rs.getInt("Property_Price"));
        property.setProperty_Area(rs.getFloat("Property_Area"));
        property.setProperty_Address(rs.getString("Property_Address"));
        property.setProperty_City(rs.getString("Property_City"));
        property.setProperty_Status(rs.getString("Property_Status"));
        property.setProperty_Description(rs.getString("Property_Description"));
        property.setProperty_Amentities(rs.getString("Property_Amentities"));
        property.setProperty_DateAdded(rs.getDate("Property_DateAdded"));
        property.setProperty_Photos(rs.getString("Property_Photos"));
        
        // Adding this line to set the CustomerID if it exists in the ResultSet
        if (hasColumn(rs, "CustomerID")) {
            property.setCustomerID(rs.getInt("CustomerID"));
        }
        
        return property;
    }
    
 // Helper method to check if a column exists in the ResultSet
    private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            if (columnName.equalsIgnoreCase(rsmd.getColumnName(i))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Search properties based on various criteria
     * 
     * @param type Property type (optional)
     * @param city Property city (optional)
     * @param minPrice Minimum price (optional)
     * @param maxPrice Maximum price (optional)
     * @param status Property status (optional)
     * @return List of properties matching the search criteria
     */
    public List<PropertyModel> searchProperties(String type, String city, Float minPrice, Float maxPrice, String status) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> properties = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM property WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Build dynamic query based on search parameters
        if (type != null && !type.isEmpty()) {
            queryBuilder.append(" AND Property_Type = ?");
            params.add(type);
        }

        if (city != null && !city.isEmpty()) {
            queryBuilder.append(" AND Property_City = ?");
            params.add(city);
        }

        if (minPrice != null) {
            queryBuilder.append(" AND Property_Price >= ?");
            params.add(minPrice);
        }

        if (maxPrice != null) {
            queryBuilder.append(" AND Property_Price <= ?");
            params.add(maxPrice);
        }

        if (status != null && !status.isEmpty()) {
            queryBuilder.append(" AND Property_Status = ?");
            params.add(status);
        }

        try (PreparedStatement stmt = dbConn.prepareStatement(queryBuilder.toString())) {
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Float) {
                    stmt.setFloat(i + 1, (Float) param);
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }
    
    public List<PropertyModel> getPropertyByCustomer(int customerId) throws SQLException {
        List<PropertyModel> properties = new ArrayList<>();

        String roleType = null;

        String customerRoleQuery = "SELECT r.RoleType " + "FROM role_Customer rc "
                + "JOIN roles r ON rc.RoleID = r.RoleID " + "JOIN Customers c ON rc.CustomerID = c.CustomerID "
                + "WHERE c.CustomerID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(customerRoleQuery)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    roleType = rs.getString("RoleType");
                }
            }
        }

        if (roleType != null) {
            String propertyQuery = "SELECT p.* " + "FROM property p "
                    + "JOIN role_property rp ON p.propertyid = rp.propertyid " + "JOIN roles r ON rp.roleid = r.roleid "
                    + "WHERE r.roletype = ?";

            try (PreparedStatement stmt = dbConn.prepareStatement(propertyQuery)) {
                stmt.setString(1, roleType);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        PropertyModel property = mapResultSetToPropertyModel(rs);
                        properties.add(property);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred while fetching properties: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No role type found for customer ID: " + customerId);
        }

        return properties;
    }
    
    /**
     * Retrieves all properties posted by a specific user
     * 
     * @param userId The ID of the user
     * @return List of PropertyModel objects posted by the user
     */
    public List<PropertyModel> getPropertiesByUserId(int userId) throws SQLException {
        List<PropertyModel> properties = new ArrayList<>();

        String roleType = null;

        String customerRoleQuery = "SELECT r.RoleType " + 
            "FROM role_Customer rc " +
            "JOIN roles r ON rc.RoleID = r.RoleID " + 
            "JOIN Customers c ON rc.CustomerID = c.CustomerID " +
            "WHERE c.CustomerID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(customerRoleQuery)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    roleType = rs.getString("RoleType");
                }
            }
        }

        if (roleType != null) {
            String propertyQuery = "SELECT p.* " + 
                "FROM property p " +
                "JOIN role_property rp ON p.propertyid = rp.propertyid " + 
                "JOIN roles r ON rp.roleid = r.roleid " +
                "WHERE r.roletype = ?";

            try (PreparedStatement stmt = dbConn.prepareStatement(propertyQuery)) {
                stmt.setString(1, roleType);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        PropertyModel property = mapResultSetToPropertyModel(rs);
                        properties.add(property);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception occurred while fetching properties: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No role type found for customer ID: " + userId);
        }

        return properties;
    }
    

    /**
     * Retrieves featured properties from the database
     * 
     * @param limit The maximum number of properties to retrieve
     * @return List of PropertyModel objects
     */
    public List<PropertyModel> getFeaturedProperties(int limit) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> properties = new ArrayList<>();
        String query = "SELECT * FROM property LIMIT ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    properties.add(mapResultSetToPropertyModel(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Retrieves additional properties for the explore section
     * 
     * @param offset The starting point for the results
     * @param limit The maximum number of properties to retrieve
     * @return List of PropertyModel objects
     */
    public List<PropertyModel> getMoreProperties(int offset, int limit) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> properties = new ArrayList<>();
        String query = "SELECT * FROM property LIMIT ?, ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    properties.add(mapResultSetToPropertyModel(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }




    public List<PropertyModel> getAllLocations() {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> locations = new ArrayList<>();
        String query = "SELECT DISTINCT Property_City FROM property";

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PropertyModel location = new PropertyModel();
                location.setProperty_City(rs.getString("Property_City"));
                locations.add(location);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(locations);
        return locations;
    }

    public List<PropertyModel> getPropertiesBySource(String source) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        // Assuming there is a column in the database that identifies the source
        // If not, you might need to join with another table
        List<PropertyModel> properties = new ArrayList<>();
        String query = "SELECT * FROM property WHERE Property_Source = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, source);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        
        return properties;
    }
    
    /**
     * Get properties filtered by category/type
     * 
     * @param category The category of properties ("Housing", "Appartment", "House")
     * @return List of properties of the specified category
     */
    public List<PropertyModel> getPropertiesByCategory(String category) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        List<PropertyModel> properties = new ArrayList<>();
        String query = "SELECT * FROM property WHERE Property_Type = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return properties;
    }
    
    /**
     * Get properties sorted by price (ascending or descending)
     * 
     * @param sortOrder The sort order for price ("asc" for low to high, "desc" for high to low)
     * @return List of properties sorted by price
     */
    public List<PropertyModel> getPropertiesSortedByPrice(String sortOrder) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        List<PropertyModel> properties = new ArrayList<>();
        
        // Validate sortOrder parameter
        if (!sortOrder.equals("asc") && !sortOrder.equals("desc")) {
            sortOrder = "asc"; // Default to ascending order
        }
        
        String query = "SELECT * FROM property ORDER BY Property_Price " + sortOrder;
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        
        return properties;
    }
    
    /**
     * Advanced search with multiple filter criteria combined
     * 
     * @param source The source of properties (optional)
     * @param location The location/city (optional)
     * @param category The property type/category (optional)
     * @param priceSort Sort order for price (optional, "asc" or "desc")
     * @return List of properties matching all specified criteria
     */
    public List<PropertyModel> advancedSearch(String source, String location, String category, String priceSort) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        List<PropertyModel> properties = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM property WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        // Add filter conditions based on provided parameters
        if (source != null && !source.isEmpty()) {
            queryBuilder.append(" AND Property_Source = ?");
            params.add(source);
        }
        
        if (location != null && !location.isEmpty()) {
            queryBuilder.append(" AND Property_City = ?");
            params.add(location);
        }
        
        if (category != null && !category.isEmpty()) {
            queryBuilder.append(" AND Property_Type = ?");
            params.add(category);
        }
        
        // Add sorting by price if specified
        if (priceSort != null && !priceSort.isEmpty()) {
            if (priceSort.equals("Low-High")) {
                queryBuilder.append(" ORDER BY Property_Price ASC");
            } else if (priceSort.equals("High-Low")) {
                queryBuilder.append(" ORDER BY Property_Price DESC");
            }
        }
        
        try (PreparedStatement stmt = dbConn.prepareStatement(queryBuilder.toString())) {
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                properties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        
        return properties;
    }
}