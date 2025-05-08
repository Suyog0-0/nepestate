package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nepestate.model.CustomerModel;
import com.nepestate.model.AdminModel;
import com.nepestate.service.LoginService;
import com.nepestate.util.CookieUtil;
import com.nepestate.util.SessionUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	/**
	 * Handles GET requests by forwarding to the Login.jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to process user login (Admin or Customer).
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve login credentials
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LoginService loginObject = new LoginService();

		// Check for admin login
		AdminModel adminModel = new AdminModel(username, password);
		Boolean adminLoginStatus = loginObject.loginAdmin(adminModel);

		if (adminLoginStatus != null && adminLoginStatus) {
			System.out.println("Successfully Admin Login");

			// Set session attribute for admin
			SessionUtil.setAttribute(request, "username", username);

			// Set admin role in cookie
			CookieUtil.addCookie(response, "role", "admin", 5 * 30);

			// Forward to admin dashboard
			request.getRequestDispatcher("/WEB-INF/pages/AdminDashboard.jsp").forward(request, response);
			return;
		}

		// Check for customer login
		CustomerModel customerModel = new CustomerModel(username, password);
		CustomerModel loggedInCustomer = loginObject.loginCustomer(customerModel);

		if (loggedInCustomer != null) {
			System.out.println("Successfully User Login");

			// Set session attributes for customer
			SessionUtil.setAttribute(request, "username", username);
			SessionUtil.setAttribute(request, "customerId", loggedInCustomer.getCustomerID());

			// Set customer role in cookie
			CookieUtil.addCookie(response, "role", "customer", 5 * 30);

			System.out.println("Set customerId in session: " + loggedInCustomer.getCustomerID());

			// Redirect to home controller
			response.sendRedirect(request.getContextPath() + "/HomeController");
			return;
		}

		// Handle failed login
		handleLoginFailure(request, response, (adminLoginStatus != null || loggedInCustomer != null) ? false : null);
	}

	/**
	 * Handles login failure by setting appropriate error messages.
	 */
	private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		request.setAttribute("error", errorMessage);
		request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
	}
}