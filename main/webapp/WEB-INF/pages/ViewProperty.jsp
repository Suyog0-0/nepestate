<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    <%@
    page import="com.nepestate.model.PropertyModel"
    %>
    <%@ page import="java.util.List"%>
   <%
   		List<PropertyModel> users = (List<PropertyModel>) request.getAttribute("propertyList");
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
href="${pageContext.request.contextPath}/css/ViewProperty.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
	<jsp:include page="Navbar.jsp" />
	<div style="display: flex; margin-top: 12px;">
        <div class="filter">
            <div>
                <h2>Filter Properties</h2>
                    <div class="filter_button">
                        <div>
                            <h4 style="margin-bottom: 10px;">Properties By</h4>
                            <button>By Nepestate</button>
                            <button>By Owners</button>
                        </div>
                    </div>
            </div>
            
            <div>
                <h2 style="margin-top: 25px;">Location</h2>
                <div>
                    <label for="location"></label>
                    <div>
                        <input list="location-options" id="locations" name="locations" class="combobox">
                        <datalist id="location-options">
                            <option value="Kathmandu">
                            <option value="Lalitpur">
                            <option value="Bhaktapur">
                        </datalist>
                    </div>
                    
                </div>
            </div>
    
            <div>
                <h2 style="margin-top: 25px;">Category</h2>
                <div>
                    <button>Housing</button>
                    <button>Appartment</button>
                    <button style="margin-top: 10px; margin-left:70px">House</button>
                </div>
            </div>
    
            <div>
                <h2 style="margin-top: 25px;">Filter by Price</h2>
                <div>
                    <button>Low-High</button>
                    <button>High-Low</button>
                </div>
            </div>
        </div>
        <div class="listing">
    		<c:forEach var="p" items="${propertyList}">
        		<a href="#">
           			<div class="card">
                <%-- <img src="${pageContext.request.contextPath}/images/${p.image}" alt="Image"> --%>
                		<div class="card-content">
                    		<h3>Rs.${p.property_Price}</h3>
                    <%-- <span class="tag">${p.}</span> --%>
                    		<div class="location">${p.property_Address}</div>
                    		<div class="description">${p.property_Description}</div>
                    		<div class="status">${p.property_Status}</div>
                		</div>
            		</div>
        		</a>
    		</c:forEach>
		</div>
    </div>
</body>
<br>
<br>
<%-- <jsp:include page="Footer.jsp" /> --%>
</html>