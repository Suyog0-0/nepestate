package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.nepestate.model.CustomerModel;
import com.nepestate.service.CustomerService;

/**
 * Servlet implementation class ContactListingController
 */
@WebServlet("/ContactListingController")
public class ContactListingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactListingController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerModel loggedInCustomer = (CustomerModel) session.getAttribute("loggedInCustomer");
        
        // Check if customer is logged in
        if (loggedInCustomer == null) {
            session.setAttribute("error", "Please log in to view your contact listings.");
            response.sendRedirect("Login.jsp");
            return;
        }
        
        try {
            CustomerService customerService = new CustomerService();
            
            // Get all interested customers (this might need to be filtered based on your business logic)
            List<CustomerModel> interestedCustomers = customerService.getAllInterestedCustomers();
            
            // Set the list as request attribute
            request.setAttribute("interestedCustomers", interestedCustomers);
            
            // Forward to the contact listing JSP page
            request.getRequestDispatcher("ContactListing.jsp").forward(request, response);
            
        } catch (Exception e) {
            session.setAttribute("error", "Unable to load contact listings at this time.");
            response.sendRedirect("Dashboard.jsp"); // or wherever you want to redirect on error
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}