// src/com/nepestate/controller/ViewPropertySPController.java
package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.nepestate.dao.ViewPropertySPDAO;
import com.nepestate.model.PropertyModel;
import com.nepestate.util.DBConnection;

@WebServlet("/ViewPropertySPController")
public class ViewPropertySPController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ViewPropertySPController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Test database connection
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.println("Database connection successful!");
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retrieve properties from database
        ViewPropertySPDAO propertyDAO = new ViewPropertySPDAO();
        List<PropertyModel> properties = propertyDAO.getAllProperties();
        request.setAttribute("properties", properties);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/pages/ViewPropertySP.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}