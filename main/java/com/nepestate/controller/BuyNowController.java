package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.nepestate.model.CustomerModel;
import com.nepestate.service.CustomerService;

/**
 * Servlet implementation class BuyNowController
 */
@WebServlet("/BuyNowController")
public class BuyNowController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyNowController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to POST for consistency
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerModel customer = (CustomerModel) session.getAttribute("loggedInCustomer");
        
        // Retrieve property ID from the request
        String propertyIdStr = request.getParameter("propertyId");

        // Check if customer is logged in
        if (customer == null) {
            session.setAttribute("error", "Please log in to express interest in this property.");
            response.sendRedirect("Login.jsp");
            return;
        }

        // Check if property ID is provided
        if (propertyIdStr == null || propertyIdStr.trim().isEmpty()) {
            session.setAttribute("error", "Invalid property selection.");
            response.sendRedirect("PropertyListController"); // Redirect to property list
            return;
        }

        try {
            int propertyId = Integer.parseInt(propertyIdStr);
            
            CustomerService customerService = new CustomerService();
            boolean saved = customerService.saveInterestedCustomer(customer, propertyId);

            if (saved) {
                session.setAttribute("message", "Your interest has been registered successfully! You can now view your contact listing.");
                response.sendRedirect("ContactListingController");
            } else {
                session.setAttribute("error", "Unable to register your interest at this time. Please try again.");
                // Redirect back to the property view page
                response.sendRedirect("ViewPropertyController?propertyId=" + propertyId);
            }
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid property ID format.");
            response.sendRedirect("PropertyListController");
        } catch (Exception e) {
            session.setAttribute("error", "An unexpected error occurred. Please try again.");
            response.sendRedirect("PropertyListController");
            e.printStackTrace();
        }
    }
}