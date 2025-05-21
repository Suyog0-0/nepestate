package com.nepestate.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nepestate.model.AdminModel;
import com.nepestate.model.CustomerModel;
import com.nepestate.service.CustomerService;
import com.nepestate.service.ReportGenerationService;

/**
 * Servlet implementation class ReportGenerationController
 */
@WebServlet("/ReportGenerationController")
public class ReportGenerationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerService customerService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGenerationController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<CustomerModel> customerList = new ArrayList<>();
        String userType = "guest";
        
        try {
            // Check if session exists
            if (session == null) {
                System.out.println("No session found - redirecting to login");
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
                return;
            }

            // Initialize CustomerService
            customerService = new CustomerService();

            // Check user type from session
            AdminModel admin = (AdminModel) session.getAttribute("loggedInAdmin");
            CustomerModel customer = (CustomerModel) session.getAttribute("loggedInCustomer");
            
            // Get the report type from request
            String reportType = request.getParameter("reportType");
            boolean generateReport = reportType != null && !reportType.isEmpty();
            
            if (admin != null) {
                // Admin user
                System.out.println("Admin logged in: " + admin.getAdmin_Username());
                userType = "admin";
                
                // Only generate report if requested
                if (generateReport) {
                    ReportGenerationService reportGenerationService = new ReportGenerationService();
                    Map<String, Object> reportData = new HashMap<>();
                    reportGenerationService.generateAdminPropertyStatistics(reportData);
                    
                    // Set the report data in the request
                    request.setAttribute("reportData", reportData);
                    request.setAttribute("reportGenerated", true);
                }
                
                // Common attributes
                request.setAttribute("CustomerList", customerList);
                request.setAttribute("userType", userType);
                
                System.out.println("Forwarding to ReportGenerationAdmin.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/ReportGenerationAdmin.jsp");
                dispatcher.forward(request, response);
                
            } else if (customer != null) {
                // Customer user
                System.out.println("Customer logged in: " + customer.getCustomer_Username() + 
                                 " with ID: " + customer.getCustomerID());
                userType = "customer";
                
                // Only generate report if requested
                if (generateReport) {
                    ReportGenerationService reportGenerationService = new ReportGenerationService();
                    Map<String, Object> reportData = new HashMap<>();
                    reportGenerationService.generateCustomerPropertyStatistics(reportData, customer.getCustomerID());
                    
                    // Set the report data in the request
                    request.setAttribute("reportData", reportData);
                    request.setAttribute("reportGenerated", true);
                }
                
                // Common attributes
                request.setAttribute("CustomerList", customerList);
                request.setAttribute("userType", userType);
                request.setAttribute("customerId", customer.getCustomerID());
                request.setAttribute("customerUsername", customer.getCustomer_Username());
                
                System.out.println("Forwarding to ReportGenerationUser.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/ReportGenerationUser.jsp");
                dispatcher.forward(request, response);
                
            } else {
                // No valid user found in session
                System.out.println("No valid user session found - redirecting to login");
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
                return;
            }

        } catch (Exception e) {
            System.out.println("Exception in ReportGenerationController: " + e.getMessage());
            e.printStackTrace();

            // Set error attributes
            request.setAttribute("errorMessage", "There was an error generating the report: " + e.getMessage());
            request.setAttribute("technicalDetails", e.toString());
            request.setAttribute("userType", userType);

            // Forward to error page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/Error.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            } else {
                // Fallback if error page doesn't exist
                response.setContentType("text/html");
                response.getWriter().println("Error: " + e.getMessage());
            }
        } finally {
            System.out.println("ReportGenerationController: Request processing completed");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}