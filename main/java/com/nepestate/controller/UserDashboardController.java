package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.nepestate.model.CustomerModel;
import com.nepestate.service.DashboardService;

/**
 * Servlet implementation class userdashboard
 */
@WebServlet("/UserDashboardController")
public class UserDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false); // Avoid creating new session

	        // Get customer from session using correct attribute name
	        CustomerModel loggedInCustomer = (CustomerModel) session.getAttribute("loggedInCustomer");

	        if (loggedInCustomer == null) {
	            System.out.println(">>> No user in session, redirecting to Login.jsp");
	            response.sendRedirect(request.getContextPath() + "/Login.jsp");
	            return;
	        }

	        int customerId = loggedInCustomer.getCustomerID();
	        System.out.println(">>> Logged in user ID: " + customerId);

	        // Dashboard stats
	        DashboardService dashboardService = new DashboardService();
	        int totalRevenue = dashboardService.getTotalRevenuebyCustomer(customerId);
	        int totalBought = dashboardService.getTotalPropertiesBoughtByCustomer(customerId);
	        int totalUploads = dashboardService.getTotalUploadsbyCustomer(customerId);

	        // Pass stats to JSP
	        request.setAttribute("totalRevenue", totalRevenue);
	        request.setAttribute("totalBought", totalBought);
	        request.setAttribute("totalUploads", totalUploads);

	        System.out.println(">>> Forwarding to UserDashboard.jsp");
	        request.getRequestDispatcher("/WEB-INF/pages/UserDashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
