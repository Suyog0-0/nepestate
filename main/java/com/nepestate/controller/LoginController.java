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

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginService loginObject = new LoginService();

        // Admin login section
        AdminModel adminModel = new AdminModel(username, password);
        Boolean adminLoginStatus = loginObject.loginAdmin(adminModel);

        if (adminLoginStatus != null && adminLoginStatus) {
            System.out.println("Successfully Admin Login: " + username);

            SessionUtil.setAttribute(request, "username", username);
            SessionUtil.setAttribute(request, "userEmail", username);
            CookieUtil.addCookie(response, "role", "admin", 5 * 30);

            request.getRequestDispatcher("/WEB-INF/pages/AdminDashboard.jsp").forward(request, response);
            return;
        }

        // Customer login section
        CustomerModel customerModel = new CustomerModel(username, password);
        CustomerModel loggedInCustomer = loginObject.loginCustomer(customerModel);

        if (loggedInCustomer != null) {
            System.out.println("Successfully User Login: " + username);


            // Set required session attributes
            SessionUtil.setAttribute(request, "username", username);
            SessionUtil.setAttribute(request, "userEmail", username);
            SessionUtil.setAttribute(request, "customerId", loggedInCustomer.getCustomerID());
            
            // Saves full customer model to session
            SessionUtil.setAttribute(request, "loggedInCustomer", loggedInCustomer);

            // Saves profile picture from DB into session
            String profilePic = loggedInCustomer.getCustomer_ProfilePicture();
            SessionUtil.setAttribute(request, "profilePic", profilePic);

            CookieUtil.addCookie(response, "role", "customer", 5 * 30);

            response.sendRedirect(request.getContextPath() + "/HomeController");
            return;
        }

        // Handle login failure
        handleLoginFailure(request, response, (adminLoginStatus != null || loggedInCustomer != null) ? false : null);
    }

    private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, Boolean loginStatus)
        throws ServletException, IOException {

        String errorMessage = (loginStatus == null)
            ? "Our server is under maintenance. Please try again later!"
            : "User credential mismatch. Please try again!";

        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
    }
}
