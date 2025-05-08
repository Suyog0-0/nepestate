<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nepestate.service.CustomerService" %>
<%@ page import="com.nepestate.model.CustomerModel" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserSidebar.css">

<%
// Get session attributes
String username = (String) session.getAttribute("username");
String userEmail = (String) session.getAttribute("userEmail");
Integer customerId = (Integer) session.getAttribute("customerId");

// Initialize variables
String profilePicPath = "${pageContext.request.contextPath}/images/defaultpfp.jpg";
String firstName = "";
String lastName = "";
String phoneNumber = "";

// Check database for user details
if (username != null && customerId != null) {
    try {
        // Database fetch
        CustomerService customerService = new CustomerService();
        CustomerModel customer = customerService.getCustomerById(customerId);
        
        if (customer != null) {
            // Get profile picture
            String dbProfilePic = customer.getCustomer_ProfilePicture();
            if (dbProfilePic != null && !dbProfilePic.trim().isEmpty()) {
                profilePicPath = "${pageContext.request.contextPath}/images/profiles/" + dbProfilePic;
            }
            
            // Get user details
            firstName = customer.getCustomer_FirstName();
            lastName = customer.getCustomer_LastName();
            phoneNumber = customer.getCustomer_PhoneNumber();
            
            // Use email from DB if session email is empty
            if (userEmail == null || userEmail.trim().isEmpty()) {
                userEmail = customer.getCustomer_EmailAddress();
            }
        }
    } catch (Exception e) {
        // Error logging
        System.err.println("Error fetching user details: " + e.getMessage());
    }
}
%>

<div class="sidebar">
    <div class="profile-section">
        <img src="<%= profilePicPath %>" alt="Profile" class="profile-img" 
             onerror="this.src='${pageContext.request.contextPath}/images/defaultpfp.jpg'"/>
        <p class="role">(User)</p>
        <h2><%= firstName + " " + lastName %></h2>
        <p class="phone"><%= phoneNumber %></p>
        <!-- Dynamic email -->
        <p class="email"><%= userEmail %></p>
        <div class="blue-line"></div>
    </div>

    <nav class="menu">
        <ul>
            <li class="menu-item">
                <img src="${pageContext.request.contextPath}/images/users.png" class="icon"/>
                <a href="${pageContext.request.contextPath}/UserProfileController" class="nav-link">
                    My profile
                </a>
            </li>

            <li class="dropdown-container">
                <div class="menu-item dropdown-header">
                    <img src="${pageContext.request.contextPath}/images/down-icon.png" class="icon"/>
                    <a href="${pageContext.request.contextPath}/UserDashboardController" class="nav-link">
                        Dashboard
                    </a>
                </div>
                <ul class="submenu">
                    <li class="submenu-item">
                        <img src="${pageContext.request.contextPath}/images/properties.png" class="icon"/>
                        <a href="${pageContext.request.contextPath}/PropertyListingController" class="nav-link">
                            Properties
                        </a>
                    </li>
                    <li class="submenu-item">
                        <img src="${pageContext.request.contextPath}/images/users.png" class="icon"/>
                        <a href="${pageContext.request.contextPath}/ContactListingController" class="nav-link">
                            Contact
                        </a>
                    </li>
                    <li class="submenu-item">
                        <img src="${pageContext.request.contextPath}/images/report.png" class="icon"/>
                        <a href="${pageContext.request.contextPath}/ReportGenerationController" class="nav-link">
                            Reports
                        </a>
                    </li>
                </ul>
            </li>

            <li class="menu-item">
                <img src="${pageContext.request.contextPath}/images/white_favourite.png" class="icon"/>
                <a href="${pageContext.request.contextPath}/FavouriteController" class="nav-link">
                    Favorites
                </a>
            </li>

            <li class="menu-item logout">
                <img src="${pageContext.request.contextPath}/images/logout.png" class="icon"/>
                <a href="${pageContext.request.contextPath}/Logout" class="nav-link-logout">
                    Log Out
                </a>
            </li>
        </ul>
    </nav>
</div>

<script>
document.addEventListener('DOMContentLoaded', () => {
    const dropdown = document.querySelector('.dropdown-header');
    dropdown.addEventListener('click', () => {
        dropdown.parentElement.classList.toggle('open');
    });
});
</script>