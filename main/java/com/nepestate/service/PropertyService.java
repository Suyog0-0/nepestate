package com.nepestate.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.nepestate.model.PropertyModel;
import com.nepestate.config.DbConfig;

public class PropertyService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public PropertyService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    // Adds a new property to the database
    public boolean addProperty(PropertyModel propertyModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
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
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Retrieves a property by its ID
    public PropertyModel getPropertyById(int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return null;
        }
        String query = "SELECT * FROM property WHERE PropertyID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
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
                try {
                    property.setCustomerID(rs.getInt("CustomerID"));
                } catch (SQLException e) {
                    property.setCustomerID(0);
                }
                return property;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Updates an existing property
    public boolean updateProperty(PropertyModel propertyModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }
        String query = "UPDATE property SET Property_Title = ?, Property_Type = ?, " +
                "Property_Price = ?, Property_Area = ?, Property_Address = ?, " +
                "Property_City = ?, Property_Status = ?, Property_Description = ?, " +
                "Property_Amentities = ?, Property_DateAdded = ?, Property_Photos = ? " +
                "WHERE PropertyID = ?";
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
            stmt.setDate(10, new java.sql.Date(propertyModel.getProperty_DateAdded().getTime()));
            stmt.setString(11, propertyModel.getProperty_Photos());
            stmt.setInt(12, propertyModel.getPropertyID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Deletes a property
    public boolean deleteProperty(int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }
        String query = "DELETE FROM property WHERE PropertyID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, propertyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean deletePropertybyId(int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        
        String query = "DELETE FROM role_property WHERE PropertyID = ?";
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

    // Gets all properties
    public List<PropertyModel> getAllProperties() {
        List<PropertyModel> properties = new ArrayList<>();
        String sql = "SELECT * FROM property";
        try (PreparedStatement ps = dbConn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                properties.add(mapResultSetToPropertyModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Search bar properties
    public List<PropertyModel> searchBarProperties(String searchQuery) {
        List<PropertyModel> properties = new ArrayList<>();
        String sql = "SELECT * FROM property WHERE " +
                "LOWER(Property_Title) LIKE ? OR " +
                "LOWER(Property_Description) LIKE ? OR " +
                "LOWER(Property_Address) LIKE ? OR " +
                "LOWER(Property_City) LIKE ? OR " +
                "LOWER(Property_Type) LIKE ?";
        try (PreparedStatement ps = dbConn.prepareStatement(sql)) {
            String likeParam = "%" + searchQuery.toLowerCase().trim() + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, likeParam);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    properties.add(mapResultSetToPropertyModel(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Helper method to map ResultSet to PropertyModel
    private PropertyModel mapResultSetToPropertyModel(ResultSet rs) throws SQLException {
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
        if (hasColumn(rs, "CustomerID")) {
            property.setCustomerID(rs.getInt("CustomerID"));
        }
        return property;
    }

    // Check if a column exists in ResultSet
    private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
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
    
    
    public List<PropertyModel> getPropertyByCustomerId(int customerId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> properties = new ArrayList<>();
        
        System.out.println("Fetching properties for customer ID: " + customerId);

        // Modified query to get properties based on role associations
        String query = """
            SELECT DISTINCT p.* 
            FROM property p 
            INNER JOIN role_property rp ON p.PropertyID = rp.PropertyID 
            INNER JOIN role_customer rc ON rp.RoleID = rc.RoleID 
            WHERE rc.CustomerID = ? 
            ORDER BY p.Property_DateAdded DESC
        """;

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PropertyModel property = mapResultSetToPropertyModel(rs);
                    properties.add(property);
                }
                System.out.println("Customer " + customerId + ": Fetched " + properties.size() + " properties");
            }
            
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while fetching customer properties: " + e.getMessage());
            e.printStackTrace();
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


    
    /**
     * Get properties filtered by category/type
     * 
     * @param category The category of properties ("Housing", "Apartment", "House")
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
    public List<PropertyModel> advancedSearch(String location, String category, String priceSort) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        List<PropertyModel> propertyList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM property WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        // Add filter conditions
        
        if (location != null && !location.isEmpty()) {
            sql.append(" AND LOWER(Property_City) LIKE ?");
            params.add("%" + location.toLowerCase() + "%");
        }
        
        if (category != null && !category.isEmpty()) {
            sql.append(" AND Property_Type = ?");
            params.add(category);
        }
        
        // Add sorting
        if (priceSort != null && !priceSort.isEmpty()) {
            if (priceSort.equalsIgnoreCase("asc")) {
                sql.append(" ORDER BY Property_Price ASC");
            } else if (priceSort.equalsIgnoreCase("desc")) {
                sql.append(" ORDER BY Property_Price DESC");
            }
        }
        
        System.out.println("Advanced search SQL: " + sql.toString());
        System.out.println("Parameters: " + params);
        
        try (PreparedStatement stmt = dbConn.prepareStatement(sql.toString())) {
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Float) {
                    stmt.setFloat(i + 1, (Float) param);
                }
            }
            
            // Execute query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PropertyModel property = mapResultSetToPropertyModel(rs);
                    propertyList.add(property);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error in advancedSearch: " + e.getMessage());
        }
        
        return propertyList;
    }
    public int addPropertyAndGetId(PropertyModel property) {
    	 
        try  {
            String query = "INSERT INTO property (property_Title, property_Type, property_Price, property_Area, "
                    + "property_Address, property_City, property_Description, property_Status, property_Amentities, "
                    + "property_DateAdded, property_Photos) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, property.getProperty_Title());
            pstmt.setString(2, property.getProperty_Type());
            pstmt.setFloat(3, property.getProperty_Price());
            pstmt.setFloat(4, property.getProperty_Area());
            pstmt.setString(5, property.getProperty_Address());
            pstmt.setString(6, property.getProperty_City());
            pstmt.setString(7, property.getProperty_Description());
            pstmt.setString(8, property.getProperty_Status());
            pstmt.setString(9, property.getProperty_Amentities());
            pstmt.setDate(10, new java.sql.Date(property.getProperty_DateAdded().getTime()));
            pstmt.setString(11, property.getProperty_Photos());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                // Get the generated ID
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
            
            return -1; // Error occurred
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
}