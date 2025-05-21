package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.nepestate.service.FavoriteService;

/**
 * Servlet implementation class AddtoFavouritesController
 */
@WebServlet("/AddToFavoritesController")
public class AddToFavoritesController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            session.setAttribute("error", "Please log in to bookmark properties.");
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }

        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        
        FavoriteService favoriteService = new FavoriteService();
        boolean success = favoriteService.addToFavorites(userId, propertyId);
        
        if (success) {
            session.setAttribute("message", "Property added to favorites!");
        } else {
            session.setAttribute("error", "Failed to add to favorites. Please try again.");
        }
        
        response.sendRedirect(request.getContextPath() + "/ViewPropertySPController?propertyId=" + propertyId);
    }
}