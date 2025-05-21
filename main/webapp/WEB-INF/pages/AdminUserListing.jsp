<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nepestate.model.CustomerModel"%>
<%@ page import="java.util.List"%>

<%
    List<CustomerModel> users = (List<CustomerModel>) request.getAttribute("customerList");
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin User Listing</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminUserListing.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminSidebar.css">
</head>

<body>
    <jsp:include page="Navbar.jsp" />

    <div style="display: flex;">
        <jsp:include page="AdminSidebar.jsp" />

        <div class="users">
            <!-- Display success or error messages if any -->
            <c:if test="${not empty successMessage}">
                <div class="message success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="message error">${errorMessage}</div>
            </c:if>

            <table>
                <thead>
                    <tr>
                        <th>S.N</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Phone Number</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="u" items="${customerList}" varStatus="status">
                        <tr>
                            <td data-label="S.N">${status.count}</td>
                            <td data-label="Name">${u.customer_Username}</td>
                            <td data-label="Age">${u.customer_DoB}</td>
                            <td data-label="Phone">${u.customer_PhoneNumber}</td>
                            <td data-label="Email">${u.customer_EmailAddress}</td>
                            <td>
                                <form action="${contextPath}/AdminUserListingController" method="post" style="margin:0" 
                                      onsubmit="return confirm('Are you sure you want to delete user ${u.customer_Username}?');">
                                    <input type="hidden" name="customerID" value="${u.customerID}">
                                    <input type="hidden" name="username" value="${u.customer_Username}">
                                    <input type="submit" class="delete-button" value="Delete User">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <br><br>

    <jsp:include page="Footer.jsp" />
</body>
</html>
