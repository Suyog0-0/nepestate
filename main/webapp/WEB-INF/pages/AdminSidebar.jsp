<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    com.nepestate.model.AdminModel loggedInAdmin = (com.nepestate.model.AdminModel) session.getAttribute("loggedInAdmin");
    String adminFName = loggedInAdmin != null ? loggedInAdmin.getAdmin_FirstName() : "Admin";
    String adminLName = loggedInAdmin != null ? loggedInAdmin.getAdmin_LastName() : "Name";
    String adminUsername = loggedInAdmin != null ? loggedInAdmin.getAdmin_Username() : "";
    String adminPhone = loggedInAdmin != null ? loggedInAdmin.getAdmin_PhoneNumber() : "";
    String adminProfilePic = loggedInAdmin != null ? loggedInAdmin.getAdmin_ProfilePicture() : "default-profile.png";
%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Sidebar</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminSidebar.css">
</head>
<body>
  <div class="sidebar">
    <div class="profile-section">
      <img src="${pageContext.request.contextPath}/images/<%= adminProfilePic %>" alt="Profile" class="profile-pic">
      <div class="user-info">
        <p class="role">(Admin)</p>
        <h3><%= adminFName %> <%= adminLName %></h3>
        <p class="phone"><%= adminPhone %></p>
        <p class="username"><%= adminUsername %></p>
      </div>
      <div class="blue-line"></div>
    </div>
    <nav class="nav-menu">
      <a href="${pageContext.request.contextPath}/AdminDashboardController" class="nav-link">
        <img src="${pageContext.request.contextPath}/images/dashboard.png" class="icon">
        Dashboard
      </a>
      <a href="${pageContext.request.contextPath}/PropertyListingController" class="nav-link">
        <img src="${pageContext.request.contextPath}/images/properties.png" class="icon">
        Properties
      </a>
      <a href="${pageContext.request.contextPath}/AdminUserListingController" class="nav-link">
        <img src="${pageContext.request.contextPath}/images/users.png" class="icon">
        Users
      </a>
      <a href="${pageContext.request.contextPath}/ReportGenerationController" class="nav-link">
        <img src="${pageContext.request.contextPath}/images/report.png" class="icon">
        Report
      </a>
    </nav>
    <!-- Log Out now routes to /logout -->
    <button class="logout-btn"
            onclick="location='${pageContext.request.contextPath}/Logout'">
      <img src="${pageContext.request.contextPath}/images/logout.png" class="icon">
      Log Out
    </button>
  </div>
</body>
</html>
