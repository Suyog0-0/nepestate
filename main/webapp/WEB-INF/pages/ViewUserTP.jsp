<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Real Estate Listings</title>
    <!-- Added CSS links -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ViewUserTP.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    <div class="navbarSection">
        <!-- Code for Including the navbar -->
        <jsp:include page="Navbar.jsp" />
    </div>
    
    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    <div class="container">
        <div class="profile-card">
            <div class="profile-img">
                <img src="${pageContext.request.contextPath}/images/profile.jpg" alt="Profile Picture">
            </div>
            <div class="profile-info">
                <h3>Durgesh Thapa</h3>
                <p class="phone">+977 9841222684</p>
                <p class="email">durgeshthapa@gmail.com</p>
            </div>
        </div>

        <!-- Listings Section -->
        <div class="listings-container">
            <h1 class="listings-title">Listed Properties (12)</h1>
            
            <div class="listings-grid">
                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/house1.jpg" alt="House">
                    </div>
                    <div class="property-badge house">House</div>
                    <div class="property-info">
                        <p class="property-price">Rs.1,20,00,000</p>
                        <p class="property-desc">2 storey residential house with 3 rooms and attached bathroom</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/house2.jpg" alt="House">
                    </div>
                    <div class="property-badge house">House</div>
                    <div class="property-info">
                        <p class="property-price">Rs.50,00,000</p>
                        <p class="property-desc">Wooden architecture with retro appeal</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/land1.jpg" alt="Land">
                    </div>
                    <div class="property-badge land">Land</div>
                    <div class="property-info">
                        <p class="property-price">Rs.1,75,00,000</p>
                        <p class="property-desc">600 sq ft residential colony with modern homes and central surveillance</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/apt1.jpg" alt="Apartment">
                    </div>
                    <div class="property-badge apartment">Apartment</div>
                    <div class="property-info">
                        <p class="property-price">Rs.3,20,00,000</p>
                        <p class="property-desc">Modern design, spacious 3 rooms and attached bathroom</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/house1.jpg" alt="House">
                    </div>
                    <div class="property-badge house">House</div>
                    <div class="property-info">
                        <p class="property-price">Rs.1,20,00,000</p>
                        <p class="property-desc">2 storey residential house with 3 rooms and attached bathroom</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/house2.jpg" alt="House">
                    </div>
                    <div class="property-badge house">House</div>
                    <div class="property-info">
                        <p class="property-price">Rs.50,00,000</p>
                        <p class="property-desc">Wooden architecture with retro appeal</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/land1.jpg" alt="Land">
                    </div>
                    <div class="property-badge land">Land</div>
                    <div class="property-info">
                        <p class="property-price">Rs.1,75,00,000</p>
                        <p class="property-desc">600 sq ft residential colony with modern homes and central surveillance</p>
                    </div>
                </div>

                <div class="property-card">
                    <div class="property-img">
                        <img src="${pageContext.request.contextPath}/images/apt1.jpg" alt="Apartment">
                    </div>
                    <div class="property-badge apartment">Apartment</div>
                    <div class="property-info">
                        <p class="property-price">Rs.3,20,00,000</p>
                        <p class="property-desc">Modern design, spacious 3 rooms and attached bathroom</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>

    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
    
</body>
</html>