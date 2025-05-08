package com.nepestate.controller;

import com.nepestate.model.CustomerModel;
import com.nepestate.service.RegisterService;
import com.nepestate.util.PasswordUtil;
import com.nepestate.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Part;

import java.io.IOException;
//import java.time.LocalDate;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Received firstName: " + req.getParameter("firstName"));
			String validationMessage = validateRegistrationForm(req);
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
				}
			else {
				CustomerModel customer = createCustomerFromRequest(req);
				RegisterService registerService = new RegisterService();
				Boolean result = registerService.addCustomer(customer);
				
				if (result != null && result) {
					handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/Login.jsp");
					req.setAttribute("success", "Registration successful!");
//					req.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(req, resp);
				} else {
					handleError(req, resp, "Failed to register. Please try again.");
				}
			}
		}
			catch (Exception e) {
				 System.err.println("Unexpected error in registration: " + e.getMessage());
			        e.printStackTrace();
			        handleError(req, resp, "An unexpected error occurred: " + e.getMessage());
						}
					}
		// TODO Auto-generated method stub
		private String validateRegistrationForm(HttpServletRequest req) {
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String username = req.getParameter("username");
//			String gender = req.getParameter("gender");
			String email = req.getParameter("email");
			String number = req.getParameter("phone");
			String password = req.getParameter("password");
			String retypePassword = req.getParameter("reTypePassword");
			
			System.out.println("Validating firstName: [" + firstName + "]");

			// Check for null or empty fields first
			if (ValidationUtil.isNullOrEmpty(firstName))
				return "First name is required.";
			if (ValidationUtil.isNullOrEmpty(lastName))
				return "Last name is required.";
			if (ValidationUtil.isNullOrEmpty(username))
				return "Username is required.";
//			if (ValidationUtil.isNullOrEmpty(dobStr))
//				return "Date of birth is required.";
//			if (ValidationUtil.isNullOrEmpty(gender))
//				return "Gender is required.";
			if (ValidationUtil.isNullOrEmpty(email))
				return "Email is required.";
			if (ValidationUtil.isNullOrEmpty(number))
				return "Phone number is required.";
			if (ValidationUtil.isNullOrEmpty(password))
				return "Password is required.";
			if (ValidationUtil.isNullOrEmpty(retypePassword))
				return "Please retype the password.";

			// Convert date of birth
//			LocalDate dob;
//			try {
//				dob = LocalDate.parse(dobStr);
//			} catch (Exception e) {
//				return "Invalid date format. Please use YYYY-MM-DD.";
//			}

			// Validate fields
			if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
				return "Username must start with a letter and contain only letters and numbers.";
//			if (!ValidationUtil.isValidGender(gender))
//				return "Gender must be 'male' or 'female'.";
			if (!ValidationUtil.isValidEmail(email))
				return "Invalid email format.";
			if (!ValidationUtil.isValidPhoneNumber(number))
				return "Phone number must be 10 digits and start with 98.";
			if (!ValidationUtil.isValidPassword(password))
				return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
			if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
				return "Passwords do not match.";
			
			
			if (!ValidationUtil.isAlphabetic(firstName))
				return "Firstname must contain letters and no numeric digits.";
			if (!ValidationUtil.isAlphabetic(lastName))
				return "Lastname must contain letters and no numeric digits.";

			// Check if the date of birth is at least 16 years before today
//			if (!ValidationUtil.isAgeAtLeast16(dob))
//				return "You must be at least 16 years old to register.";
//
		return null; // All validations passed
		}
		private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
				throws ServletException, IOException {
			req.setAttribute("success", message);
			req.getRequestDispatcher(redirectPage).forward(req, resp);
		}
			private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
					throws ServletException, IOException {
				req.setAttribute("error", message);
				req.setAttribute("firstName", req.getParameter("firstName"));
				req.setAttribute("lastName", req.getParameter("lastName"));
				req.setAttribute("username", req.getParameter("username"));
//				req.setAttribute("dob", req.getParameter("dob"));
//				req.setAttribute("gender", req.getParameter("gender"));
				req.setAttribute("email", req.getParameter("email"));
				req.setAttribute("phone", req.getParameter("phone"));
				req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
			}
			
			private CustomerModel createCustomerFromRequest(HttpServletRequest req) {
				CustomerModel customer = new CustomerModel();
				customer.setCustomer_FirstName(req.getParameter("firstName"));
				customer.setCustomer_LastName(req.getParameter("lastName"));
				String username=req.getParameter("username");
				customer.setCustomer_Username(req.getParameter("username"));
				customer.setCustomer_EmailAddress(req.getParameter("email"));
				String password=req.getParameter("password");
				customer.setCustomer_PhoneNumber(req.getParameter("phone"));
				password=PasswordUtil.encrypt(username,password);
				System.out.println(password);
				customer.setCustomer_Password(password);
				
				// Set default profile picture path or handle file upload
				customer.setCustomer_ProfilePicture("defaul.jpg");
				customer.setCustomer_DoB("21-07-2006");
				
				return customer;
			}
			
		
	}


