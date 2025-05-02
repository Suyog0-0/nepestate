package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nepestate.model.PropertyModel;
import com.nepestate.service.PropertyService;

/**
 * Servlet implementation class PostProductController
 */
@WebServlet("/PostPropertyController")
public class PostPropertyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostPropertyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/PostProperty.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date date = new Date(); 
		String title = request.getParameter("title");
        float price = Float.parseFloat(request.getParameter("price"));
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        float area = Float.parseFloat(request.getParameter("area"));
        String description = request.getParameter("description");
        String propertyType = request.getParameter("propertyType");
        
        // Handle multiple amenities
        List<String> selectedAmenities = new ArrayList<>();
        if (request.getParameter("amenity_cctv") != null) {
            selectedAmenities.add("cctv");
        }
        if (request.getParameter("amenity_parking") != null) {
            selectedAmenities.add("parking");
        }
        if (request.getParameter("amenity_cafeteria") != null) {
            selectedAmenities.add("cafeteria");
        }
        if (request.getParameter("amenity_lift") != null) {
            selectedAmenities.add("lift");
        }
        if (request.getParameter("amenity_garden") != null) {
            selectedAmenities.add("garden");
        }
        if (request.getParameter("amenity_swimming_pool") != null) {
            selectedAmenities.add("swimming-pool");
        }
        if (request.getParameter("amenity_gym") != null) {
            selectedAmenities.add("gym");
        }
        if (request.getParameter("amenity_wifi") != null) {
            selectedAmenities.add("wifi");
        }
        if (request.getParameter("amenity_dining_room") != null) {
            selectedAmenities.add("dining-room");
        }
        if (request.getParameter("amenity_fencing") != null) {
            selectedAmenities.add("fencing");
        }
        if (request.getParameter("amenity_tv_cable") != null) {
            selectedAmenities.add("tv-cable");
        }
        if (request.getParameter("amenity_electricity_backup") != null) {
            selectedAmenities.add("electricity-backup");
        }
        if (request.getParameter("amenity_conditioning") != null) {
            selectedAmenities.add("conditioning");
        }
        if (request.getParameter("amenity_fire_alarm") != null) {
            selectedAmenities.add("fire-alarm");
        }
        if (request.getParameter("amenity_fire_place") != null) {
            selectedAmenities.add("fire-place");
        }
        if (request.getParameter("amenity_solar_water") != null) {
            selectedAmenities.add("solar-water");
        }
        if (request.getParameter("amenity_security") != null) {
            selectedAmenities.add("security");
        }
        if (request.getParameter("amenity_jacuzzi") != null) {
            selectedAmenities.add("jacuzzi");
        }
        if (request.getParameter("amenity_kitchen") != null) {
            selectedAmenities.add("kitchen");
        }
        if (request.getParameter("amenity_drainage") != null) {
            selectedAmenities.add("drainage");
        }
        if (request.getParameter("amenity_washing_machine") != null) {
            selectedAmenities.add("washing-machine");
        }
        String amenitiesForDB = String.join(",", selectedAmenities);
        System.out.println("Amenities:"+ amenitiesForDB);
        
        PropertyModel propertyModel = new PropertyModel(title,propertyType,price,area,address,city,description,"Available",amenitiesForDB,date,"");
        
        PropertyService postPropertyService = new PropertyService();
        postPropertyService.addProperty(propertyModel);
	}
}