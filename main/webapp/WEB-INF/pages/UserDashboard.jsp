<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>

  <jsp:include page="navbar.jsp"/>

  <!-- Main Layout -->
  <div class="main-container">
    
    <jsp:include page="usersidebar.jsp"/>
   
    <!-- Dashboard -->
    <main class="dashboard">
      <h1>Dashboard</h1>
      <div class="dashboard-cards">
        <div class="card">
          <div class="card-content">
            <h2>$100</h2>
            <p>Revenue</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/moneyicon.png" alt="Revenue Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2>2</h2>
            <p>Sold Properties</p>
          </div>
          <img src="${pageContext.request.contextPath}/images/sold-hanging-board-or-signboard-for-property-vector-55161838-removebg-preview.png" alt="Sold Properties Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2>5</h2>
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
	
</body>
</html>