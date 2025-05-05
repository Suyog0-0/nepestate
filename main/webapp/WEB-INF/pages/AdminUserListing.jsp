<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@
    page import="com.nepestate.model.CustomerModel"
    %>
    <%@ page import="java.util.List"%>
   <%
   		List<CustomerModel> users = (List<CustomerModel>) request.getAttribute("customerList");
        %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Admin</title>
<style></style>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Navbar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/AdminUserListing.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Footer.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/AdminSidebar.css">
</head>
<body>
	<jsp:include page="Navbar.jsp" />

	<div style="display: flex;">
		<jsp:include page="AdminSidebar.jsp" />
    
        	
          <div class="users"> 
          <table>
    <thead>
      <tr>
        <th>S.N</th>
        <th>Name</th>
        <th>Age</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>Total Listings</th>
      </tr>
    </thead>

    <tbody>
    	<c:forEach var="u" items="${customerList}">
     		<tr>
        		<td data-label="S.N">1</td>
        		<td data-label="Name">${u.customer_Username}</td>
        		<td data-label="Age">${u.customer_DoB}</td>
        		<td data-label="Phone">${u.customer_PhoneNumber}</td>
        		<td data-label="Email">${u.customer_EmailAddress}</td>
        		<!-- td data-label="Listings">2</td> -->
      		</tr>
      		</c:forEach>
    </tbody>
  </table>
          
            
          </div>
    </div>
    <br>
    <br>
   <jsp:include page="Footer.jsp" />
</body>
</html>