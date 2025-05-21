package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search query parameter
        String query = request.getParameter("query");
        
        if (query != null && !query.trim().isEmpty()) {
            // If there's a valid query, redirect to the properties page with the search parameter
            response.sendRedirect(request.getContextPath() + "/ViewPropertyController?search=" + 
                                 java.net.URLEncoder.encode(query, "UTF-8"));
        } else {
            // If no query or empty query, just redirect to the properties page
            response.sendRedirect(request.getContextPath() + "/ViewPropertyController");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}