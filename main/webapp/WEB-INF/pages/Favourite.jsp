<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.nepestate.model.PropertyModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Favourite Properties</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/css/Favourite.css">
 	<link rel="stylesheet" type="text/css" href="${contextPath}/css/Navbar.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/Footer.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/UserSidebar.css">
</head>
<body>

    <div class="navbarSection">
        <jsp:include page="Navbar.jsp" />    
    </div>
    
    <div class="maindiv">
        <jsp:include page="UserSidebar.jsp" />
  
        <div class="favouriteContainer">
            <div class="favouriteText">My Favourites</div>
            <div class="favouriteUnderline"></div> <!-- For underline -->

            <!-- Display message if no favourite properties -->
            <c:if test="${empty favouriteProperties}">
                <div class="no-properties">
                    <p>No favourite properties found.</p>
                </div>
            </c:if>

            <!-- Loop through each favourite property -->
            <c:forEach var="property" items="${favouriteProperties}">
                <div class="property-item">
                    <div class="imageOfAndDetailsContainer">
                        <img src="${contextPath}/images/${property.image}" class="propertyImage">
                        <div class="priceandlocation">
                            <div class="price">Rs.${property.price}</div>
                            <div class="location">${property.location}</div>
                            <div class="statusContainer">
                                <div class="status">
                                    <img src="${contextPath}/images/greentick.png" class="tickIcon">
                                    <h1 class="statusText">${property.status}</h1>
                                </div>
                                <div class="profileButtonContainer">
                                    <button class="buyNowButton">Buy Now</button>
                                    <button class="bookmarkButton">About</button>
                                </div>
                            </div>
                        </div>
                        <div class="removeIconContainer">
                            <img src="${contextPath}/images/removeIcon.png" class="removeIcon">
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>

    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>