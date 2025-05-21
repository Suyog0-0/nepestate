package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nepestate.config.DbConfig;

public class DashboardService {
	private Connection dbConn;
    private boolean isConnectionError = false;

    public DashboardService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public int getTotalRevenue() {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }

        String query = "SELECT SUM(CAST(p.Property_Price AS UNSIGNED)) AS TotalRevenue " +
                "FROM customers_buyers cb " +
                "JOIN property p ON cb.PropertyID = p.PropertyID";

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int revenue = rs.getInt("TotalRevenue");
                System.out.println("Total revenue calculated: " + revenue);
                return revenue;
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception getting total revenue: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
    
    public int getTotalRevenuebyCustomer(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }

        String query =  "SELECT SUM(p.Property_Price) AS TotalRevenue " +
                "FROM property p " +
                "WHERE p.PropertyID IN ( " +
                "  SELECT DISTINCT rp.PropertyID " +
                "  FROM role_customer rc " +
                "  JOIN role_property rp ON rc.RoleID = rp.RoleID " +
                "  WHERE rc.CustomerID = ? " +
                ")";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("TotalRevenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getTotalUsers() {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }

        String query = "SELECT COUNT(*) AS TotalUsers FROM customers";

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("TotalUsers");
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception getting total users: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
    
    public int getTotalPropertiesBoughtByCustomer(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }

        // SQL query to get the total number of properties bought by a particular customer
        String query = "SELECT COUNT(DISTINCT cb.PropertyID) AS PropertiesBought " +
                       "FROM customers_buyers cb " +
                       "WHERE cb.CustomerID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId); // Set customerId parameter in the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PropertiesBought");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public int getTotalUploadsbyCustomer(int customerId) {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }

        String query = "SELECT COUNT(DISTINCT c.PropertyID) AS PropertiesUploaded" +
                "FROM customers c " +
                "JOIN property p ON c.PropertyID = p.PropertyID " +
                "WHERE c.CustomerID = ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("UploadCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getTotalBuyers() {
        if (isConnectionError || dbConn == null) {
            System.out.println("Database connection error!");
            return 0;
        }

        String query = "SELECT COUNT(*) AS TotalBuyers FROM customers_buyers";

        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("TotalBuyers");
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception getting total buyers: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}
