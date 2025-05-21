package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles success page redirection and message display
 */
@WebServlet("/SuccessController")
public class SuccessController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests for success page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        // Retrieve success message from request parameters
        String successMessage = request.getParameter("message");
        if (successMessage != null) {
            request.setAttribute("success", successMessage);
        }
        
        // Forward to success page
        request.getRequestDispatcher("/WEB-INF/pages/Success.jsp")
              .forward(request, response);
    }

    /**
     * Handles POST requests by delegating to GET
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        doGet(request, response);
    }
}