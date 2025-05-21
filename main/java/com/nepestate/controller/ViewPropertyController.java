package com.nepestate.controller;
import jakarta.servlet.RequestDispatcher;
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
 * Servlet implementation class ViewPropertyController
 */
@WebServlet("/ViewPropertyController")
public class ViewPropertyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPropertyController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PropertyService propertyService = new PropertyService();
        List<PropertyModel> propertyList;
        List<PropertyModel> locationList = propertyService.getAllLocations();

        // Check for search query
        String searchQuery = request.getParameter("search");
        
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // If there's a search query, use the search functionality
            propertyList = propertyService.searchBarProperties(searchQuery);
        } else {
            // Your existing logic for handling filters/reset
            String reset = request.getParameter("reset");
            if (reset != null && reset.equals("true")) {
                propertyList = propertyService.getAllProperties();
            } else {
                // Your existing filter handling logic
                String source = request.getParameter("source");
                String location = request.getParameter("location");
                String category = request.getParameter("category");
                String priceSort = request.getParameter("priceSort");

                // Store selected filter values
                request.setAttribute("selectedSource", source);
                request.setAttribute("selectedLocation", location);
                request.setAttribute("selectedCategory", category);
                request.setAttribute("selectedPriceSort", priceSort);

                if ((source != null && !source.isEmpty()) || 
                    (location != null && !location.isEmpty()) || 
                    (category != null && !category.isEmpty()) || 
                    (priceSort != null && !priceSort.isEmpty())) {
                    
                    String sortOrder = null;
                    if (priceSort != null) {
                        sortOrder = priceSort.equals("Low-High") ? "asc" : "desc";
                    }
                    
                    propertyList = propertyService.advancedSearch(source, location, category, sortOrder);
                } else {
                    propertyList = propertyService.getAllProperties();
                }
            }
        }

        // Set attributes and forward to JSP
        request.setAttribute("propertyList", propertyList);
        request.setAttribute("locationList", locationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/ViewProperty.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}