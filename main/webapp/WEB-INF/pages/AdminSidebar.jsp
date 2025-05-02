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
        <div class="profile-section">
            <img src="${pageContext.request.contextPath}/images/profilepicture.jpg" 
                 alt="Profile" class="profile-pic">
            <div class="user-info">
                <p class="role">(Admin)</p>
                <h3>Durgesh Thapa</h3>
                <p class="phone">+977 9841222694</p>
                <p class="email">durgeshthapa@gmail.com</p>
            </div>
            <div class="blue-line"></div>
        </div>
        <nav class="nav-menu">
            <a href="${pageContext.request.contextPath}/admindashboard" class="nav-link">
                <img src="${pageContext.request.contextPath}/images/dashboard.png" class="icon">
                Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/propertylisting" class="nav-link">
                <img src="${pageContext.request.contextPath}/images/properties.png" class="icon">
                Properties
            </a>
            <a href="${pageContext.request.contextPath}/ProductAdmin" class="nav-link">
                <img src="${pageContext.request.contextPath}/images/users.png" class="icon">
                Users
            </a>
            <a href="${pageContext.request.contextPath}/ReportGeneration" class="nav-link">
                <img src="${pageContext.request.contextPath}/images/report.png" class="icon">
                Report
            </a>
        </nav>

        <button class="logout-btn">
            <img src="${pageContext.request.contextPath}/images/logout.png" class="icon">
            Log Out
        </button>
    </div>
</body>
</html>
