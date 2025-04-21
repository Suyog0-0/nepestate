<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Product</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/viewProduct.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
	
		
</head>

<body>
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<div class="navbarSection">
	<jsp:include page="navbar.jsp" />
</div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<div class="centerSectionWrapper">
    <div class="centerSection">

        <div class="productContainer">
            <div class="productContent">
                <div class="price"><a>Rs. 2,56,00,000</a> </div>
                <div class="location">Thamel, Kathmandu</div>
                
                <div class="statusContainer">
                    <div class="status">
                        <img src="${pageContext.request.contextPath}/images/greentick.png" class="tickIcon">
                        <h1 class="statusText">Available</h1>
                    </div>
                </div>
        
                <div class="imageContainer">
                    <img src="${pageContext.request.contextPath}/images/house1.jpeg" class="house1">
                    <img src="${pageContext.request.contextPath}/images/house2.jpg" class="house2">
                    <img src="${pageContext.request.contextPath}/images/house3.jpeg" class="house3">
                    <img src="${pageContext.request.contextPath}/images/house4.jpg" class="house4">
                    <img src="${pageContext.request.contextPath}/images/house5.jpeg" class="house5">
                </div>
    
                <div class="housingDetails">
                    <div class="type">
                        <h1 class="typeHeading">Type</h1>
                        <h1 class="typeText">Housing</h1>
                    </div>
            
                    <div class="area">
                        <h1 class="areaHeading">Area</h1>
                        <h1 class="areaText">20 aana of Land</h1>
                    </div>
            
                    <div class="totalUnits">
                        <h1 class="totalUnitsHeading">Total Units</h1>
                        <h1 class="totalUnitsText">5</h1>
                    </div>
                </div>
    
            </div>
        </div>
    
        <div class="profileContainer">
    
            <div class="profileDetailsContainer">
                <div class="profileImage">
                    <img src="${pageContext.request.contextPath}/images/profile.png" class="profileImage">
                </div>
    
                <div class="profileDetails">
                    <div class="profileName">Durgesh Thapa</div>
                    <div class="profileNumber">
                        <img src="${pageContext.request.contextPath}/images/phone.png" class="phoneIcon">
                        +977 9841222694
                    </div>    
                    <div class="profileEmail">
                        <img src="${pageContext.request.contextPath}/images/mail.png" class="mailIcon">
                        durgeshthapa@gmail.com
                    </div>
                </div>
            </div>
    
            <div class="profileButtonContainer">
                <button class="buyNowButton">Buy Now</button>
                <button class="bookmarkButton">Bookmmark</button>
            </div>
    
        </div>
    
    </div>
</div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<div class="bottomSection">

    <div class="aboutContainer">
        <div class="aboutHeading">About the Property</div>
        <div class="aboutText">
            Downtown Residency is a premium housing development located in the heart of Thamel, Kathmandu. Designed for modern living, this project combines convenience, comfort, and style in one of the city’s most vibrant neighborhoods. With a total of 7 thoughtfully designed units spread across 20 aana of land, it offers the perfect blend of privacy and community living.
        </div>
    </div>

    <div class="featureContainer">
        <div class="featureHeading">Features</div>
        <div class="featureImages">
            <img src="${pageContext.request.contextPath}/images/feature1.png" class="featureImage1">
            <img src="${pageContext.request.contextPath}/images/feature2.png" class="featureImage2">
            <img src="${pageContext.request.contextPath}/images/feature3.png" class="featureImage3">
        </div>
    </div>
    
</div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

<div class="footerSection">
	<jsp:include page="footer.jsp" />
</div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

</body>
</html>