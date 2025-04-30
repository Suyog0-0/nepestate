<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User SideBar</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/UserSidebar.css">
</head>
<body>
	<div class="sidebar">
    <div class="profile-section">
      <img src="${pageContext.request.contextPath}/images/profilepicture.png" alt="profile Picture" class="profile-img"/>
      <p class="role">( User )</p>
      <h2>Durgesh Thapa</h2>
      <p class="phone">+977 9841222694</p>
      <p class="email">durgeshthapa@gmail.com</p>
    </div>
    
    <nav class="menu">   
      <ul>
      <li><img src="${pageContext.request.contextPath}/images/users.png" class="dashboard-images"> My Profile</li> 
        <li class="dashboard-toggle">
            <span class="arrow"><img src="${pageContext.request.contextPath}/images/downward-icon.png"></span>
            <img src="${pageContext.request.contextPath}/images/dashboard.png" class="dashboard-images"> Dashboard </li>
        <ul class="submenu">
          <li><img src="${pageContext.request.contextPath}/images/properties.png" class="dashboard-images"> Properties</li>
          <li><img src="${pageContext.request.contextPath}/images/report.png" class="dashboard-images">  Reports</li>
          <li><img src="${pageContext.request.contextPath}/images/contact.png" class="dashboard-images">  Contact</li>
        </ul>
        <li><img src="${pageContext.request.contextPath}/images/logout.png" class="logout-img">  Log Out</li>
      </ul>
       </nav>
  </div>

  <script>
    const toggle = document.querySelector('.dashboard-toggle');
    const submenu = document.querySelector('.submenu');

    toggle.addEventListener('click', () => {
      submenu.classList.toggle('active');
    });
  </script>
</body>
</html>