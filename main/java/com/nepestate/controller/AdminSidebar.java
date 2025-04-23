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
 * Servlet implementation class adminsidebar
 */
@WebServlet("/AdminSidebar")
public class AdminSidebar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSidebar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/AdminSidebar.jsp").forward(request, response);
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
		String userType = request.getParameter("userType");
		LoginService loginObject=new LoginService();
		System.out.println(userType);
		
		if ("Administrator".equals(userType))
		{
			AdminModel adminModel = new AdminModel(username, password);
			Boolean loginStatus = loginObject.loginAdmin(adminModel);
			if (loginStatus != null && loginStatus) {
				
				System.out.println("Successfully Login");
				SessionUtil.setAttribute(request, "username", username);
				CookieUtil.addCookie(response, "role", "admin", 5 * 30);
				request.getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
			}else {
				SessionUtil.setAttribute(request, "username", username);
				CookieUtil.addCookie(response, "role", "student", 5 * 1);
				System.out.println("Error at Login Controller");
				System.out.println(loginStatus);
				handleLoginFailure(request, response, loginStatus);
			}
		}
	else {
		CustomerModel customerModel = new CustomerModel(username, password);
		Boolean loginStatus = loginObject.loginUser(customerModel);
		if (loginStatus != null && loginStatus) {
			System.out.println("Successfully Login");
			request.getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
		}else {
			System.out.println("Error at Login Controller");
			System.out.println(loginStatus);
			handleLoginFailure(request, response, loginStatus);
		}
	}
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
