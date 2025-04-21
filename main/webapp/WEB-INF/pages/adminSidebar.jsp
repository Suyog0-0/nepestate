<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/adminSidebar.css">


<body>
	<div class="sidebar">
    <div class="sidebar-top">

      <img src="${pageContext.request.contextPath}/images/profile.png" alt="Profile Picture" class="profile-pic" />

      <div class="user-info">
        <h3  class="roleName">( Admin )</h3>
        <h3>Durgesh Thapa</h3>
        <p>+977 9841222694</p>
        <p>durgeshthapa@gmail.com</p>
      </div>
      <nav class="nav-menu">
        <a href="#" class="nav-link active">
          <img src="1530975-200.png" class="icon" />
          Dashboard
        </a>
        <a href="propertylist.html" class="nav-link">
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
  </body>
</html>
