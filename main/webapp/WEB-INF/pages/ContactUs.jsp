<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ContactUs.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	
<body>
    <header>
        <div class="container header-container">
            <div class="logo">
                <img src="Logo.png" alt="Logo">
            </div>
            <div class="search-bar">
                <i class="fas fa-search"></i>
                <input type="text" placeholder="What are you searching for?">
            </div>
            <nav>
                <ul>
                    <li><a href="index.html">Home</a></li>
                    <li><a href="properties.html">Properties</a></li>
                    <li><a href="contact.html" class="active">Contact Us</a></li>
                    <li><a href="about.html">About Us</a></li>
                </ul>
            </nav>
            <div class="social-icons">
                <a href="#" class="icon"><img src="mail-icon.png" alt="Mail"></a>
                <a href="#" class="icon"><img src="phone-icon.png" alt="Phone"></a>
            </div>
        </div>
    </header>

    <section class="contact-hero">
        <div class="container">
            <h1>Contact Us</h1>
            <p>Have question or need more information??</p>
        </div>
    </section>

    <section class="contact-content">
        <div class="container">
            <div class="contact-illustration">
                <img src="Contact.png" alt="Contact Illustration">
            </div>
            <div class="contact-info">
                <h2>Get In Touch</h2>
                <div class="info-item">
                    <div class="icon-box">
                        <i class="fas fa-map-marker-alt"></i>
                    </div>
                    <div class="info-text">
                        <h3>Our Office</h3>
                        <p>Baneshwor, Kathmandu, Nepal</p>
                    </div>
                </div>
                <div class="info-item">
                    <div class="icon-box">
                        <i class="fas fa-phone-alt"></i>
                    </div>
                    <div class="info-text">
                        <h3>Call Us</h3>
                        <p>+ 977 9110011900</p>
                    </div>
                </div>
                <div class="info-item">
                    <div class="icon-box">
                        <i class="far fa-envelope"></i>
                    </div>
                    <div class="info-text">
                        <h3>Email Us</h3>
                        <p>nepestale@gmail.com</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>