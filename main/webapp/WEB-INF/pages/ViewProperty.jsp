<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    <%@
    page import="com.nepestate.model.PropertyModel"
    %>
    <%@ page import="java.util.List"%>
   <%
   		List<PropertyModel> property = (List<PropertyModel>) request.getAttribute("propertyList");
   		List<PropertyModel> location = (List<PropertyModel>) request.getAttribute("locationList");
   		List<PropertyModel> propertyForFilter = (List<PropertyModel>) request.getAttribute("propertyListForFIlter");
   		List<PropertyModel> locationForFIlter = (List<PropertyModel>) request.getAttribute("locationListForFilter");
        %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Navbar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/ViewProperty.css?v=2">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Footer.css">

</head>
<body>
	<jsp:include page="Navbar.jsp" />
	
        <div style="display: flex; margin-top: 12px;">
        <!-- Filter Section -->
        <div class="filter">
            <form action="${pageContext.request.contextPath}/ViewPropertyController" method="get">
                <div>
                    <h2>Filter Properties</h2>
               
                </div>
                
                <div>
                    <h2 style="margin-top: 25px;">Location</h2>
                    <div>
                        <div>
                            <select name="location" id="locations" class="combobox">
                                <option value="">All locations</option>
                                <c:forEach var="l" items="${locationList}">
                                    <option value="${l.property_City}" 
                                        <c:if test="${selectedLocation == l.property_City}">selected</c:if>>${l.property_City}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div>
                    <h2 style="margin-top: 25px;">Category</h2>
                    <div>
                        <input type="radio" id="housing" name="category" value="Housing" class="feature-checkbox"
                            <c:if test="${selectedCategory == 'Housing'}">checked</c:if>>
                        <label for="housing" class="feature-label">Housing</label>
                        
                        <input type="radio" id="apartment" name="category" value="Appartment" class="feature-checkbox"
                            <c:if test="${selectedCategory == 'Appartment'}">checked</c:if>>
                        <label for="apartment" class="feature-label">Appartment</label>
                        
                        <input type="radio" id="house" name="category" value="House" class="feature-checkbox"
                            <c:if test="${selectedCategory == 'House'}">checked</c:if>>
                        <label for="house" class="feature-label" style="margin-top: 5px;">House</label>
                    </div>
                </div>
                
                <div>
                    <h2 style="margin-top: 25px;">Filter by Price</h2>
                    <div>
                        <input type="radio" id="low-high" name="priceSort" value="Low-High" class="feature-checkbox"
                            <c:if test="${selectedPriceSort == 'Low-High'}">checked</c:if>>
                        <label for="low-high" class="feature-label">Low-High</label>
                        
                        <input type="radio" id="high-low" name="priceSort" value="High-Low" class="feature-checkbox"
                            <c:if test="${selectedPriceSort == 'High-Low'}">checked</c:if>>
                        <label for="high-low" class="feature-label">High-Low</label>
                    </div>
                </div>
                
                <div style="margin-top: 25px; padding:15px">
                   <!--  <input type="submit" value="Apply" class="apply-button" style="margin-left:20px;">
                    <input type="submit" name="reset" value="true" class="reset-button" onclick="resetForm(this.form); return true;"> -->
                    <button type="submit" class="apply-button">Apply Filters</button>
            <button type="submit" name="reset" value="true" class="reset-button">Reset</button>
                </div>
            </form>
        </div>
        
        <div class="listing">
    		<c:forEach var="p" items="${propertyList}">
        		<a href="${pageContext.request.contextPath}/ViewPropertySPController?propertyId=${p.propertyID}" >
           			<div class="card">
                 <img src="${pageContext.request.contextPath}${p.property_Photos}" alt="Image"> 
                		<div class="card-content">
                    		<h3>Rs.${p.property_Price}</h3>
                    <%-- <span class="tag">${p.}</span> --%>
                    		<div class="location">${p.p	roperty_Address}</div>
                    		<div class="description">${p.property_Description}</div>
                    		<div class="status">${p.property_Status}</div>
                		</div>
            		</div>
        		</a>
    		</c:forEach>
		</div>
		<c:if test="${empty propertyList}">
                <div class="no-results">
                    <h3>No properties found matching your criteria</h3>
                    <p>Try adjusting your filters or <a href="${contextPath}/ViewPropertyController">view all properties</a>.</p>
                </div>
            </c:if>
    </div>
</body>
<br>
<br>
<jsp:include page="Footer.jsp" />
</html>