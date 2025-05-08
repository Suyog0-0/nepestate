package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.nepestate.model.CustomerModel;
import com.nepestate.model.PropertyModel;
import com.nepestate.service.CustomerService;
import com.nepestate.util.ValidationUtil;

/**
 * Servlet implementation class UserProfileController
 */
@WebServlet("/UserProfileController")
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileController() {
        super();
        // TODO Auto-generated constructor stub
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
	        
	       
		 	CustomerService customerService=new CustomerService();
	        CustomerModel customer = customerService.getCustomerById(customerId);
	        
	        if (customer == null) {
	            
	            session.invalidate();
	            response.sendRedirect(request.getContextPath() + "/LoginController");
	            return;
	        }
	        
	        if (customer !=null){
	        	  request.setAttribute("customer", customer);
	        	  request.setAttribute("username", customer.getCustomer_Username());
	        	  request.setAttribute("description", customer.getCustomer_Description());
	        	  request.setAttribute("phoneNumber", customer.getCustomer_PhoneNumber());
	        	  request.setAttribute("emailAddress", customer.getCustomer_EmailAddress());
	        	  request.setAttribute("dob", customer.getCustomer_DoB());
	        	  request.setAttribute("profilePicture", customer.getCustomer_ProfilePicture());
	   
	        }
	
	        request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String validationMessage = validateCustomerForm(request);
			if (validationMessage != null) {
				System.out.println(validationMessage);
				handleError(request, response, validationMessage);
				return;
				}		
			else {
			CustomerModel customer=updateCustomerFromRequest(request);
			CustomerService customerService =new CustomerService();
			Boolean result =customerService.updateCustomer(customer);
			if (result != null && result) {
				request.setAttribute("customer", customer);
			    request.setAttribute("username", customer.getCustomer_Username());
			    request.setAttribute("description", customer.getCustomer_Description());
			    request.setAttribute("phoneNumber", customer.getCustomer_PhoneNumber());
			    request.setAttribute("emailAddress", customer.getCustomer_EmailAddress());
			    request.setAttribute("dob", customer.getCustomer_DoB());
				request.setAttribute("success", "The profile was successfully updated!");
                request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
			} else {
				handleError(request, response, "Failed to update user profile. Please try again.");
			}
			}
		}catch (NumberFormatException e) {
	            System.err.println("Invalid number format: " + e.getMessage());
	            handleError(request, response, "Please enter valid numbers ");
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
       
        
        System.out.println("Validating Username: [" + username+ "]");
        

		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(description))
			return "Description is required.";
		if (ValidationUtil.isNullOrEmpty(phoneNumber))
			return "Phone Number is requiredlogin.";
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
		req.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(req, resp);
	
	}	
	
	private CustomerModel updateCustomerFromRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
	    Integer customerId = (Integer) session.getAttribute("customerId");
	    System.out.println(customerId);
		String username = request.getParameter("name");
        String description = request.getParameter("description");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        CustomerModel customer=new CustomerModel();
        customer.setCustomerID(customerId);
        customer.setCustomer_Username(username);
        customer.setCustomer_Description(description);
        customer.setCustomer_PhoneNumber(phoneNumber);
        customer.setCustomer_EmailAddress(email);
        customer.setCustomer_DoB(dob);
        return customer;
		
	}
}


