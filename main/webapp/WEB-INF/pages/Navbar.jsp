<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Navbar.css">

<%
String username = (String) session.getAttribute("username");
String profilePic = (String) session.getAttribute("profilePic");

// Improved handling of default profile picture
String profilePicPath;
if (profilePic == null || profilePic.trim().isEmpty()) {
    // Use the full path to default profile picture
    profilePicPath = "${pageContext.request.contextPath}/images/defaultpfp.jpg";
} else {
    // Use the profiles directory for user-specific pictures
    profilePicPath = "${pageContext.request.contextPath}/images/profiles/" + profilePic;
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
            <li>
                <a href="${pageContext.request.contextPath}/ViewPropertyController">
                    Properties <span class="dropdown-icon">&#9662;</span>
                </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/ContactUsController">Contact Us</a></li>
            <li><a href="${pageContext.request.contextPath}/AboutUsController">About Us</a></li>
            <li class="divider">|</li>
            <li>
                <div class="navbar-register-container">
                    <% if (username == null) { %>
                        <button class="navbar-register-button">
                            <a href="${pageContext.request.contextPath}/LoginController" class="NavbarItemsRightWhite">
                                Login / Sign Up
                            </a>
                            <img src="${pageContext.request.contextPath}/images/register.png" class="navbar-register-icon">
                        </button>
                    <% } else { %>
                        <a href="${pageContext.request.contextPath}/UserProfileController" class="navbar-user-link">
                            <div class="navbar-user-button">
                                <img src="<%= profilePicPath %>" class="navbar-user-icon" alt="Profile Image" onerror="this.src='${pageContext.request.contextPath}/images/defaultpfp.jpg'">
                                <span class="navbar-username">Welcome, <%= username %></span>
                                <span class="dropdown-icon">&#9662;</span>
                            </div>
                        </a>
                    <% } %>
                </div>
            </li>
        </ul>
    </div>
</div>