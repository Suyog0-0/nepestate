<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/ContactUs.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/Navbar.css" />
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/Footer.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

    <!-- Navbar -->
    <div class="navbarSection">
        <jsp:include page="Navbar.jsp" />
    </div>

    <!-- Hero Section -->
    <section class="contact-hero">
        <div class="container">
            <h1>Contact Us</h1>
            <p>Have questions or need more information?</p>
        </div>
    </section>

    <!-- Contact Content -->
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
                        <p>+977 9110011900</p>
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

    <!-- Footer -->
    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>

</body>
</html>