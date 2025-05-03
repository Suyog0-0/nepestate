// src/com/nepestate/service/PropertyService.java
package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.nepestate.model.PropertyModel;
import com.nepestate.config.DbConfig;

public class PropertyService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor initializes the database connection.
     * Sets the connection error flag if the connection fails.
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
     * Adds a new property to the database.
     *
     * @param propertyModel The property details to be added.
     * @return The ID of the newly added property, or -1 if an error occurred.
     */
    public int addProperty(PropertyModel propertyModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return -1;
        }

        String query = "INSERT INTO property (Property_Title, Property_Type, Property_Price, Property_Area, Property_Address, Property_City, Property_Status, Property_Description, Property_Amentities, Property_DateAdded, Property_Photos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            
            if (affectedRows == 0) {
                System.out.println("Creating property failed, no rows affected.");
                return -1;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int propertyId = generatedKeys.getInt(1);
                    System.out.println("Property added successfully with ID: " + propertyId);
                    return propertyId;
                } else {
                    System.out.println("Creating property failed, no ID obtained.");
                    return -1;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Retrieves a property by its ID.
     *
     * @param propertyId The ID of the property to retrieve.
     * @return The PropertyModel if found, null otherwise.
     */
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
                return mapResultSetToPropertyModel(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updates an existing property in the database.
     *
     * @param propertyModel The property details to be updated.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateProperty(PropertyModel propertyModel) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        String query = "UPDATE property SET Property_Title = ?, Property_Type = ?, Property_Price = ?, Property_Area = ?, Property_Address = ?, Property_City = ?, Property_Municipality = ?, Property_Ward = ?, Property_Status = ?, Property_Description = ?, Property_Amentities = ?, Property_DateAdded = ?, Property_Photos = ? WHERE PropertyID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, propertyModel.getProperty_Title());
            stmt.setString(2, propertyModel.getProperty_Type());
            stmt.setFloat(3, propertyModel.getProperty_Price());
            stmt.setFloat(4, propertyModel.getProperty_Area());
            stmt.setString(5, propertyModel.getProperty_Address());
            stmt.setString(6, propertyModel.getProperty_City());
            stmt.setString(7, propertyModel.getProperty_Municipality());
            stmt.setInt(8, propertyModel.getProperty_Ward());
            stmt.setString(9, propertyModel.getProperty_Status());
            stmt.setString(10, propertyModel.getProperty_Description());
            stmt.setString(11, propertyModel.getProperty_Amentities());
            
            java.sql.Date sqlDate = new java.sql.Date(propertyModel.getProperty_DateAdded().getTime());
            stmt.setDate(12, sqlDate);
            
            stmt.setString(13, propertyModel.getProperty_Photos());
            stmt.setInt(14, propertyModel.getPropertyID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a property from the database.
     *
     * @param propertyId The ID of the property to delete.
     * @return true if deletion was successful, false otherwise.
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
     * Gets all properties from the database.
     *
     * @return List of PropertyModel objects.
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
     * Helper method to map a ResultSet to a PropertyModel.
     *
     * @param rs The ResultSet containing property data.
     * @return A PropertyModel object populated with data from the ResultSet.
     * @throws SQLException If a database access error occurs.
     */
    private PropertyModel mapResultSetToPropertyModel(ResultSet rs) throws SQLException {
        PropertyModel property = new PropertyModel();
        property.setPropertyID(rs.getInt("PropertyID"));
        property.setProperty_Title(rs.getString("Property_Title"));
        property.setProperty_Type(rs.getString("Property_Type"));
        property.setProperty_Price(rs.getFloat("Property_Price"));
        property.setProperty_Area(rs.getFloat("Property_Area"));
        property.setProperty_Address(rs.getString("Property_Address"));
        property.setProperty_City(rs.getString("Property_City"));
        property.setProperty_Municipality(rs.getString("Property_Municipality"));
        property.setProperty_Ward(rs.getInt("Property_Ward"));
        property.setProperty_Status(rs.getString("Property_Status"));
        property.setProperty_Description(rs.getString("Property_Description"));
        property.setProperty_Amentities(rs.getString("Property_Amentities"));
        property.setProperty_DateAdded(rs.getDate("Property_DateAdded"));
        property.setProperty_Photos(rs.getString("Property_Photos"));
        return property;
    }

    /**
     * Search properties based on various criteria.
     *
     * @param type Property type (optional).
     * @param city Property city (optional).
     * @param minPrice Minimum price (optional).
     * @param maxPrice Maximum price (optional).
     * @param status Property status (optional).
     * @return List of properties matching the search criteria.
     */
    public List<PropertyModel> searchProperties(String type, String city, Float minPrice, Float maxPrice, String status) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> properties = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM property WHERE 1=1");
        List<Object> params = new ArrayList<>();

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
}