package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.nepestate.model.CustomerModel;
import com.nepestate.model.PropertyModel;
import com.nepestate.service.CustomerService;
import com.nepestate.service.PropertyService;
import com.nepestate.util.ImageUtil;
import com.nepestate.util.ValidationUtil;

/**
 * Servlet implementation class UserProfileController
 */
@WebServlet("/UserProfileController")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ImageUtil imageUtil = new ImageUtil();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("customerId");
		
		if (customerId == null) {
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }
        
        CustomerService customerService = new CustomerService();
        CustomerModel customer = customerService.getCustomerById(customerId);
        
        if (customer == null) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }
        
        // Load properties
        List<PropertyModel> properties = null;
        PropertyService ps = new PropertyService();
        try {
			properties = ps.getPropertyByCustomer(customerId);
			System.out.print(properties);
			request.setAttribute("properties", properties);
		    request.setAttribute("propertiesCount", properties != null ? properties.size() : 0);
		    System.out.println("Loaded " + properties.size() + " properties for customer ID: " + customerId);
		} catch (SQLException e) {
			System.out.println("Error loading properties: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error loading properties. Please try again.");
		}
        
        // Set customer attributes
        request.setAttribute("customer", customer);
        request.setAttribute("username", customer.getCustomer_Username());
        request.setAttribute("description", customer.getCustomer_Description());
        request.setAttribute("phoneNumber", customer.getCustomer_PhoneNumber());
        request.setAttribute("emailAddress", customer.getCustomer_EmailAddress());
        request.setAttribute("dob", customer.getCustomer_DoB());
        request.setAttribute("profilePicture", customer.getCustomer_ProfilePicture());
        
        request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String validationMessage = validateCustomerForm(request);
			if (validationMessage != null) {
				System.out.println(validationMessage);
				handleError(request, response, validationMessage);
				return;
			}
			
			CustomerModel customer = updateCustomerFromRequest(request);
			if (customer == null) {
				handleError(request, response, "Failed to process customer information. Please try again.");
				return;
			}
			
			CustomerService customerService = new CustomerService();
			Boolean result = customerService.updateCustomer(customer);
			
			if (result != null && result) {
				// Reload the customer to get the updated information including profile picture
				CustomerModel updatedCustomer = customerService.getCustomerById(customer.getCustomerID());
				
				request.setAttribute("customer", updatedCustomer);
			    request.setAttribute("username", updatedCustomer.getCustomer_Username());
			    request.setAttribute("description", updatedCustomer.getCustomer_Description());
			    request.setAttribute("phoneNumber", updatedCustomer.getCustomer_PhoneNumber());
			    request.setAttribute("emailAddress", updatedCustomer.getCustomer_EmailAddress());
			    request.setAttribute("dob", updatedCustomer.getCustomer_DoB());
			    request.setAttribute("profilePicture", updatedCustomer.getCustomer_ProfilePicture());
			    List<PropertyModel> properties = null;
		        PropertyService ps = new PropertyService();
		        try {
					properties = ps.getPropertyByCustomer(customer.getCustomerID());
					System.out.print(properties);
					request.setAttribute("properties", properties);
				    request.setAttribute("propertiesCount", properties != null ? properties.size() : 0);
				    System.out.println("Loaded " + properties.size() + " properties for customer ID: " + customer.getCustomerID());
				} catch (SQLException e) {
					System.out.println("Error loading properties: " + e.getMessage());
		            e.printStackTrace();
		            request.setAttribute("error", "Error loading properties. Please try again.");
				}
				request.setAttribute("success", "The profile was successfully updated!");
                request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
			} else {
				handleError(request, response, "Failed to update user profile. Please try again.");
			}
		} catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
            handleError(request, response, "Please enter valid numbers");
        } catch (Exception e) {
            System.err.println("Unexpected error in updating user profile: " + e.getMessage());
            e.printStackTrace();
            handleError(request, response, "An unexpected error occurred: " + e.getMessage());
        }
	}
		
	private String validateCustomerForm(HttpServletRequest request) {
		String username = request.getParameter("name");
        String description = request.getParameter("description");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
       
        System.out.println("Validating Username: [" + username + "]");

		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(description))
			return "Description is required.";
		if (ValidationUtil.isNullOrEmpty(phoneNumber))
			return "Phone Number is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(dob))
			return "Date of Birth is required.";
		
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidPhoneNumber(phoneNumber))
			return "Phone number must be 10 digits and start with 98.";
		
		return null; // All validations passed
	}
	
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("description", req.getParameter("description"));
		req.setAttribute("phoneNumber", req.getParameter("phoneNumber"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("propertiesCount", 0); // Add this to avoid JSP errors
		req.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(req, resp);
	}	
	
	private CustomerModel updateCustomerFromRequest(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
		    Integer customerId = (Integer) session.getAttribute("customerId");
		    System.out.println("Customer ID: " + customerId);
		    
			String username = request.getParameter("name");
	        String description = request.getParameter("description");
	        String phoneNumber = request.getParameter("phoneNumber");
	        String email = request.getParameter("email");
	        String dob = request.getParameter("dob");
	        
	        // Get the current customer to preserve existing profile picture if no new one is uploaded
	        CustomerService customerService = new CustomerService();
	        CustomerModel existingCustomer = customerService.getCustomerById(customerId);
	        String imagePath = existingCustomer != null ? existingCustomer.getCustomer_ProfilePicture() : null;
	        
	        // Handle image upload
	        Part image = request.getPart("image");
	        if (image != null && image.getSize() > 0) {
	            String folder = "profiles"; // Changed from "property" to "profiles" for better organization
	            String savePath = request.getServletContext().getRealPath("/images/" + folder);
	            System.out.println("Attempting to save image to: " + savePath);
	            
	            boolean uploaded = imageUtil.uploadImage(image, savePath);
	            
	            if (uploaded) {
	                String imageName = imageUtil.getImageNameFromPart(image);
	                imagePath = "/images/" + folder + "/" + imageName;
	                System.out.println("Image uploaded successfully. Path: " + imagePath);
	            } else {
	                System.out.println("Image upload failed, keeping existing image");
	            }
	        } else {
	            System.out.println("No image provided, keeping existing image");
	        }
	        
	        CustomerModel customer = new CustomerModel();
	        customer.setCustomerID(customerId);
	        customer.setCustomer_Username(username);
	        customer.setCustomer_Description(description);
	        customer.setCustomer_PhoneNumber(phoneNumber);
	        customer.setCustomer_EmailAddress(email);
	        customer.setCustomer_DoB(dob);
	        customer.setCustomer_ProfilePicture(imagePath);
	        
	        return customer;
		} catch (Exception e) {
	        System.err.println("Unexpected error in updating profile: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
}