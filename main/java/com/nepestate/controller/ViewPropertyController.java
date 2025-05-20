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
		// TODO Auto-generated method stub
//		PropertyService propertyListService = new PropertyService();
//        List<PropertyModel> propertyList = propertyListService.getAllProperties();
//        request.setAttribute("propertyList", propertyList);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/ViewProperty.jsp");
//        dispatcher.forward(request, response);
//        request.getRequestDispatcher("/WEB-INF/pages/ViewProperty.jsp").forward(request, response);
		PropertyService propertyService = new PropertyService();
        List<PropertyModel> propertyList;
        List<PropertyModel> locationList = propertyService.getAllLocations();
        
        // Check if reset was requested
        String reset = request.getParameter("reset");
        if (reset != null && reset.equals("true")) {
            // Reset filters - get all properties
            propertyList = propertyService.getAllProperties();
        } else {
            // Get filter parameters
            String source = request.getParameter("source");
            String location = request.getParameter("location");
            String category = request.getParameter("category");
            String priceSort = request.getParameter("priceSort");
            
            // Store selected filter values to repopulate form
            request.setAttribute("selectedSource", source);
            request.setAttribute("selectedLocation", location);
            request.setAttribute("selectedCategory", category);
            request.setAttribute("selectedPriceSort", priceSort);
            
            // If any filter is set, get filtered results
            if ((source != null && !source.isEmpty()) || 
                (location != null && !location.isEmpty()) || 
                (category != null && !category.isEmpty()) || 
                (priceSort != null && !priceSort.isEmpty())) {
                
                propertyList = propertyService.advancedSearch(source, location, category, priceSort);
            } else {
                // No filters set, get all properties
                propertyList = propertyService.getAllProperties();
            }
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

