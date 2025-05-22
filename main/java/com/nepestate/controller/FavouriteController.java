package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.nepestate.model.PropertyModel;
import com.nepestate.service.FavoritesService;

/**
 * Servlet implementation class FavouriteController
 */
@WebServlet("/FavouriteController")
public class FavouriteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FavoritesService favoritesService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavouriteController() {
        super();
        this.favoritesService = new FavoritesService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Debug logging
        System.out.println("=== FavouriteController Debug ===");
        
        if (session != null) {
            System.out.println("Session exists");
            System.out.println("Username: " + session.getAttribute("username"));
            System.out.println("CustomerId: " + session.getAttribute("customerId"));
            System.out.println("Role: " + session.getAttribute("role"));
        } else {
            System.out.println("No session found");
        }

        // FIXED: Check for correct session attributes
        // Option 1: Check for username (most reliable)
        String username = (String) session.getAttribute("username");
        Integer customerId = (Integer) session.getAttribute("customerId");
        
        if (session == null || username == null || customerId == null) {
            System.out.println("User not logged in, redirecting to login");
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }

        System.out.println("User logged in: " + username + ", CustomerId: " + customerId);

        try {
            // Get favorite properties for the logged-in customer
            List<PropertyModel> favoriteProperties = favoritesService.getFavoriteProperties(customerId);
            
            System.out.println("Found " + (favoriteProperties != null ? favoriteProperties.size() : 0) + " favorite properties");

            // Set the favorite properties as request attribute
            request.setAttribute("favouriteProperties", favoriteProperties);

            // Forward to the Favourite.jsp page
            request.getRequestDispatcher("/WEB-INF/pages/Favourite.jsp").forward(request, response);
            
        } catch (Exception e) {
            System.err.println("Error in FavouriteController: " + e.getMessage());
            e.printStackTrace();
            
            // Set error message and forward to error page or back to home
            request.setAttribute("error", "Unable to load favorite properties. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/Favourite.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests by redirecting to GET
        doGet(request, response);
    }
}