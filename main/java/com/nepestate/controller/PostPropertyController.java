	package com.nepestate.controller;
	
	import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
	import java.time.LocalTime;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;
	
	import com.nepestate.model.PropertyModel;
	import com.nepestate.service.PropertyService;
import com.nepestate.util.ImageUtil;
import com.nepestate.util.ValidationUtil;
	
	/**
	 * Servlet implementation class PostProductController
	 */
	@WebServlet("/PostPropertyController")
	@MultipartConfig

	public class PostPropertyController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private final ImageUtil imageUtil = new ImageUtil();
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
			
				try {
				System.out.println("Received Title: " + request.getParameter("title"));
				String validationMessage = validatePropertyForm(request);
				
				if (validationMessage != null) {
					System.out.println(validationMessage);
					handleError(request, response, validationMessage);
					return;
					}
					else {
					
						
					PropertyModel property=createPropertyFromRequest(request);
					PropertyService propertyService = new PropertyService();
					Boolean result = propertyService.addProperty(property);
					if (result != null && result) {
	//			
						request.setAttribute("success", "Property posting is successful!");
		                request.getRequestDispatcher("/WEB-INF/pages/PropertyListing.jsp").forward(request, response);
					} else {
						handleError(request, response, "Failed to post property. Please try again.");
					}
				}
			}catch (NumberFormatException e) {
	            System.err.println("Invalid number format: " + e.getMessage());
	            handleError(request, response, "Please enter valid numbers for price and area.");
	        } catch (Exception e) {
	            System.err.println("Unexpected error in posting property: " + e.getMessage());
	            e.printStackTrace();
	            handleError(request, response, "An unexpected error occurred: " + e.getMessage());
	        }
					}
			
	        
	//        
		private String validatePropertyForm(HttpServletRequest request) {
			String title = request.getParameter("title");
	        String Stringprice = request.getParameter("price");
	        int price = Integer.parseInt(request.getParameter("price"));
	        String propertyType = request.getParameter("propertyType");
	        String address = request.getParameter("address");
	        String city = request.getParameter("city");
	        String StringArea = request.getParameter("area");
	        float area = Float.parseFloat(request.getParameter("area"));
	        String description = request.getParameter("description");
	        
	        System.out.println("Validating Title: [" + title + "]");
	        
		
			if (ValidationUtil.isNullOrEmpty(title))
				return "Title is required.";
			if (ValidationUtil.isNullOrEmpty(Stringprice))
				return "Price is required.";
			if (ValidationUtil.isNullOrEmpty(propertyType))
				return "Property Type is required.";
			if (ValidationUtil.isNullOrEmpty(address))
				return "Address is required.";
			if (ValidationUtil.isNullOrEmpty(city))
				return "City is required.";
			if (ValidationUtil.isNullOrEmpty(StringArea))
				return "Area is required.";
			if (ValidationUtil.isNullOrEmpty(description))
				return "Description is required.";
			
		
			if (!ValidationUtil.isAlphabetic(city))
				return "City must contain letters and no numeric digits.";
			if (!ValidationUtil.isAlphabetic(address))
				return "Address must contain letters and no numeric digits.";
			if (!ValidationUtil.isValidPrice(price)) {
			    return "Price must be a number greater than 0.";
			}
			if (!ValidationUtil.isValidPrice(area)) {
			    return "Area must be a number greater than 0.";
			}
	
			return null; // All validations passed
		}

		
			private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
					throws ServletException, IOException {
				req.setAttribute("error", message);
				req.setAttribute("title", req.getParameter("title"));
				req.setAttribute("price", req.getParameter("price"));
				req.setAttribute("propertyType", req.getParameter("propertyType"));
				req.setAttribute("address", req.getParameter("address"));
				req.setAttribute("city", req.getParameter("city"));
				req.setAttribute("area", req.getParameter("area"));
				req.setAttribute("description", req.getParameter("description"));
				req.getRequestDispatcher("/WEB-INF/pages/PostProperty.jsp").forward(req, resp);
			
			}
			
			private PropertyModel createPropertyFromRequest(HttpServletRequest request) {
				try {
				Date date = new Date(); 
				String title = request.getParameter("title");
				int price = Integer.parseInt(request.getParameter("price"));
		        String address = request.getParameter("address");
		        String city = request.getParameter("city");
		        float area = Float.parseFloat(request.getParameter("area"));
		        String description = request.getParameter("description");
		        String propertyType = request.getParameter("propertyType");
		        Part image = request.getPart("image");
	            String imagePath = null;
	            if (image != null && image.getSize() > 0) {
	                String folder = "property"; // Changed from "profile" to "property" for clarity
	                String savePath = request.getServletContext().getRealPath("/images/" + folder);
	                System.out.println("Attempting to save image to: " + savePath);
	                
	                boolean uploaded = imageUtil.uploadImage(image, savePath);
	                
	                if (uploaded) {
	                    String imageName = imageUtil.getImageNameFromPart(image);
	                    imagePath = "/images/" + folder + "/" + imageName;
	                    System.out.println("Image uploaded successfully. Path: " + imagePath);
	                } else {
	                    System.out.println("Image upload failed, using default image");
	                }
	            } else {
	                System.out.println("No image provided or empty image, using default image");
	            }
	            
		      
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
		        PropertyModel property= new PropertyModel();
		        property.setProperty_Title(title);
		        property.setProperty_Type(propertyType);
		        property.setProperty_Price(price);
		        property.setProperty_Area(area);
		        property.setProperty_Address(address);
		        property.setProperty_City(city);
		        property.setProperty_Description(description);
		        property.setProperty_Status("Available");
		        property.setProperty_Amentities(amenitiesForDB);
		        property.setProperty_DateAdded(date);
//		        property.setProperty_Photos("");
		        property.setProperty_Photos(imagePath);
		        
		        return property;
		        

			
			
			  }catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Exception in createPropertyFromRequest: " + e.getMessage());
		        }
				return null;
			}
	}