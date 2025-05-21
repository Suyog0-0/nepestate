package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.config.DbConfig;


/**
 * Service class for handling role-property relationships
 */
public class RolePropertyService {
    
    private Connection dbConn;
    private boolean isConnectionError = false;
    
    /**
     * Constructor initializes database connection
     */
    public RolePropertyService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Adds a new role-property relationship to the database
     * 
     * @param roleId the ID of the role
     * @param propertyId the ID of the property
     * @return true if the relationship was successfully added, false otherwise
     */
    public boolean addRoleProperty(int roleId, int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }
        
        try {
            String query = "INSERT INTO role_property (RoleID, PropertyID) VALUES (?, ?)";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            pstmt.setInt(2, propertyId);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception adding role property: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Removes a role-property relationship from the database
     * 
     * @param roleId the ID of the role
     * @param propertyId the ID of the property
     * @return true if the relationship was successfully removed, false otherwise
     */
    public boolean removeRoleProperty(int roleId, int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }
        
        try {
            String query = "DELETE FROM role_property WHERE RoleID = ? AND PropertyID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            pstmt.setInt(2, propertyId);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception removing role property: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Gets all properties associated with a specific role
     * 
     * @param roleId the ID of the role
     * @return a list of property IDs associated with the role
     */
    public List<Integer> getPropertiesByRoleId(int roleId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        List<Integer> propertyIds = new ArrayList<>();
        
        try {
            String query = "SELECT PropertyID FROM role_property WHERE RoleID = ?";
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                propertyIds.add(rs.getInt("PropertyID"));
            }
            
            return propertyIds;
        } catch (SQLException e) {
            System.out.println("SQL Exception getting properties by role ID: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Gets all roles associated with a specific property
     * 
     * @param propertyId the ID of the property
     * @return a list of role IDs associated with the property
     */
    public List<Integer> getRolesByPropertyId(int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return new ArrayList<>();
        }
        
        List<Integer> roleIds = new ArrayList<>();
        
        try {
            String query = "SELECT RoleID FROM role_property WHERE PropertyID = ?";
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, propertyId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                roleIds.add(rs.getInt("RoleID"));
            }
            
            return roleIds;
        } catch (SQLException e) {
            System.out.println("SQL Exception getting roles by property ID: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Gets all role-property relationships
     * 
     * @return a list of RolePropertyModel objects containing all relationships
     */
//    public List<RolePropertyModel> getAllRoleProperties() {
//        if (isConnectionError || dbConn == null) {
//            System.out.println("Database connection error!");
//            return new ArrayList<>();
//        }
//        
//        List<RolePropertyModel> roleProperties = new ArrayList<>();
//        
//        try {
//            String query = "SELECT * FROM role_property";
//            PreparedStatement pstmt = dbConn.prepareStatement(query);
//            
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                RolePropertyModel model = new RolePropertyModel();
//                model.setRoleID(rs.getInt("RoleID"));
//                model.setPropertyID(rs.getInt("PropertyID"));
//                roleProperties.add(model);
//            }
//            
//            return roleProperties;
//        } catch (SQLException e) {
//            System.out.println("SQL Exception getting all role properties: " + e.getMessage());
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
    
    /**
     * Checks if a role-property relationship exists
     * 
     * @param roleId the ID of the role
     * @param propertyId the ID of the property
     * @return true if the relationship exists, false otherwise
     */
    public boolean relationshipExists(int roleId, int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }
        
        try {
            String query = "SELECT COUNT(*) FROM role_property WHERE RoleID = ? AND PropertyID = ?";
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            pstmt.setInt(2, propertyId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
            return false;
        } catch (SQLException e) {
            System.out.println("SQL Exception checking relationship existence: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Updates a role-property relationship
     * 
     * @param oldRoleId the ID of the original role
     * @param oldPropertyId the ID of the original property
     * @param newRoleId the ID of the new role
     * @param newPropertyId the ID of the new property
     * @return true if the relationship was successfully updated, false otherwise
     */
    public boolean updateRoleProperty(int oldRoleId, int oldPropertyId, int newRoleId, int newPropertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }
        
        try {
            // First check if the relationship exists
            if (!relationshipExists(oldRoleId, oldPropertyId)) {
                return false;
            }
            
            // Then remove the old relationship and add the new one
            String deleteQuery = "DELETE FROM role_property WHERE RoleID = ? AND PropertyID = ?";
            PreparedStatement deleteStmt = dbConn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, oldRoleId);
            deleteStmt.setInt(2, oldPropertyId);
            deleteStmt.executeUpdate();
            
            String insertQuery = "INSERT INTO role_property (RoleID, PropertyID) VALUES (?, ?)";
            PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery);
            insertStmt.setInt(1, newRoleId);
            insertStmt.setInt(2, newPropertyId);
            
            int affectedRows = insertStmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception updating role property: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Removes all properties associated with a role
     * 
     * @param roleId the ID of the role
     * @return true if the relationships were successfully removed, false otherwise
     */
    public boolean removeAllPropertiesByRoleId(int roleId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }
        
        try {
            String query = "DELETE FROM role_property WHERE RoleID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows >= 0; // Return true even if no rows were affected
        } catch (SQLException e) {
            System.out.println("SQL Exception removing all properties by role ID: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Removes all roles associated with a property
     * 
     * @param propertyId the ID of the property
     * @return true if the relationships were successfully removed, false otherwise
     */
    public boolean removeAllRolesByPropertyId(int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return false;
        }
        
        try {
            String query = "DELETE FROM role_property WHERE PropertyID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, propertyId);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows >= 0; // Return true even if no rows were affected
        } catch (SQLException e) {
            System.out.println("SQL Exception removing all roles by property ID: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Counts the number of properties associated with a role
     * 
     * @param roleId the ID of the role
     * @return the number of properties associated with the role
     */
    public int countPropertiesByRoleId(int roleId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }
        
        try {
            String query = "SELECT COUNT(*) FROM role_property WHERE RoleID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, roleId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
            return 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception counting properties by role ID: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Counts the number of roles associated with a property
     * 
     * @param propertyId the ID of the property
     * @return the number of roles associated with the property
     */
    public int countRolesByPropertyId(int propertyId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }
        
        try {
            String query = "SELECT COUNT(*) FROM role_property WHERE PropertyID = ?";
            
            PreparedStatement pstmt = dbConn.prepareStatement(query);
            pstmt.setInt(1, propertyId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
            return 0;
        } catch (SQLException e) {
            System.out.println("SQL Exception counting roles by property ID: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    
    
    /**
     * Close the database connection when service is no longer needed
     */
    public void closeConnection() {
        if (dbConn != null) {
            try {
                dbConn.close();
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}