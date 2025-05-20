package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String action = request.getParameter("action");
		    String customerIdStr = request.getParameter("customerId");

		    if (customerIdStr != null && !customerIdStr.isEmpty()) {
		        int customerId = Integer.parseInt(customerIdStr);
		        CustomerService customerService = new CustomerService();
		        customerService.deleteInterestedCustomer(customerId);

		        if ("notify".equals(action)) {
		            request.setAttribute("popupMessage", "Customer is notified of their land purchase.");
		        }
		    }
		CustomerService customerService = new CustomerService();
		List<CustomerModel> interestedCustomers = customerService.getAllInterestedCustomers();
        request.setAttribute("interestedCustomers", interestedCustomers);
		request.getRequestDispatcher("/WEB-INF/pages/ContactListing.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
