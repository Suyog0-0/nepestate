package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.nepestate.model.PropertyModel;
import com.nepestate.service.PropertyService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/HomeController", "/"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PropertyService propertyService;

    public HomeController() {
        super();
        propertyService = new PropertyService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch 4 properties for featured section
            List<PropertyModel> featuredProperties = propertyService.getFeaturedProperties(4);
            // Fetch next 3 properties for explore section
            List<PropertyModel> moreProperties = propertyService.getMoreProperties(4, 3);
            
            // Set properties as request attributes
            request.setAttribute("featuredProperties", featuredProperties);
            request.setAttribute("moreProperties", moreProperties);
            
            // Forward to JSP
            request.getRequestDispatcher("/WEB-INF/pages/Home.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading properties");
            request.getRequestDispatcher("/WEB-INF/pages/Error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}