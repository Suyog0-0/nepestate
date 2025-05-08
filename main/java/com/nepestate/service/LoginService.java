// src/com/nepestate/service/LoginService.java
package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.nepestate.config.DbConfig;
import com.nepestate.model.AdminModel;
import com.nepestate.model.CustomerModel;
import com.nepestate.util.PasswordUtil;

public class LoginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public Boolean loginAdmin(AdminModel adminModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        String query = "SELECT Admin_Username, Admin_Password FROM admins WHERE Admin_Username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, adminModel.getAdmin_Username());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return validatePasswordForAdmin(rs, adminModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return false;
    }

    public Boolean loginUser(CustomerModel customerModel) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }
        String query = "SELECT Customer_Username, Customer_Password FROM customers WHERE Customer_Username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, customerModel.getCustomer_Username());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return validatePasswordForCustomer(rs, customerModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return false;
    }

    public CustomerModel loginCustomer(CustomerModel customerModel) {
        if (isConnectionError) {
            return null;
        }
        String query = "SELECT CustomerID, Customer_Username, Customer_Password FROM customers WHERE Customer_Username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, customerModel.getCustomer_Username());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbUsername = rs.getString("Customer_Username");
                String dbPassword = rs.getString("Customer_Password");
                boolean authSuccess = false;
                try {
                    String decrypted = PasswordUtil.decrypt(dbPassword, dbUsername);
                    if (Objects.equals(decrypted, customerModel.getCustomer_Password())) {
                        authSuccess = true;
                    }
                } catch (RuntimeException ex) {
                    if (Objects.equals(dbPassword, customerModel.getCustomer_Password())) {
                        authSuccess = true;
                    }
                }
                if (authSuccess) {
                    CustomerModel logged = new CustomerModel();
                    logged.setCustomerID(rs.getInt("CustomerID"));
                    logged.setCustomer_Username(dbUsername);
                    return logged;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean validatePasswordForCustomer(ResultSet rs, CustomerModel m) throws SQLException {
        String dbUser = rs.getString("Customer_Username");
        String dbPass = rs.getString("Customer_Password");
        try {
            return Objects.equals(PasswordUtil.decrypt(dbPass, dbUser), m.getCustomer_Password());
        } catch (RuntimeException ex) {
            return Objects.equals(dbPass, m.getCustomer_Password());
        }
    }

    private boolean validatePasswordForAdmin(ResultSet rs, AdminModel m) throws SQLException {
        String dbUser = rs.getString("Admin_Username");
        String dbPass = rs.getString("Admin_Password");
        return Objects.equals(dbUser, m.getAdmin_Username())
            && Objects.equals(dbPass, m.getAdmin_Password());
    }
}
