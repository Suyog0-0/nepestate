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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		LoginService loginObject=new LoginService();
		AdminModel adminModel = new AdminModel(username, password);
		Boolean adminLoginStatus = loginObject.loginAdmin(adminModel);
			
			if (adminLoginStatus != null && adminLoginStatus) {
				
				System.out.println("Successfully Admin Login");
				SessionUtil.setAttribute(request, "username", username);
				CookieUtil.addCookie(response, "role", "admin", 5 * 30);
				request.getRequestDispatcher("/WEB-INF/pages/AdminDashboard.jsp").forward(request, response);
				return;
			}
		CustomerModel customerModel = new CustomerModel(username, password);
		Boolean customerLoginStatus = loginObject.loginUser(customerModel);
		if (customerLoginStatus != null && customerLoginStatus) {
			System.out.println("Successfully User Login");
			request.getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
			return;
		}
		handleLoginFailure(request, response, (adminLoginStatus != null || customerLoginStatus != null) ? false : null);
	}

		
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