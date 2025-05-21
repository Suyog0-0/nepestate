package com.nepestate.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.nepestate.model.CustomerModel;
import com.nepestate.model.PropertyModel;
import com.nepestate.service.CustomerService;
import com.nepestate.service.PropertyService;

/**
 * Servlet implementation class ProductAdmin
 */
@WebServlet("/AdminUserListingController")
public class AdminUserListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserListing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
        CustomerService customerListService = new CustomerService();
        List<CustomerModel> customerList = customerListService.getAllCustomers();
        request.setAttribute("customerList", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/AdminUserListing.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Handle delete user action
        String customerID = request.getParameter("customerID");
        String username = request.getParameter("username");
        if (customerID != null && !customerID.isEmpty()) {
            try {
                int id = Integer.parseInt(customerID);
                CustomerService customerService = new CustomerService();
                boolean deleted = customerService.deleteCustomer(id);
                if (deleted) {
                    request.setAttribute("successMessage", "User '" + username + "' deleted successfully");
                } else {
                    request.setAttribute("errorMessage", "Failed to delete user '" + username + "'");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid customer ID");
            }
        }
        // Redirect back to the admin user listing page
        doGet(request, response);
	}

}

