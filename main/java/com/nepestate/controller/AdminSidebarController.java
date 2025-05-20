package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.nepestate.model.CustomerModel;
import com.nepestate.model.AdminModel;
import com.nepestate.service.LoginService;
import com.nepestate.util.CookieUtil;
import com.nepestate.util.SessionUtil;

@WebServlet("/AdminSidebar")
public class AdminSidebarController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminSidebarController() {
        super();
    }
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session != null) {
            AdminModel loggedInAdmin = (AdminModel) session.getAttribute("loggedInAdmin");

            if (loggedInAdmin != null) {
                request.setAttribute("adminName", loggedInAdmin.getAdmin_FirstName());
                request.setAttribute("adminEmail", loggedInAdmin.getAdmin_EmailAddress());
                request.setAttribute("adminPhone", loggedInAdmin.getAdmin_PhoneNumber());
                request.setAttribute("adminUsername", loggedInAdmin.getAdmin_Username());
                request.setAttribute("adminProfilePic", loggedInAdmin.getAdmin_ProfilePicture()); // Optional
            }
        request.getRequestDispatcher("/WEB-INF/pages/AdminSidebar.jsp").forward(request, response);
    }
 }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        LoginService loginObject = new LoginService();
        

        if ("Administrator".equals(userType)) {
            AdminModel adminModel = new AdminModel(username, password);
            AdminModel loggedInAdmin = loginObject.loginAdmin(adminModel);
            
            if (loggedInAdmin!=null) {
                SessionUtil.setAttribute(request, "username", username);
                SessionUtil.setAttribute(request, "role", "admin");
                CookieUtil.addCookie(response, "role", "admin", 5 * 30);
                SessionUtil.setAttribute(request, "loggedInAdmin", loggedInAdmin);
                response.sendRedirect(request.getContextPath() + "/AdminDashboardController");
            } else {
                handleLoginFailure(request, response, false);
            }
        } else {
            CustomerModel customerModel = new CustomerModel(username, password);
            Boolean loginStatus = loginObject.loginUser(customerModel);
            
            if (loginStatus != null && loginStatus) {
                request.getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
            } else {
                handleLoginFailure(request, response, loginStatus);
            }
        }
    }
    

    private void handleLoginFailure(HttpServletRequest request, HttpServletResponse response, Boolean loginStatus) 
        throws ServletException, IOException {
        String errorMessage = (loginStatus == null) ? 
            "Our server is under maintenance. Please try again later!" : 
            "User credential mismatch. Please try again!";
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
    }
}