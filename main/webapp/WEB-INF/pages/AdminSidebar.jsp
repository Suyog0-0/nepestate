<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Sidebar</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/AdminSidebar.css">
</head>
<body>
	<div class="sidebar">
    <div class="sidebar-top">
      <img src="${pageContext.request.contextPath}/images/profilepicture.jpg" alt="Profile Picture" class="profile-pic" />
      <div class="user-info">
        <h3  class="roleName">( Admin )</h3>
        <h3>Durgesh Thapa</h3>
        <p>+977 9841222694</p>
        <p>durgeshthapa@gmail.com</p>
      </div>
      <nav class="nav-menu">
        <a href="${pageContext.request.contextPath}/admindashboard" class="nav-link active">
          <img src="${pageContext.request.contextPath}/images/dashboard.png" class="icon" />
          Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/propertylisting" class="nav-link">
          <img src="${pageContext.request.contextPath}/images/properties.png" class="icon" />
          Properties
        </a>
        <a href="${pageContext.request.contextPath}/ProductAdmin" class="nav-link">
          <img src="${pageContext.request.contextPath}/images/users.png" class="icon" />
          Users
        </a>
        <a href="${pageContext.request.contextPath}/ReportGeneration" class="nav-link">
          <img src="${pageContext.request.contextPath}/images/report.png" class="icon" />
          Report
        </a>
      </nav>
    </div>
    <button class="logout-btn">
      <img src="${pageContext.request.contextPath}/images/logout.png" class="icon" />
      Logout
    </button>
  </div>
</body>
</html>