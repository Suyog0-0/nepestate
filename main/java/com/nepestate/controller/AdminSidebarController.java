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

@WebServlet("/AdminSidebar")
public class AdminSidebarController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminSidebarController() {
        super();
    }
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/AdminSidebar.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        LoginService loginObject = new LoginService();
        

        if ("Administrator".equals(userType)) {
            AdminModel adminModel = new AdminModel(username, password);
            Boolean loginStatus = loginObject.loginAdmin(adminModel);
            
            if (loginStatus != null && loginStatus) {
                SessionUtil.setAttribute(request, "username", username);
                CookieUtil.addCookie(response, "role", "admin", 5 * 30);
                request.getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
            } else {
                SessionUtil.setAttribute(request, "username", username);
                CookieUtil.addCookie(response, "role", "student", 5 * 1);
                handleLoginFailure(request, response, loginStatus);
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