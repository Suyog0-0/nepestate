<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nepestate.service.DashboardService" %> 
<%
    DashboardService dashboardService = new DashboardService();
    int totalRevenue = dashboardService.getTotalRevenue();
    int totalUsers = dashboardService.getTotalUsers();
    int totalBuyers = dashboardService.getTotalBuyers();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
</head>
<body>

    <!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
    
    <div class="navbarSection">
        <!-- Code for Including the navbar -->
        <jsp:include page="Navbar.jsp" />
    </div>

<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
  <!-- Main Layout -->
  <div class="main-container">
    <!-- Sidebar -->
      <jsp:include page="AdminSidebar.jsp"/>

    <!-- Dashboard Content -->
    <main class="dashboard">
      <h1>Dashboard</h1>
      <div class="dashboard-cards">
        <div class="card">
          <div class="card-content">
            <h2>Rs.<%= totalRevenue %></h2>
            <p>Revenue</p>
          </div>
          
          <img src="${pageContext.request.contextPath}/images/moneyicon.png" alt="Revenue Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2><%= totalUsers %></h2>
            <p>Users</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/user.png" alt="Users Icon" class="card-icon" />
        </div>
        
        <div class="card">
          <div class="card-content">
            <h2><%= totalBuyers %></h2>
            <p>Buyers</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/page-visitors-icon-style-free-vector-removebg-preview.png" alt="Visitors Icon" class="card-icon" />
        </div>
      </div>

      <div class="data-section">
        <div class="chart-box">
          <h3><img src="${pageContext.request.contextPath}/images/signupchart.png" class="section-icon" /> Sign-Ups</h3>
          <img src="${pageContext.request.contextPath}/images/sign_ups.png" alt="Sign-Ups Chart" class="data-img-signup" />
        </div>
        <div class="chart-box">
          <h3><img src="${pageContext.request.contextPath}/images/growthicon.png" class="section-icon" /> Growth</h3>
          <img src="${pageContext.request.contextPath}/images/ProgressCircle.webp" alt="Growth Chart" class="data-img-chart" />
        </div>
      </div>
    </main>
  </div>
</body>
</html>
</body>
</html>