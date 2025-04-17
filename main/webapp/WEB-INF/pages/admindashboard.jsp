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
	
  <iframe src="navbar.html" frameborder="0" style="width: 100%; height: 130px;"></iframe>

  <!-- Main Layout -->
  <div class="main-container">
    

    <div class="sidebar">
      <div class="sidebar-top">
        <img src="Screenshot 2024-07-10 083255.png" alt="Profile Picture" class="profile-pic" />
        <div class="user-info">
          <h3>Durgesh Thapa</h3>
          <p>+977 9841222694</p>
          <p>durgeshthapa@gmail.com</p>
        </div>
        <nav class="nav-menu">

          <a href="#" class="nav-link active">
            <img src="1530975-200.png" class="icon" />
            Dashboard
          </a>
          <a href="#" class="nav-link">
            <img src="1413908.png" class="icon" />
            Properties
          </a>
          <a href="#" class="nav-link">
            <img src="free-users-icon-267-thumb.png" class="icon" />
            Users
          </a>
          <a href="#" class="nav-link">
            <img src="3093748.png" class="icon" />
            Report
          </a>
          <a href="#" class="nav-link">
            <img src="455705.png" class="icon" />
            Contact
          </a>
        </nav>
      </div>

      <button class="logout-btn">
        <img src="https://cdn-icons-png.flaticon.com/512/660/660252.png" class="icon" />
        Logout
      </button>
    </div>

    <main class="dashboard">
      <h1>Dashboard</h1>
      <div class="dashboard-cards">
        <div class="card">
          <div class="card-content">
            <h2>$100</h2>
            <p>Revenue</p>
          </div>
          <img src="pngtree-paper-money-icon-png-image_5053703-removebg-preview.png" alt="Revenue Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2>2</h2>
            <p>Sold Properties</p>
          </div>
          <img src="sold-hanging-board-or-signboard-for-property-vector-55161838-removebg-preview.png" alt="Sold Properties Icon" class="card-icon" />
        </div>
        <div class="card">
          <div class="card-content">
            <h2>5</h2>
            <p>Total Uploads</p>
          </div>
          <img src="4209802.png" alt="Upload Icon" class="card-icon" />
        </div>
      </div>
      
      

      <div class="visits-section">
        <h2>Total Visits</h2>
        <img src="The-line-graph-shows-the-number-of-first-time-visitors-and-returning-visitors-who-visited-Caryl-Island-from-2000-to-2008..webp" alt="Graph Chart" class="graph-img"/>
      </div>
    </main>
  </div>
</body>
</html>