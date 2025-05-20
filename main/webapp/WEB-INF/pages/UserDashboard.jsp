<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.nepestate.model.CustomerModel" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/UserDashboard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
</head>
<body>
  <jsp:include page="Navbar.jsp"/>

  <!-- Main Layout -->
  <div class="main-container">
    
    <jsp:include page="UserSidebar.jsp"/>
    
    <!-- Dashboard -->
    <main class="dashboard">
      <h1>Dashboard</h1>
      <div class="dashboard-cards">
        <div class="card">
          <div class="card-content">
            <h2>Rs. <%= request.getAttribute("totalRevenue") %></h2>
            <p>Revenue</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/moneyicon.png" alt="Revenue Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2><%= request.getAttribute("totalBought") %></h2>
            <p>Bought Properties</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/buyicon.jpg" alt="Bought Properties Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2><%= request.getAttribute("totalUploads") %></h2>
            <p>Total Uploads</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/upload.png" alt="Upload Icon" class="card-icon" />
        </div>
      </div>

      <div class="visits-section">
        <h2>Total Visits</h2>
        <img src="${pageContext.request.contextPath}/images/linegraph.webp" alt="Graph Chart" class="graph-img"/>
      </div>
    </main>
  </div>
  
  <!-- Footer -->
  <jsp:include page="Footer.jsp"/>

</body>
</html>