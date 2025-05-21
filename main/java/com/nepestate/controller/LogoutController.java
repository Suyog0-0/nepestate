package com.nepestate.controller;

import com.nepestate.util.CookieUtil;
import com.nepestate.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = {"/Logout"})
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle GET (e.g. if your sidebar Logout button just links to /logout)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        performLogout(request, response);
    }

    // Handle POST (in case you submit a form to /logout)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        performLogout(request, response);
    }

    // Common logout logic
    private void performLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    	 // Invalidate the HTTP session
        SessionUtil.invalidateSession(request);
        // Remove the “rememberMe” cookie if you’re using it
        CookieUtil.deleteCookie(response, "rememberMe");

        // Redirect to HomeController to display the message
        response.sendRedirect(request.getContextPath() + "/HomeController");
    }
}
