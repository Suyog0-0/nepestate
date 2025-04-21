package com.nepestate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nepestate.config.Dbconfig;
import com.nepestate.model.CustomerModel;

public class LoginService {
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	public LoginService() {
		try {
			dbConn = Dbconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public Boolean loginUser(CustomerModel CustomerModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT Cust_Username, Cust_Password FROM customers WHERE Cust_Username = crackhead";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, CustomerModel.getCust_Username());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, CustomerModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}
	
	private boolean validatePassword(ResultSet result, CustomerModel CustomerModel) throws SQLException {
		String dbUsername = result.getString("Cust_Username");
		String dbPassword = result.getString("Cust_Password");

		return dbUsername.equals(CustomerModel.getCust_Username())
				&& dbPassword.equals(CustomerModel.getCust_Password());
	}
}
