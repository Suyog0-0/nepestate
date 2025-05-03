<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ViewPropertySP.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
    <!-- Navbar Include -->
    <div class="navbarSection">
        <jsp:include page="Navbar.jsp" />
    </div>

    <!-- Main Content -->
    <div class="centerSectionWrapper">
        <div class="centerSection">
            <!-- Product Container -->
            <div class="productContainer">
                <div class="productContent">
                    <div class="price">Rs. 2,56,00,000</div>
                    <div class="location">Thamel, Kathmandu</div>
                    
                    <!-- Available/Not Available Section -->
                    <div class="availabilitySection">
                        <div class="availableOption">
                            <span class="availableText">Available</span>
                        </div>
                        <div class="notAvailableOption">
                            <span class="notAvailableText">Not Available</span>
                        </div>
                    </div>
                    
                    <!-- Image Gallery -->
                    <div class="imageContainer">
                        <img src="${pageContext.request.contextPath}/images/house1.jpeg" class="house1">
                        <img src="${pageContext.request.contextPath}/images/house2.jpg" class="house2">
                        <img src="${pageContext.request.contextPath}/images/house3.jpeg" class="house3">
                        <img src="${pageContext.request.contextPath}/images/house4.jpg" class="house4">
                        <img src="${pageContext.request.contextPath}/images/house5.jpeg" class="house5">
                    </div>
                    
                    <!-- Property Details -->
                    <div class="housingDetails">
                        <div class="type">
                            <div class="typeHeading">Type</div>
                            <div class="typeText">Housing</div>
                        </div>
                        <div class="area">
                            <div class="areaHeading">Area</div>
                            <div class="areaText">20 Aana of Land</div>
                        </div>
                        <div class="totalUnits">
                            <div class="totalUnitsHeading">Total Units</div>
                            <div class="totalUnitsText">2</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Profile Container -->
            <div class="profileContainer">
                <div class="profileDetailsContainer">
                    <img src="${pageContext.request.contextPath}/images/profile.png" class="profileImage">
                    <div class="profileName">Durgesh Thapa</div>
                    <div class="profileContact">
                        <div class="profileNumber">
                            <img src="${pageContext.request.contextPath}/images/phone.png" class="phoneIcon">
                            +977 9812222284
                        </div>
                        <div class="profileEmail">
                            <img src="${pageContext.request.contextPath}/images/mail.png" class="mailIcon">
                            durgesh45@gmail.com
                        </div>
                    </div>
                </div>
                
                <div class="profileButtonContainer">
                    <button class="buyNowButton">Buy Now</button>
                    <button class="bookmarkButton">Bookmark</button>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bottom Section -->
    <div class="bottomSectionWrapper">
        <div class="bottomSection">
            <!-- About Container -->
            <div class="aboutContainer">
                <div class="aboutHeading">About</div>
                <div class="aboutText">
                    Downtown Residency is a premium housing development located in the heart of Thamel, Kathmandu. Designed for modern living, this project combines convenience, comfort, and style in one of the city's most vibrant neighborhoods. With a total of 7 thoughtfully designed units, Downtown Residency offers the perfect balance between urban living and comfort.
                </div>
                <div class="aboutText">
                    Whether you're a family looking for a forever home or an investor seeking value in Kathmandu's real estate market, Downtown Residency delivers on location, design, and quality construction.
                </div>
            </div>
            
            
            <!-- Features Container -->
            <div class="featureContainer">
                <div class="featureHeading">Features</div>
                <div class="featureImages">
                    <div class="featureItem">
                        <img src="${pageContext.request.contextPath}/images/feature1.png" class="featureImage1">
                        <div class="featureName">Swimming</div>
                    </div>
                    <div class="featureItem">
                        <img src="${pageContext.request.contextPath}/images/feature2.png" class="featureImage2">
                        <div class="featureName">Gym</div>
                    </div>
                    <div class="featureItem">
                        <img src="${pageContext.request.contextPath}/images/feature3.png" class="featureImage3">
                        <div class="featureName">Parking</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Footer Include -->
    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>
</body>

</html>