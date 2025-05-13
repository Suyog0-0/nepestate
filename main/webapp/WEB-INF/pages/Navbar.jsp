<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.nepestate.service.CustomerService" %>
<%@ page import="com.nepestate.model.CustomerModel" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Navbar.css">

<%
    // Get session attributes
    String username = (String) session.getAttribute("username");
    Integer customerId = (Integer) session.getAttribute("customerId");

    // Initialize profile path
    String profilePicPath = "${pageContext.request.contextPath}/images/defaultpfp.jpg";

    // Check database for current profile picture
    if (username != null && customerId != null) {
        try {
            // Database fetch
            CustomerService customerService = new CustomerService();
            CustomerModel customer = customerService.getCustomerById(customerId);

            if (customer != null) {
                String dbProfilePic = customer.getCustomer_ProfilePicture();

                // Validate picture
                if (dbProfilePic != null && !dbProfilePic.trim().isEmpty()) {
                    profilePicPath = "${pageContext.request.contextPath}/images/" + dbProfilePic;
                }
            }
        } catch (Exception e) {
            // Error logging
            System.err.println("Error fetching profile picture: " + e.getMessage());
            // Keep default picture on error
        }
    }
%>

<div class="navbar-container">
    <div class="navbar-left">
        <a href="${pageContext.request.contextPath}/HomeController" class="navbar-logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" class="navbar-logo-image">
        </a>
    </div>

    <div class="navbar-center">
        <div class="navbar-search-container">
            <img src="${pageContext.request.contextPath}/images/searchIcon.png" class="navbar-search-icon">
            <input type="text" class="navbar-search-bar" placeholder="What are you looking for?">
        </div>
    </div>

    <div class="navbar-right">
        <ul class="navbar-items">


            <li><a href="${pageContext.request.contextPath}/HomeController">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/ViewPropertyController">Properties <span class="dropdown-icon">▾</span></a></li>
            <li><a href="${pageContext.request.contextPath}/ContactUsController">Contact Us</a></li>
            <li><a href="${pageContext.request.contextPath}/AboutUsController">About Us</a></li>
            <!-- Favourite Icon before the divider -->
            <li>
                <a href="${pageContext.request.contextPath}/FavouriteController" class="navbar-favourite-icon-link">
                    <img src="${pageContext.request.contextPath}/images/favourite.png" class="navbar-favourite-icon">
                </a>
            </li>
            <li class="divider">|</li>
            
            <li>
                <div class="navbar-register-container">
                    <% if (username == null) { %>
<button class="navbar-register-button">
<a href="${pageContext.request.contextPath}/LoginController" class="navbar-items-right-white">Login / Sign Up</a>
    <img src="${pageContext.request.contextPath}/images/register.png" class="navbar-register-icon">
</button>
                    <% } else { %>
                        <a href="${pageContext.request.contextPath}/UserProfileController" class="navbar-user-link">
                            <div class="navbar-user-button">
                                <img src="<%= profilePicPath %>" class="navbar-user-icon" alt="Profile Image" onerror="this.src='${pageContext.request.contextPath}/images/defaultpfp.jpg'">
                                <span class="navbar-username">Welcome, <%= username %></span>
                                <span class="dropdown-icon">▾</span>
                            </div>
                        </a>
                    <% } %>
                </div>
            </li>
        </ul>
    </div>
</div>
