package com.nepestate.controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nepestate.model.PropertyModel;
import com.nepestate.service.PropertyService;

/**
 * Servlet implementation class Product
 */
@WebServlet("/ViewPropertyController")
public class ViewPropertyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPropertyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PropertyService propertyService = new PropertyService();
        List<PropertyModel> propertyList;
        List<PropertyModel> locationList = propertyService.getAllLocations();

        // Get search parameter first
        String searchQuery = request.getParameter("search");

        // Get filter parameters
        String source = request.getParameter("source");
        String location = request.getParameter("location");
        String category = request.getParameter("category");
        String priceSort = request.getParameter("priceSort");

        // Check if reset was requested
        String reset = request.getParameter("reset");

        // Debug output - check filter parameters
        System.out.println("Filter parameters - Source: " + source + ", Location: " + location + 
                          ", Category: " + category + ", PriceSort: " + priceSort);

        if (reset != null && reset.equals("true")) {
            // Reset filters - get all properties
            propertyList = propertyService.getAllProperties();
            System.out.println("Filters reset - All properties count: " + 
                              (propertyList != null ? propertyList.size() : "null"));
        } else if (source != null || location != null || category != null || priceSort != null) {
            // Apply filters if any filter parameter is present
            propertyList = propertyService.advancedSearch(source, location, category, priceSort);
            System.out.println("Filters applied - Results count: " + 
                              (propertyList != null ? propertyList.size() : "null"));
        } else if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Apply search if search query is present
            propertyList = propertyService.searchBarProperties(searchQuery);
            request.setAttribute("searchQuery", searchQuery);
            System.out.println("Search applied - Query: " + searchQuery + ", Results count: " + 
                              (propertyList != null ? propertyList.size() : "null"));
        } else {
            // Default to all properties if no filters or search query
            propertyList = propertyService.getAllProperties();
            System.out.println("No filters - All properties count: " + 
                              (propertyList != null ? propertyList.size() : "null"));
        }

        // Set attributes and forward to the JSP
        request.setAttribute("propertyList", propertyList);
        request.setAttribute("locationList", locationList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/ViewProperty.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}