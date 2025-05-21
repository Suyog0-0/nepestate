package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nepestate.model.PropertyModel;
import com.nepestate.model.CustomerModel;
import com.nepestate.service.PropertyService;
import com.nepestate.service.CustomerService;

@WebServlet("/ViewPropertySPController")
public class ViewPropertySPController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PropertyService propertyService;
    private CustomerService customerService;

    public ViewPropertySPController() {
        super();
        propertyService = new PropertyService();
        customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String propertyIdParam = request.getParameter("propertyId");
        
        // Debug output to check the parameter
        System.out.println("Property ID Parameter: " + propertyIdParam);
        
        if (propertyIdParam == null || propertyIdParam.trim().isEmpty()) {
            request.setAttribute("error", "Property ID not provided");
            request.getRequestDispatcher("/WEB-INF/pages/ViewPropertySP.jsp").forward(request, response);
            return;
        }
        
        int propertyId;
        try {
            propertyId = Integer.parseInt(propertyIdParam);
        } catch (NumberFormatException e) {
            System.out.println("Invalid property ID format: " + propertyIdParam);
            request.setAttribute("error", "Invalid property ID format");
            request.getRequestDispatcher("/WEB-INF/pages/ViewPropertySP.jsp").forward(request, response);
            return;
        }

        // Debug output before getting property
        System.out.println("Fetching property with ID: " + propertyId);
        
        PropertyModel property = propertyService.getPropertyById(propertyId);
        
        // Debug output after property retrieval
        if (property == null) {
            System.out.println("Property not found for ID: " + propertyId);
            request.setAttribute("error", "Property not found");
            request.getRequestDispatcher("/WEB-INF/pages/ViewPropertySP.jsp").forward(request, response);
            return;
        }
        
        System.out.println("Property found: " + property.getProperty_Title());
        System.out.println("CustomerID from property: " + property.getCustomerID());
        
        // Only try to get the customer if we have a valid customerID
        CustomerModel customer = null;
        if (property.getCustomerID() > 0) {
            customer = customerService.getCustomerById(property.getCustomerID());
            if (customer == null) {
                System.out.println("Customer not found for ID: " + property.getCustomerID());
            } else {
                System.out.println("Customer found: " + customer.getCustomer_FirstName());
            }
        } else {
            System.out.println("No valid CustomerID found in property");
        }

        request.setAttribute("property", property);
        request.setAttribute("customer", customer);

        // Debug output before forwarding
        System.out.println("Forwarding to ViewPropertySP.jsp");
        
        request.getRequestDispatcher("/WEB-INF/pages/ViewPropertySP.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}