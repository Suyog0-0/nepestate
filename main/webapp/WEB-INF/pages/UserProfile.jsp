<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ page import="java.util.List" %>
<%@ page import="com.nepestate.model.PropertyModel" %>
<%
    @SuppressWarnings("unchecked")
    List<PropertyModel> properties = (List<PropertyModel>) request.getAttribute("properties");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile </title>
</head>
<style>
    .form-label {
        font-family: Arial, sans-serif;
        font-size: 18px;
        color: black;
      }
      .subColumn{
        display: flex;
        flex-direction: column;
    }
    .underline-long {
        font-family: Arial, sans-serif;
        font-size: 18px;
        width:754px;
        padding: 4px 0;
        background: transparent; 
        border:none;
        border-bottom: 1.5px solid #7C7C7C;      
    }
</style>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Navbar.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UserSidebar.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UpdateProperty.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UserProfile.css" />
<body>
	<div>
<jsp:include page="Navbar.jsp"/>
	</div>
	<div style="display:flex"> 
	<jsp:include page="UserSidebar.jsp"/>
	
        <div class="mainBox" style="margin-top: 50px;">
            <div class="contentBox">
                <form action="${pageContext.request.contextPath}/UserProfileController" method="post">
                    <div> 
                        <div style="display: flex;flex-direction:column">
                            <!-- Display error or success message if available -->
                            <c:if test="${not empty error}">
							 	<div class="error-message">${error}</div>
							</c:if>

                            <c:if test="${not empty success}">
							 	<div class="error-message">${success}</div>
							</c:if>

                            
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Name</label>
                                    <input type="text" name="name" class="underline-long" value="${username}">
                                </div>
                            </div>
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Description</label>
                                    <textarea name="description" class="user-description">${description}</textarea>
                                </div>
                            </div>
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Phone Number</label>
                                    <input type="text" name="phoneNumber" class="underline-long" value="${phoneNumber}">
                                </div>
                            </div>
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Email</label>
                                    <input type="text" name="email" class="underline-long" value="${emailAddress}">
                                </div>
                            </div>
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Date of Birth</label>
                                    <input type="text" name="dob" class="underline-long" value="${dob}">
                                </div>
                            </div>    
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Location</label>
                                    <input type="text" name="location" class="underline-long">
                                </div>
                            </div>
                            <br>
                            <div class="subRow">
                                <button type="submit" class="update-button">UPDATE PROFILE</button>
                            </div>
                        </div>
                    </div>
                </form>
               
               
                    <div style="margin-top:50px;">
                        <img src="${pageContext.request.contextPath}/images/updateIcon.png" alt="Property Image" />
                    </div>
                 
            </div>  
        </div>
 	</div>

    <div style="font-family:Arial,sans-serif;font-size:40px;margin-left:125px;margin-top:50px;display:block;">
             Listed Properties (${propertiesCount})
        </div>
    <div style="display:flex;gap:60px;margin-top:50px;margin-left:120px;">
    <c:forEach var="property" items="${properties}">
        <div class="productBox">
            <img src="${pageContext.request.contextPath}/images/ListedProperty1.png"> 
            <div style="direction:flex;margin-left:12px;max-width:300px;margin-top:10px;">
                <div class="tag"> ${property.property_Type} </div>
                <h3 style="margin-bottom:10px;">${property.property_Price}</h3>
                <div style="font-size:12px;color:#666"> ${property.property_Description}</div>
                <div style="text-align:center;font-size:10px;color:#666;margin-top:12px"> ${property.property_Address}</div>
            </div> 
        </div>
       </c:forEach>
        </div>
     <c:if test="${empty properties}">
            <div style="text-align:center;width:100%;padding:30px;color:#666;font-family:Arial,sans-serif;">
                No properties found for this customer.
            </div>
        </c:if> 
    <br>
    <br>
    <jsp:include page="Footer.jsp"/>
</body>
</html>