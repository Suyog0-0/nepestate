<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/admindashboard.css">
</head>
<body>
	
  <jsp:include page="navbar.jsp"/>
  
  <!-- Main Layout -->
  <div class="main-container">
    <!-- Sidebar -->
      <jsp:include page="adminsidebar.jsp"/>

    <!-- Dashboard Content -->
    <main class="dashboard">
      <h1>Dashboard</h1>
      <div class="dashboard-cards">
        <div class="card">
          <div class="card-content">
            <h2>$100</h2>
            <p>Revenue</p>
          </div>
          <img src="/images/moneyicon.png" alt="Revenue Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2>20</h2>
            <p>Users</p>
          </div>
          <img src="/images/user.png" alt="Users Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2>5</h2>
            <p>Visitors</p>
          </div>
          <img src="/images/page-visitors-icon-style-free-vector-removebg-preview.png" alt="Visitors Icon" class="card-icon" />
        </div>
      </div>

      <div class="data-section">
        <div class="chart-box">
          <h3><img src="/images/signupchart.png" class="section-icon" /> Sign-Ups</h3>
          <img src="/images/linedrawing.jpg" alt="Sign-Ups Chart" class="data-img-signup" />
        </div>
        <div class="chart-box">
          <h3><img src="/images/growthicon.png" class="section-icon" /> Growth</h3>
          <img src="/images/growthchart.png" alt="Growth Chart" class="data-img-chart" />
        </div>
      </div>
    </main>
  </div>
</body>
</html>