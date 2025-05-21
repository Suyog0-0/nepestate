package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nepestate.config.DbConfig;


public class FavoriteService {
    
    public boolean addToFavorites(int userId, int propertyId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DbConfig.getDbConnection();
            
            // First check if already in favorites
            ps = conn.prepareStatement("SELECT * FROM favorites WHERE Customer_ID = ? AND Property_ID = ?");
            ps.setInt(1, userId);
            ps.setInt(2, propertyId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Already favorited
                return true;
            }
            
            // Add to favorites
            ps = conn.prepareStatement("INSERT INTO favorites (Customer_ID, Property_ID) VALUES (?, ?)");
            ps.setInt(1, userId);
            ps.setInt(2, propertyId);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {

        }
    }
}