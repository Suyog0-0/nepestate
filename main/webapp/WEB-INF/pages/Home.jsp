<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nepestate.model.PropertyModel" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NepeState - Find Your Dream Property</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
    <style>
        /* Additional styling to enhance the design */
    </style>
</head>
<body>
    <!-- NAVBAR -->
    <div class="navbarSection">
        <jsp:include page="Navbar.jsp" />
    </div>

    <!-- HERO SECTION -->
    <div class="heroSection">
        <div class="heroOverlay"></div>
        <div class="heroContent">
            <h1 class="heroTitle">Find the perfect home or investment property in Nepal with ease.</h1>
            <div class="heroSearchBar">
                <div class="searchInputContainer">
                    <div class="searchIconContainer">
                        <img src="${pageContext.request.contextPath}/images/searchIcon.png" alt="Search Icon" class="searchIcon">
                    </div>
                    <input type="text" placeholder="What are you searching for?" class="searchInput">
                </div>
            </div>
        </div>
    </div>

    <!-- FEATURED PROPERTIES -->
    <div class="featuredSection" id="featured">
        <h2 class="sectionTitle">Featured Listings</h2>
        <div class="propertyContainer">
            <c:forEach var="property" items="${featuredProperties}">
                <div class="propertyCard">
                    <div class="propertyImageContainer">
                        <img src="${pageContext.request.contextPath}/images/${property.property_Photos}" 
                             alt="${property.property_Type} for sale" class="propertyImage">
                        <div class="saleTag">SALE!</div>
                    </div>
                    <div class="propertyDetails">
                        <div class="propertyType">${property.property_Type}</div>
                        <div class="propertyPrice">Rs.${property.property_Price}</div>
                        <div class="propertyDescription">
                            <p>${property.property_Description}</p>
                        </div>
                        <div class="propertyLocation">${property.property_Address}</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- EXPLORE MORE PROPERTIES SECTION -->
    <div class="alternateSection" id="favourites">
        <div class="sectionPadding">
            <div class="sectionContainer">
                <h2 class="sectionTitle">Explore More Properties</h2>
                
                <div class="exploreGrid">
                    <c:forEach var="property" items="${moreProperties}">
                        <div class="propertyCardExpanded">
                            <div class="propertyImageWrapper">
                                <img src="${pageContext.request.contextPath}/images/${property.property_Photos}" alt="Property">
                                <div class="propertyBadge">NEW</div>
                            </div>
                            <div class="propertyCardContent">
                                <div class="propertyCategory">${property.property_Type}</div>
                                <h3 class="propertyTitle">${property.property_Title}</h3>
                                <div class="propertyPrice">Rs.${property.property_Price}</div>
                                <p class="propertyDesc">${property.property_Description}</p>
                                <div class="propertyFeatures">
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                
                <div style="text-align: center; margin-top: 40px;">
                    <a href="${pageContext.request.contextPath}/ViewPropertySPController" class="ctaButton">View Properties</a>
                </div>
            </div>
        </div>
    </div>

    <!-- ABOUT NEPESTATE SECTION -->
    <div class="sectionPadding" id="about">
        <div class="sectionContainer">
            <div class="aboutContent">
                <div class="aboutImageContainer">
                    <img src="${pageContext.request.contextPath}/images/figma.jpg" alt="About NepeState" class="aboutImage">
                </div>
                <div class="aboutTextContent">
                    <h2 class="sectionHeading">About NepeState</h2>
                    <p class="sectionDescription">
                        NepeState is Nepal's leading platform for buying, selling, and renting real estate. Founded in 2018, we've helped thousands of Nepalis find their dream homes and make smart property investments.
                    </p>
                    <p class="sectionDescription">
                        Whether you're a first-time buyer, a seasoned investor, or just exploring options, our extensive database of properties across Nepal and our team of experienced professionals are here to make your property journey smooth and successful.
                    </p>
                    <p class="sectionDescription">
                        Our mission is to simplify real estate transactions through technology, transparency, and trusted expertise.
                    </p>
                    <a href="${pageContext.request.contextPath}/AboutUsController" class="ctaButton">Learn More About Us</a>
                </div>
            </div>
        </div>
    </div>

    <!-- CONTACT US SECTION -->
    <div class="alternateSection" id="contact">
        <div class="sectionPadding">
            <div class="sectionContainer">
                <h2 class="sectionTitle">Contact Us</h2>
                
                <p style="text-align: center; max-width: 800px; margin: 0 auto 40px; color: #555; font-size: 16px; line-height: 1.6;">
                    Have questions about properties, pricing, or the buying process? Our team of experts is here to help you every step of the way. Reach out to us through any of these channels.
                </p>
                
                <div class="contactGrid">
                    <div class="contactCard">
                        <img src="${pageContext.request.contextPath}/images/emailIcon.png" alt="Email" class="contactIcon">
                        <h3 class="contactTitle">Email Us</h3>
                        <p class="contactInfo">
                            For general inquiries:<br>
                            <strong>info@nepestate.com</strong><br><br>
                            For business partnerships:<br>
                            <strong>business@nepestate.com</strong>
                        </p>
                    </div>
                    
                    <div class="contactCard">
                        <img src="${pageContext.request.contextPath}/images/phoneIcon.png" alt="Phone" class="contactIcon">
                        <h3 class="contactTitle">Call Us</h3>
                        <p class="contactInfo">
                            Customer Support:<br>
                            <strong>+977-1-2345678</strong><br><br>
                            Emergency Line:<br>
                            <strong>+977-9876543210</strong>
                        </p>
                    </div>
                    
                    <div class="contactCard">
                        <img src="${pageContext.request.contextPath}/images/locationIcon.png" alt="Location" class="contactIcon">
                        <h3 class="contactTitle">Visit Us</h3>
                        <p class="contactInfo">
                            NepeState Headquarters<br>
                            Durbar Marg, Kathmandu<br>
                            Nepal, 44600<br><br>
                            <strong>Open: Mon-Fri, 9AM-5PM</strong>
                        </p>
                    </div>
                </div>
                
                <div style="text-align: center; margin-top: 40px;">
                    <a href="${pageContext.request.contextPath}/ContactUsController" class="ctaButton">Send Us a Message</a>
                </div>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>