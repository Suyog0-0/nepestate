<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Property</title>
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
                    <c:if test="${not empty sessionScope.error}">
                        <div class="error-message">${sessionScope.error}</div>
                        <% session.removeAttribute("error"); %>
                    </c:if>
                    <c:if test="${not empty sessionScope.message}">
                        <div class="success-message">${sessionScope.message}</div>
                        <% session.removeAttribute("message"); %>
                    </c:if>
                    <c:if test="${empty sessionScope.error && not empty property}">
                        <div class="price">Rs. ${property.property_Price}</div>
                        <div class="location">${property.property_Address}, ${property.property_City}</div>
                        
                        <!-- Available/Not Available Section -->
                        <div class="availabilitySection">
                            <div class="availableOption">
                                <span class="availableText">${property.property_Status}</span>
                            </div>
                        </div>
                        
                        <!-- Image Gallery -->
                        <div class="imageContainer">
                            <c:choose>
                                <c:when test="${not empty property.property_Photos}">
                                    <img src="${pageContext.request.contextPath}${property.property_Photos}" class="house1" alt="Property Image">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/images/default_property.jpg" class="house1" alt="Default Property Image">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        
                        <!-- Property Details -->
                        <div class="housingDetails">
                            <div class="type">
                                <div class="typeHeading">Type</div>
                                <div class="typeText">${property.property_Type}</div>
                            </div>
                            <div class="area">
                                <div class="areaHeading">Area</div>
                                <div class="areaText">${property.property_Area} sq.ft</div>
                            </div>
                            <div class="totalUnits">
                                <div class="totalUnitsHeading">Total Units</div>
                                <div class="totalUnitsText">${property.property_Ward}</div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
            
            <!-- Profile Container -->
            <div class="profileContainer">
                <div class="profileDetailsContainer">
                    <!-- Profile Picture -->
                    <c:set var="profilePicPath" value="${pageContext.request.contextPath}/images/defaultpfp.jpg" />
                    <c:if test="${customer != null}">
                        <c:set var="dbProfilePic" value="${customer.customer_ProfilePicture}" />
                        <c:if test="${not empty dbProfilePic}">
                            <c:set var="profilePicPath" value="${pageContext.request.contextPath}/images/${dbProfilePic}" />
                        </c:if>
                    </c:if>
                    
                    <img src="${profilePicPath}" class="profileImage" alt="Profile Picture">
                    
                    <div class="profileName">${customer.customer_FirstName} ${customer.customer_LastName}</div>
                    <div class="profileContact">
                        <div class="profileNumber">
                            <img src="${pageContext.request.contextPath}/images/phone.png" class="phoneIcon" alt="Phone Icon">
                            ${customer.customer_PhoneNumber}
                        </div>
                        <div class="profileEmail">
                            <img src="${pageContext.request.contextPath}/images/mail.png" class="mailIcon" alt="Mail Icon">
                            ${customer.customer_EmailAddress}
                        </div>
                    </div>
                </div>
                
                <div class="profileButtonContainer">
                    <!-- FIXED: Changed from customer_id to username for authentication check -->
                    <c:if test="${sessionScope.username != null}">
                        <form action="BuyNowController" method="post">
                            <input type="hidden" name="propertyId" value="${property.propertyID}" />
                            <button type="submit" class="buyNowButton">Buy Now</button>
                        </form>
                        
                        <form action="AddToFavoritesController" method="post">
                            <input type="hidden" name="propertyId" value="${property.propertyID}" />
                            <button type="submit" class="bookmarkButton">
                                <c:choose>
                                    <c:when test="${isBookmarked}">
                                        ❤️ Remove from Favorites
                                    </c:when>
                                    <c:otherwise>
                                        🤍 Add to Favorites
                                    </c:otherwise>
                                </c:choose>
                            </button>
                        </form>
                    </c:if>
                    
                    <!-- FIXED: Changed from customer_id to username for authentication check -->
                    <c:if test="${sessionScope.username == null}">
                        <div class="login-prompt">
                            <p>Please <a href="${pageContext.request.contextPath}/LoginController">login</a> to buy or bookmark this property.</p>
                        </div>
                    </c:if>
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
                    ${property.property_Description}
                </div>
            </div>
            
            <!-- Amenities Container -->
            <div class="amenitiesContainer">
                <div class="amenitiesHeading">Amenities</div>
                <div class="amenitiesList">
                    <c:forTokens items="${property.property_Amentities}" delims="," var="amenity">
                        <div class="amenityItem">
                            <img src="${pageContext.request.contextPath}/images/${amenity}.png" class="amenityImage" alt="${amenity}">
                            <div class="amenityName">${amenity}</div>
                        </div>
                    </c:forTokens>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Footer Include -->
    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>
    
    <style>
        .success-message {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 10px;
            margin: 10px 0;
            border-radius: 4px;
        }
        
        .error-message {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 10px;
            margin: 10px 0;
            border-radius: 4px;
        }
        
        .login-prompt {
            text-align: center;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 4px;
            margin-top: 10px;
        }
        
        .login-prompt a {
            color: #007bff;
            text-decoration: none;
        }
        
        .login-prompt a:hover {
            text-decoration: underline;
        }
    </style>
</body>
</html>