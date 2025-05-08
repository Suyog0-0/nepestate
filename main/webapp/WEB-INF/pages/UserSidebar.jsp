<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserSidebar.css">

<div class="sidebar">
  <div class="profile-section">
    <img src="${pageContext.request.contextPath}/images/profilepicture.jpg"
         alt="Profile" class="profile-img"/>
    <p class="role">(User)</p>
    <h2>Durgesh Thapa</h2>
    <p class="phone">+977 9841222694</p>
    <!-- Dynamic email -->
    <p class="email">${sessionScope.userEmail}</p>
    <div class="blue-line"></div>
  </div>

  <nav class="menu">
    <ul>
      <li class="menu-item">
        <img src="${pageContext.request.contextPath}/images/users.png" class="icon"/>
        <a href="${pageContext.request.contextPath}/UserProfileController" class="nav-link">
          My profile
        </a>
      </li>

      <li class="dropdown-container">
        <div class="menu-item dropdown-header">
          <img src="${pageContext.request.contextPath}/images/down-icon.png" class="icon"/>
          <a href="${pageContext.request.contextPath}/UserDashboardController" class="nav-link">
            Dashboard
          </a>
        </div>
        <ul class="submenu">
          <li class="submenu-item">
            <img src="${pageContext.request.contextPath}/images/properties.png" class="icon"/>
            <a href="${pageContext.request.contextPath}/PropertyListingController" class="nav-link">
              Properties
            </a>
          </li>
          <li class="submenu-item">
            <img src="${pageContext.request.contextPath}/images/users.png" class="icon"/>
            <a href="${pageContext.request.contextPath}/ContactListingController" class="nav-link">
              Contact
            </a>
          </li>
          <li class="submenu-item">
            <img src="${pageContext.request.contextPath}/images/report.png" class="icon"/>
            <a href="${pageContext.request.contextPath}/ReportGenerationController" class="nav-link">
              Reports
            </a>
          </li>
        </ul>
      </li>

      <li class="menu-item">
        <img src="${pageContext.request.contextPath}/images/white_favourite.png" class="icon"/>
        <a href="${pageContext.request.contextPath}/FavouriteController" class="nav-link">
          Favorites
        </a>
      </li>

      <li class="menu-item logout">
        <img src="${pageContext.request.contextPath}/images/logout.png" class="icon"/>
        <a href="${pageContext.request.contextPath}/Logout" class="nav-link-logout">
          Log Out
        </a>
      </li>
    </ul>
  </nav>
</div>

<script>
  document.addEventListener('DOMContentLoaded', () => {
    const dropdown = document.querySelector('.dropdown-header');
    dropdown.addEventListener('click', () => {
      dropdown.parentElement.classList.toggle('open');
    });
  });
</script>
