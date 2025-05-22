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

            <!-- Display success/error messages -->
            <c:if test="${not empty sessionScope.message}">
                <div class="success-message">${sessionScope.message}</div>
                <% session.removeAttribute("message"); %>
            </c:if>
            
            <c:if test="${not empty sessionScope.error}">
                <div class="error-message">${sessionScope.error}</div>
                <% session.removeAttribute("error"); %>
            </c:if>

            <!-- Display message if no favourite properties -->
            <c:if test="${empty favouriteProperties}">
                <div class="no-properties">
                    <p>No favourite properties found.</p>
                    <p><a href="${contextPath}/ViewPropertyController">Browse Properties</a> to add some to your favorites!</p>
                </div>
            </c:if>

            <!-- Loop through each favourite property -->
            <c:forEach var="property" items="${favouriteProperties}">
                <div class="property-item">
                    <div class="imageOfAndDetailsContainer">
                        <img src="${contextPath}${property.property_Photos}" class="propertyImage" alt="Property Image">
                        <div class="priceandlocation">
                            <div class="price">Rs.${property.property_Price}</div>
                            <div class="location">${property.property_Address}, ${property.property_City}</div>
                            <div class="statusContainer">
                                <div class="status">
                                    <img src="${contextPath}/images/greentick.png" class="tickIcon">
                                    <h1 class="statusText">${property.property_Status}</h1>
                                </div>
                                <div class="profileButtonContainer">
                                  
                                    <a href="${contextPath}/ViewPropertySPController?propertyId=${property.propertyID}" class="bookmarkButton">About</a>
                                </div>
                            </div>
                        </div>
                        <div class="removeIconContainer">
                            <form action="${contextPath}/AddToFavoritesController" method="post" style="display: inline;">
                                <input type="hidden" name="propertyId" value="${property.propertyID}" />
                                <button type="submit" class="remove-button" title="Remove from favorites">
                                    <img src="${contextPath}/images/removeIcon.png" class="removeIcon">
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>

    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>
    
    <style>
        .remove-button {
            background: none;
            border: none;
            cursor: pointer;
            padding: 0;
        }
        
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
        
        .no-properties {
            text-align: center;
            padding: 40px;
            color: #666;
        }
        
        .no-properties a {
            color: #007bff;
            text-decoration: none;
        }
        
        .no-properties a:hover {
            text-decoration: underline;
        }
    </style>
</body>
</html>