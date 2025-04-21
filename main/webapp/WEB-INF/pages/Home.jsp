<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NepeState - Find Your Dream Property</title>
    <!-- Added CSS links -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Home.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    <div class="navbarSection">
        <!-- Code for Including the navbar -->
        <jsp:include page="navbar.jsp" />
    </div>
    
    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    

    <!-- Hero Section -->
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

    <!-- Featured Listings Section -->
    <div class="featuredSection">
        <h2 class="sectionTitle">Featured Listing</h2>
        
        <div class="propertyContainer">
            <!-- Property Card 1 -->
            <div class="propertyCard">
                <div class="propertyImageContainer">
                    <img src="${pageContext.request.contextPath}/images/house1.jpeg" alt="House for sale" class="propertyImage">
                    <div class="saleTag">SALE!</div>
                </div>
                <div class="propertyDetails">
                    <div class="propertyType">Home</div>
                    <div class="propertyPrice">Rs.1,20,00,000</div>
                    <div class="propertyDescription">
                        <img src="${pageContext.request.contextPath}/images/house-icon.png" alt="House Icon" class="descIcon">
                        <p>🏡 Beautiful home for sale in a scenic and peaceful location!</p>
                    </div>
                    <div class="propertyLocation">Kamalpaokhari, Kathmandu</div>
                </div>
            </div>

            <!-- Property Card 2 -->
            <div class="propertyCard">
                <div class="propertyImageContainer">
                    <img src="${pageContext.request.contextPath}/images/house2.jpg" alt="Apartment for sale" class="propertyImage">
                    <div class="saleTag">SALE!</div>
                </div>
                <div class="propertyDetails">
                    <div class="propertyType">Apartment</div>
                    <div class="propertyPrice">Rs.3,20,00,000</div>
                    <div class="propertyDescription">
                        <img src="${pageContext.request.contextPath}/images/apartment-icon.png" alt="Apartment Icon" class="descIcon">
                        <p>🏢 A modern and spacious apartment in a prime residential area.</p>
                    </div>
                    <div class="propertyLocation">Munipokhari, Kathmandu</div>
                </div>
            </div>

            <!-- Property Card 3 -->
            <div class="propertyCard">
                <div class="propertyImageContainer">
                    <img src="${pageContext.request.contextPath}/images/house3.jpeg" alt="Colony house for sale" class="propertyImage">
                    <div class="saleTag">SALE!</div>
                </div>
                <div class="propertyDetails">
                	    <div class="propertyType">Colony</div>
                    <div class="propertyPrice">Rs.1,75,00,000</div>
                    <div class="propertyDescription">
                        <img src="${pageContext.request.contextPath}/images/colony-icon.png" alt="Colony Icon" class="descIcon">
                        <p>🏘️ A well-planned residential colony with modern homes and serene surroundings.</p>
                    </div>
                    <div class="propertyLocation">Tyaudi Dhunnga, Macchapokhari</div>
                </div>
            </div>

            <!-- Property Card 4 -->
            <div class="propertyCard">
                <div class="propertyImageContainer">
                    <img src="${pageContext.request.contextPath}/images/house4.jpg" alt="Vintage home for sale" class="propertyImage">
                    <div class="saleTag">SALE!</div>
                </div>
                <div class="propertyDetails">
                    <div class="propertyType">Home</div>
                    <div class="propertyPrice">Rs.50,00,000</div>
                    <div class="propertyDescription">
                        <img src="${pageContext.request.contextPath}/images/house-icon.png" alt="House Icon" class="descIcon">
                        <p>🏠 A charming vintage home with timeless architecture and rustic appeal.</p>
                    </div>
                    <div class="propertyLocation">Sindhu Khola, Tehrathum</div>
                </div>
            </div>
            
            <!-- Property Card 5 -->
            <div class="propertyCard">
                <div class="propertyImageContainer">
                    <img src="${pageContext.request.contextPath}/images/house5.jpeg" alt="Modern home for sale" class="propertyImage">
                    <div class="saleTag">SALE!</div>
                </div>
                <div class="propertyDetails">
                    <div class="propertyType">Home</div>
                    <div class="propertyPrice">Rs.2,25,00,000</div>
                    <div class="propertyDescription">
                        <img src="${pageContext.request.contextPath}/images/house-icon.png" alt="House Icon" class="descIcon">
                        <p>✨ Luxury modern home with premium amenities and mountain views!</p>
                    </div>
                    <div class="propertyLocation">Budhanilkantha, Kathmandu</div>
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