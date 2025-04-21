<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ContactUs.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<div class="navbarSection">
    <jsp:include page="navbar.jsp" />
</div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<section class="hero">
    <h1>Contact Us</h1>
    <p>Have question or need more information??</p>
</section>

<div class="content">
    <div class="illustration">
        <img src="${pageContext.request.contextPath}/images/Contact.png" alt="Contact Illustration">
    </div>

    <div class="contact-info">
        <h2>Get In Touch</h2>

        <div class="info-item">
            <div class="icon">
                <img src="${pageContext.request.contextPath}/images/location.png" alt="Location">
            </div>
            <div class="text">
                <h3>Our Office</h3>
                <p>Kathmandu, Nepal</p>
            </div>
        </div>

        <div class="info-item">
            <div class="icon">
                <img src="${pageContext.request.contextPath}/images/phone.png" alt="Phone">
            </div>
            <div class="text">
                <h3>Call Us</h3>
                <p>+977 9765153600</p>
            </div>
        </div>

        <div class="info-item">
            <div class="icon">
                <img src="${pageContext.request.contextPath}/images/email.png" alt="Email">
            </div>
            <div class="text">
                <h3>Email Us</h3>
                <p>nepalestate@gmail.com</p>
            </div>
        </div>
    </div>
</div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->



<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

</body>
</html>