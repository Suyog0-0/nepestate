// src/com/nepestate/dao/ViewPropertySPDAO.java
package com.nepestate.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.nepestate.model.PropertyModel;
import com.nepestate.util.DBConnection;

public class ViewPropertySPDAO {
    
    public List<PropertyModel> getAllProperties() {
        List<PropertyModel> properties = new ArrayList<>();
        String query = "SELECT * FROM property";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                PropertyModel prop = new PropertyModel(
                    rs.getInt("PropertyID"),
                    rs.getString("Property_Title"),
                    rs.getString("Property_Type"),
                    rs.getFloat("Property_Price"),
                    rs.getFloat("Property_Area"),
                    rs.getString("Property_Address"),
                    rs.getString("Property_City"),
                    rs.getString("Property_Municipality"),
                    rs.getInt("Property_Ward"),
                    rs.getString("Property_Status"),
                    rs.getString("Property_Description"),
                    rs.getString("Property_Amentities"),
                    rs.getDate("Property_DateAdded"),
                    rs.getString("Property_Photos")
                );
                properties.add(prop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }
}