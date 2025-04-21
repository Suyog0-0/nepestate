<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/ContactUs.css">
</head>
<body>
    <header>
        <div class="logo">
            <img src="Logo.png" alt="nepestate">
        </div>
        <div class="search-bar">
            <input type="text" placeholder="What are you searching for?">
            <button><img src="search-icon.png" alt="Search"></button>
        </div>
        <nav>
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">Properties</a></li>
                <li><a href="#">Contact Us</a></li>
                <li><a href="#">About Us</a></li>
            </ul>
        </nav>
        <div class="icons">
            <a href="#"><img src="icon1.png" alt=""></a>
            <a href="#"><img src="icon2.png" alt=""></a>
        </div>
    </header>

    <section class="hero">
        <h1>Contact Us</h1>
        <p>Have question or need more information??</p>
    </section>

    <div class="content">
        <div class="illustration">
            <img src="Contact.png" alt="Contact Illustration">
        </div>

        <div class="contact-info">
            <h2>Get In Touch</h2>
            
            <div class="info-item">
                <div class="icon">
                    <img src="location-icon.png" alt="Location">
                </div>
                <div class="text">
                    <h3>Our Office</h3>
                    <p>Kathmandu, Nepal</p>
                </div>
            </div>
            
            <div class="info-item">
                <div class="icon">
                    <img src="phone-icon.png" alt="Phone">
                </div>
                <div class="text">
                    <h3>Call Us</h3>
                    <p>+977 9765153600</p>
                </div>
            </div>
            
            <div class="info-item">
                <div class="icon">
                    <img src="email-icon.png" alt="Email">
                </div>
                <div class="text">
                    <h3>Email Us</h3>
                    <p>nepalestate@gmail.com</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>