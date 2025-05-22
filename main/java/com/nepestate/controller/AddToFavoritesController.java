package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.nepestate.service.FavoritesService;

/**
 * Servlet implementation class AddToFavoritesController
 */
@WebServlet("/AddToFavoritesController")
public class AddToFavoritesController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FavoritesService favoritesService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToFavoritesController() {
        super();
        this.favoritesService = new FavoritesService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to POST
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        // Debug logging
        System.out.println("=== AddToFavoritesController Debug ===");
        
        // FIXED: Check for correct session attributes based on your login system
        // Check for both possible session attribute names
        Integer customerId = null;
        String username = null;
        
        if (session != null) {
            // Try different possible session attribute names
            customerId = (Integer) session.getAttribute("customerId");
            if (customerId == null) {
                customerId = (Integer) session.getAttribute("customer_id");
            }
            
            username = (String) session.getAttribute("username");
            
            System.out.println("Session exists");
            System.out.println("Username: " + username);
            System.out.println("CustomerId: " + customerId);
            System.out.println("Role: " + session.getAttribute("role"));
        } else {
            System.out.println("No session found");
        }
        
        // Check if user is logged in
        if (session == null || username == null || customerId == null) {
            System.out.println("User not logged in, redirecting to login");
            if (session == null) {
                session = request.getSession(true);
            }
            session.setAttribute("error", "Please log in to add properties to favorites.");
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }
        
        try {
            // Get property ID from request
            String propertyIdParam = request.getParameter("propertyId");
            System.out.println("Property ID Parameter: " + propertyIdParam);
            
            if (propertyIdParam == null || propertyIdParam.trim().isEmpty()) {
                session.setAttribute("error", "Invalid property ID.");
                response.sendRedirect(request.getContextPath() + "/ViewPropertyController");
                return;
            }
            
            int propertyId = Integer.parseInt(propertyIdParam);
            System.out.println("Processing for CustomerId: " + customerId + ", PropertyId: " + propertyId);
            
            // Check if property is already in favorites
            if (favoritesService.isFavoriteExists(customerId, propertyId)) {
                // Remove from favorites if it already exists
                System.out.println("Property already in favorites, removing...");
                boolean removed = favoritesService.removeFromFavorites(customerId, propertyId);
                if (removed) {
                    System.out.println("Property removed successfully");
                    session.setAttribute("message", "Property removed from favorites successfully!");
                } else {
                    System.out.println("Failed to remove property");
                    session.setAttribute("error", "Failed to remove property from favorites.");
                }
            } else {
                // Add to favorites if it doesn't exist
                System.out.println("Property not in favorites, adding...");
                boolean added = favoritesService.addToFavorites(customerId, propertyId);
                if (added) {
                    System.out.println("Property added successfully");
                    session.setAttribute("message", "Property added to favorites successfully!");
                } else {
                    System.out.println("Failed to add property");
                    session.setAttribute("error", "Failed to add property to favorites.");
                }
            }
            
            // Redirect back to the property details page
            response.sendRedirect(request.getContextPath() + "/ViewPropertySPController?propertyId=" + propertyId);
            
        } catch (NumberFormatException e) {
            System.out.println("Number format exception: " + e.getMessage());
            session.setAttribute("error", "Invalid property ID format.");
            response.sendRedirect(request.getContextPath() + "/ViewPropertyController");
        } catch (Exception e) {
            System.out.println("Error in AddToFavoritesController: " + e.getMessage());
            e.printStackTrace();
            session.setAttribute("error", "An error occurred while processing your request.");
            response.sendRedirect(request.getContextPath() + "/ViewPropertyController");
        }
    }
}