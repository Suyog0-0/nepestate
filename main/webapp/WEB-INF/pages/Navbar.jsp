<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Navbar.css">

<div class="navbar-container">
    <div class="navbar-left">
        <div class="navbar-logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" class="navbar-logo-image">
        </div>
    </div>

    <div class="navbar-center">
        <div class="navbar-search-container">
            <img src="${pageContext.request.contextPath}/images/searchIcon.png" class="navbar-search-icon">
            <input type="text" class="navbar-search-bar" placeholder="What are you looking for?">
        </div>
    </div>

    <div class="navbar-right">
        <ul class="navbar-items">
			<li><a href="${pageContext.request.contextPath}/home">Home</a></li>            
			<li><a href="${pageContext.request.contextPath}/">Properties</a></li>
			<li><a href="${pageContext.request.contextPath}/ContactUs">Contact Us</a></li>
            <li><a href="${pageContext.request.contextPath}AboutUs">About Us</a></li>
            <li><a href="#">|</a></li>
            <li>
                <div class="navbar-register-container">
                    <button class="navbar-register-button">Register/SignUp</button>
                    <img src="${pageContext.request.contextPath}/images/register.png" class="navbar-register-icon">
                </div>
            </li>
        </ul>
    </div>
</div>
