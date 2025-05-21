package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.nepestate.service.PropertyService;

@WebServlet("/DeletePropertyController")
public class DeletePropertyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String propertyIdStr = request.getParameter("propertyId");
         HttpSession session = request.getSession();

         if (propertyIdStr != null && !propertyIdStr.isEmpty()) {
             try {
                 int propertyId = Integer.parseInt(propertyIdStr);
                 PropertyService propertyService = new PropertyService();
                 boolean deleted = propertyService.deletePropertybyId(propertyId);

                 if (deleted) {
                     session.setAttribute("successMessage", "Property deleted successfully.");
                 } else {
                     session.setAttribute("errorMessage", "Failed to delete property.");
                 }

             } catch (NumberFormatException e) {
                 session.setAttribute("errorMessage", "Invalid property ID format.");
             } catch (Exception e) {
                 session.setAttribute("errorMessage", "Error deleting property: " + e.getMessage());
             }
         } else {
             session.setAttribute("errorMessage", "Property ID is missing.");
         }

         // Redirect to listing page
         response.sendRedirect(request.getContextPath() + "/PropertyListingController");
     }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
