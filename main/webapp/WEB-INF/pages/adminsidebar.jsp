<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/adminsidebar.css">
</head>
<body>
	<div class="sidebar">
    <div class="sidebar-top">
      <img src="/images/profilepicture.png" alt="Profile Picture" class="profile-pic" />
      <div class="user-info">
        <h3  class="roleName">( Admin )</h3>
        <h3>Durgesh Thapa</h3>
        <p>+977 9841222694</p>
        <p>durgeshthapa@gmail.com</p>
      </div>
      <nav class="nav-menu">
        <a href="#" class="nav-link active">
          <img src="/images/dashboard.png" class="icon" />
          Dashboard
        </a>
        <a href="propertylist.html" class="nav-link">
          <img src="/images/properties.png" class="icon" />
          Properties
        </a>
        <a href="#" class="nav-link">
          <img src="/images/users.png" class="icon" />
          Users
        </a>
        <a href="#" class="nav-link">
          <img src="/images/report.png" class="icon" />
          Report
        </a>
        <a href="#" class="nav-link">
          <img src="/images/contact.png" class="icon" />
          Contact
        </a>
      </nav>
    </div>
    <button class="logout-btn">
      <img src="/images/logout.png" class="icon" />
      Logout
    </button>
  </div>
</body>
</html>