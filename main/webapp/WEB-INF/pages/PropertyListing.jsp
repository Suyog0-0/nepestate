<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nepestate.model.PropertyModel" %>
<%
List<PropertyModel> propertyList = (List<PropertyModel>) request.getAttribute("propertyList");
String userType = (String) request.getAttribute("userType");
   %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Property Listing</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/PropertyListing.css">
</head>
<body>

    <!-- Taskbar -->
    <jsp:include page="Navbar.jsp"/>

    <!-- Main Layout -->
    <div class="main-container">
        
	  <c:choose>
	    <c:when test="${userType == 'admin'}">
	        <jsp:include page="AdminSidebar.jsp"/>
	    </c:when>
	    <c:when test="${userType == 'customer'}">
	        <jsp:include page="UserSidebar.jsp"/>
	    </c:when>
	</c:choose>
        
        <!-- Content -->
        <main class="property-list">
            <div class="property-header">
                <h1>Property List</h1>
             <c:if test="${userType == 'admin' || userType == 'customer'}">
                <div class="property-controls">
                    <button class="new-btn"><a href="${pageContext.request.contextPath}/PostPropertyController">+ New Property</a></button>
                </div>
             </c:if>
            </div>

	<c:choose>
    	<c:when test="${propertyList != null && not empty propertyList}">
            <table class="property-table">
                <thead>
                    <tr>
                     <c:if test="${userType == 'admin'}">
                        <th><input type="checkbox"></th>
                      </c:if>
                        <th>Property Image</th>
                        <th>Address</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${propertyList}">
                        <tr>
                        <c:if test="${userType == 'admin'}">
                            <td><input type="checkbox" /></td>
                         </c:if>
                            <%-- <td><img src="${pageContext.request.contextPath}/images/${p.image}" class="property-img" alt="Image"></td> --%>
                            <td><img src="${pageContext.request.contextPath}/images/house1.webp" class="property-img" /></td>
                            <td>${p.property_Address}</td>
                            <td>${p.property_Type}</td>
                            <td>Rs.${p.property_Price}</td>
                            <td class="status available">${p.property_Status}</td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/UpdatePropertyController">
                                    <img src="${pageContext.request.contextPath}/images/edit-246.png" />
                                </a>
                                <a href="${pageContext.request.contextPath}/ViewPropertySPController">
                                    <img src="${pageContext.request.contextPath}/images/view icon.png" />
                                </a>
                                <a href="#">
                                    <img src="${pageContext.request.contextPath}/images/delete-icon.png" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
         </c:when>
        <c:otherwise>
        <!-- No properties found message -->
        <div class="no-properties">
            <h3>No Properties Found</h3>
            </div>
         </c:otherwise>
       </c:choose>
            <div class="entries-dropdown">
            </div>
        </main>
    </div>
</body>
</html>