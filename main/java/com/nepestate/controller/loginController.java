package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nepestate.util.RedirectionUtil;
import com.nepestate.util.ValidationUtil;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/loginController")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;
	
	@Override
	public void init() throws ServletException {
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(RedirectionUtil.loginUrl).forward(request, response);
	}

	
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (!validationUtil.isNullOrEmpty("username") && !validationUtil.isNullOrEmpty("password")) {

			StudentModel studentModel = new StudentModel(username, password);
			Boolean loginStatus = loginService.loginUser(studentModel);

			if (loginStatus != null && loginStatus) {	
				SessionUtil.setAttribute(req, "username", username);
				if (username.equals("admin")) {
					CookiesUtil.addCookie(resp, "role", "admin", 5 * 30);
					resp.sendRedirect(req.getContextPath() + "/dashboard"); // Redirect to /home
				} else {
					CookiesUtil.addCookie(resp, "role", "student", 5 * 30);
					resp.sendRedirect(req.getContextPath() + "/home"); // Redirect to /home
				}
			} else {
				handleLoginFailure(req, resp, loginStatus);
			}
		} else {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please fill all the fields!",
					RedirectionUtil.loginUrl);
		}
	} **/

		doGet(request, response);
	}
	
	/** private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher(RedirectionUtil.loginUrl).forward(req, resp);
	} **/
}
