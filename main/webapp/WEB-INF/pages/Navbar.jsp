<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Navbar.css">

<div class="navbar-container">
    <div class="navbar-left">
        <div class="navbar-logo">
            <a href="${pageContext.request.contextPath}/HomeController">
                <img src="${pageContext.request.contextPath}/images/logo.png" class="navbar-logo-image" alt="Logo">
            </a>
        </div>
    </div>

    <div class="navbar-center">
        <div class="navbar-search-container">
            <img src="${pageContext.request.contextPath}/images/searchIcon.png" class="navbar-search-icon" alt="Search">
            <input type="text" class="navbar-search-bar" placeholder="What are you looking for?">
        </div>
    </div>

    <div class="navbar-right">
        <ul class="navbar-items">
            <li><a href="${pageContext.request.contextPath}/HomeController">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/ViewPropertyController">Properties</a></li>
            <li><a href="${pageContext.request.contextPath}/ContactUsController">Contact Us</a></li>
            <li><a href="${pageContext.request.contextPath}/AboutUsController">About Us</a></li>
            <li class="divider">|</li>
            <li>
                <div class="navbar-register-container">
                    <% if (session.getAttribute("username") == null) { %>
                        <button class="navbar-register-button">
                            <a href="${pageContext.request.contextPath}/RegisterController">
                                Register/SignUp
                            </a>
                            <img src="${pageContext.request.contextPath}/images/register.png" class="navbar-register-icon" alt="Register">
                        </button>
                    <% } else { %>
                        <button class="navbar-user-button">
                             <a href="${pageContext.request.contextPath}/UserProfileController"><span>Welcome, <%= session.getAttribute("username") %></span> </a>
                        </button>

                    <% } %>
                </div>
            </li>
        </ul>
    </div>
</div>