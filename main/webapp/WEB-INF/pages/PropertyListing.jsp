<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nepestate.model.PropertyModel" %>
<%
    List<PropertyModel> users = (List<PropertyModel>) request.getAttribute("propertyList");
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
        
        <!-- Sidebar -->
        <jsp:include page="AdminSidebar.jsp"/>
        
        <!-- Content -->
        <main class="property-list">
            <div class="property-header">
                <h1>Property List</h1>
                <div class="property-controls">
                    <input type="text" placeholder="Search" class="search-bar" />
                    <button class="new-btn">+ New Property</button>
                </div>
            </div>

            <table class="property-table">
                <thead>
                    <tr>
                        <th><input type="checkbox"></th>
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
                            <td><input type="checkbox" /></td>
                            <%-- <td><img src="${pageContext.request.contextPath}/images/${p.image}" class="property-img" alt="Image"></td> --%>
                            <td><img src="${pageContext.request.contextPath}/images/house1.webp" class="property-img" /></td>
                            <td>${p.property_Address}</td>
                            <td>${p.property_Type}</td>
                            <td>Rs.${p.property_Price}</td>
                            <td class="status available">${p.property_Status}</td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/UpdateProperty">
                                    <img src="${pageContext.request.contextPath}/images/edit-246.png" />
                                </a>
                                <a href="#">
                                    <img src="${pageContext.request.contextPath}/images/view-icon-614x460.png" />
                                </a>
                                <a href="#">
                                    <img src="${pageContext.request.contextPath}/images/delete-icon.png" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <div class="entries-dropdown">
                Show 
                <select>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3" selected>3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select> 
                entries
            </div>
        </main>
    </div>
</body>
</html>