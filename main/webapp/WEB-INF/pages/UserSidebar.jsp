<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserSidebar.css">

<div class="sidebar">
  <div class="profile-section">
    <img src="${pageContext.request.contextPath}/images/profilepicture.jpg" alt="Profile" class="profile-img"/>
    <p class="role">(User)</p>
    <h2>Durgesh Thapa</h2>
    <p class="phone">+977 9841222694</p>
    <p class="email">durgeshthapa@gmail.com</p>
    <div class="blue-line"></div>
  </div>

  <nav class="menu">
    <ul>
      <li class="menu-item">
        <img src="${pageContext.request.contextPath}/images/users.png" class="icon">
        My Profile
      </li>

      <li class="menu-item dropdown-toggle">
        <div class="dropdown-header">
          <img src="${pageContext.request.contextPath}/images/down-icon.png" class="icon dropdown-icon">
          Dashboard
        </div>
        <ul class="submenu">
          <li class="submenu-item">
            <img src="${pageContext.request.contextPath}/images/properties.png" class="icon">
            Properties
          </li>
          <li class="submenu-item">
            <img src="${pageContext.request.contextPath}/images/users.png" class="icon">
            Users
          </li>
          <li class="submenu-item">
            <img src="${pageContext.request.contextPath}/images/report.png" class="icon">
            Reports
          </li>
        </ul>
      </li>

      <li class="menu-item">
        <img src="${pageContext.request.contextPath}/images/favourite.png" class="icon favorite-icon">
        Favourites
      </li>

      <li class="menu-item logout">
        <img src="${pageContext.request.contextPath}/images/logout.png" class="icon">
        Log Out
      </li>
    </ul>
  </nav>
</div>

<script>
document.addEventListener('DOMContentLoaded', () => {
  // Init dropdown
  const toggle = document.querySelector('.dropdown-toggle');
  
  toggle.addEventListener('click', () => {
    toggle.classList.toggle('open');
    toggle.classList.toggle('active');
  });
});
</script>