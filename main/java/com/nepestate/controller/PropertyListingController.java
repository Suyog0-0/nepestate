package com.nepestate.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.model.AdminModel;
import com.nepestate.model.CustomerModel;
import com.nepestate.model.PropertyModel;
import com.nepestate.service.PropertyService;

/**
 * Servlet implementation class propertylisting
 */
@WebServlet("/PropertyListingController")
public class PropertyListingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyListingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("PropertyListingController: doGet() called");
	        
	        PropertyService propertyService = null;
	        HttpSession session = request.getSession(false);
	        List<PropertyModel> propertyList = new ArrayList<>();
	        String userType = "guest";

	        try {
	            // Check if session exists
	            if (session == null) {
	                System.out.println("No session found - redirecting to login");
	                response.sendRedirect(request.getContextPath() + "/Login.jsp");
	                return;
	            }

	            // Initialize PropertyService
	            propertyService = new PropertyService();

	            // Check user type from session
	            AdminModel admin = (AdminModel) session.getAttribute("loggedInAdmin");
	            CustomerModel customer = (CustomerModel) session.getAttribute("loggedInCustomer");

	            if (admin != null) {
	                // Admin user - show all properties
	                System.out.println("Admin logged in: " + admin.getAdmin_Username());
	                userType = "admin";
	                propertyList = propertyService.getAllProperties();
	                System.out.println("Admin: Fetched " + propertyList.size() + " total properties");
	                
	            } else if (customer != null) {
	                // Customer user - show only their associated properties
	                System.out.println("Customer logged in: " + customer.getCustomer_Username() + 
	                                 " with ID: " + customer.getCustomerID());
	                userType = "customer";
	                
	                propertyList = propertyService.getPropertyByCustomerId(customer.getCustomerID());
	                System.out.println("Customer " + customer.getCustomerID() + ": Fetched " + 
	                                 propertyList.size() + " properties");
	                
	                // If no properties found, log this information
	                if (propertyList.isEmpty()) {
	                    System.out.println("No properties found for customer ID: " + customer.getCustomerID());
	                    System.out.println("This could mean:");
	                    System.out.println("1. Customer has no role assigned in role_customer table");
	                    System.out.println("2. Customer's role has no properties assigned in role_property table");
	                    System.out.println("3. Database connection issue");
	                }
	                
	            } else {
	                // No valid user found in session
	                System.out.println("No valid user session found - redirecting to login");
	                response.sendRedirect(request.getContextPath() + "/Login.jsp");
	                return;
	            }

	            // Set attributes for JSP
	            request.setAttribute("propertyList", propertyList);
	            request.setAttribute("userType", userType);
	            request.setAttribute("totalProperties", propertyList.size());
	            
	            // Add customer info for debugging if customer is logged in
	            if (customer != null) {
	                request.setAttribute("customerId", customer.getCustomerID());
	                request.setAttribute("customerUsername", customer.getCustomer_Username());
	            }
	            
	            System.out.println("Forwarding to PropertyListing.jsp with " + propertyList.size() + " properties");
	            
	            // Forward to JSP
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/PropertyListing.jsp");
	            dispatcher.forward(request, response);
	            
	        } catch (Exception e) {
	            System.out.println("Exception in PropertyListingController: " + e.getMessage());
	            e.printStackTrace();
	            
	            // Set error attributes
	            request.setAttribute("errorMessage", "There was an error retrieving property data: " + e.getMessage());
	            request.setAttribute("technicalDetails", e.toString());
	            request.setAttribute("userType", userType);
	            
	            // Forward to error page
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/ErrorPage.jsp");
	            if (dispatcher != null) {
	                dispatcher.forward(request, response);
	            } else {
	                // Fallback if error page doesn't exist
	                response.setContentType("text/html");
	            }
	        } finally {
	            // PropertyService cleanup handled automatically by garbage collection
	            // No explicit close method available
	            System.out.println("PropertyListingController: Request processing completed");
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
