package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.config.DbConfig;
import com.nepestate.model.PropertyModel;

public class FavoritesService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public FavoritesService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Adds a property to user's favorites
     * @param customerId The ID of the customer
     * @param propertyId The ID of the property
     * @return true if successfully added, false otherwise
     */
    public boolean addToFavorites(int customerId, int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        // First check if the favorite already exists
        if (isFavoriteExists(customerId, propertyId)) {
            System.out.println("Property is already in favorites for this customer");
            return false;
        }

        // FIXED: Using correct table structure - RoleID and FavoritesID only
        // Since your favorites table only has RoleID and FavoritesID columns,
        // we need to find the RoleID for this customer first
        int roleId = getRoleIdForCustomer(customerId);
        if (roleId == -1) {
            System.out.println("Could not find role for customer: " + customerId);
            return false;
        }

        String query = "INSERT INTO favorites (RoleID, FavoritesID) VALUES (?, ?)";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            stmt.setInt(2, propertyId); // Using PropertyID as FavoritesID
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception adding to favorites: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Helper method to get RoleID for a customer
     */
    private int getRoleIdForCustomer(int customerId) {
        String query = "SELECT RoleID FROM role_customer WHERE CustomerID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("RoleID");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception getting role for customer: " + e.getMessage());
            e.printStackTrace();
        }
        
        return -1; // Return -1 if not found
    }

    /**
     * Removes a property from user's favorites
     * @param customerId The ID of the customer
     * @param propertyId The ID of the property
     * @return true if successfully removed, false otherwise
     */
    public boolean removeFromFavorites(int customerId, int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        int roleId = getRoleIdForCustomer(customerId);
        if (roleId == -1) {
            System.out.println("Could not find role for customer: " + customerId);
            return false;
        }

        String query = "DELETE FROM favorites WHERE RoleID = ? AND FavoritesID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            stmt.setInt(2, propertyId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception removing from favorites: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a property is already in user's favorites
     * @param customerId The ID of the customer
     * @param propertyId The ID of the property
     * @return true if exists, false otherwise
     */
    public boolean isFavoriteExists(int customerId, int propertyId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return false;
        }

        int roleId = getRoleIdForCustomer(customerId);
        if (roleId == -1) {
            return false;
        }

        String query = "SELECT COUNT(*) FROM favorites WHERE RoleID = ? AND FavoritesID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            stmt.setInt(2, propertyId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception checking favorites: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }

    /**
     * Gets all favorite properties for a specific customer
     * @param customerId The ID of the customer
     * @return List of PropertyModel objects
     */
    public List<PropertyModel> getFavoriteProperties(int customerId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }

        List<PropertyModel> favoriteProperties = new ArrayList<>();
        
        int roleId = getRoleIdForCustomer(customerId);
        if (roleId == -1) {
            System.out.println("Could not find role for customer: " + customerId);
            return favoriteProperties;
        }
        
        String query = "SELECT p.* " +
                      "FROM property p " +
                      "INNER JOIN favorites f ON p.PropertyID = f.FavoritesID " +
                      "WHERE f.RoleID = ? " +
                      "ORDER BY p.Property_DateAdded DESC";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PropertyModel property = mapResultSetToPropertyModel(rs);
                favoriteProperties.add(property);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception getting favorite properties: " + e.getMessage());
            e.printStackTrace();
        }
        
        return favoriteProperties;
    }

    /**
     * Gets the count of favorite properties for a customer
     * @param customerId The ID of the customer
     * @return count of favorite properties
     */
    public int getFavoritesCount(int customerId) {
        if (isConnectionError) {
            System.out.println("Database connection error!");
            return 0;
        }

        int roleId = getRoleIdForCustomer(customerId);
        if (roleId == -1) {
            return 0;
        }

        String query = "SELECT COUNT(*) FROM favorites WHERE RoleID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception getting favorites count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }

    /**
     * Helper method to map ResultSet to PropertyModel
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
        property.setProperty_Status(rs.getString("Property_Status"));
        property.setProperty_Description(rs.getString("Property_Description"));
        property.setProperty_Amentities(rs.getString("Property_Amentities"));
        property.setProperty_DateAdded(rs.getDate("Property_DateAdded"));
        property.setProperty_Photos(rs.getString("Property_Photos"));
        
        // Set CustomerID if available
        try {
            property.setCustomerID(rs.getInt("CustomerID"));
        } catch (SQLException e) {
            property.setCustomerID(0);
        }
        
        return property;
    }
}