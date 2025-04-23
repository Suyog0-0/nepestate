package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.nepestate.config.DbConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/DbTestServlet")
public class DbTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DbTestServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DbConfig.getDbConnection();
            out.println("✅ Database connection successful!");
            conn.close();
        } catch (Exception e) {
            out.println("❌ Database connection failed: " + e.getMessage());
            e.printStackTrace(out); // Print stack trace to response
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
