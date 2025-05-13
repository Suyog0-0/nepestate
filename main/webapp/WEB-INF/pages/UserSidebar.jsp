<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nepestate.service.CustomerService" %>
<%@ page import="com.nepestate.model.CustomerModel" %>
<%
    CustomerModel customer = (CustomerModel) session.getAttribute("loggedInCustomer");
%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserSidebar.css">

 
<body>
  <div class="sidebar">
    <div class="profile-section">
    <% if (customer == null) { %>
    <img src="${pageContext.request.contextPath}/images/profilepicture.png" alt="Profile" class="profile-img"/>
      <p class="role">( User )</p>
      <h2>Name</h2>
      <p class="phone">Phone Number</p>
      <p class="username">Username</p>
                    <% } else { %>
                     <img src="<%= customer.getCustomer_ProfilePicture() %>" class="profile-img" alt="Profile" 
         onerror="this.src='${pageContext.request.contextPath}/images/defaultpfp.jpg'" />
				      <p class="role">( User )</p>
				      <h2><%= customer.getCustomer_FirstName() + " " + customer.getCustomer_LastName() %></h2>
				      <p class="phone"><%= customer.getCustomer_PhoneNumber() %></p>
				      <p class="username"><%= customer.getCustomer_Username() %></p>
                    <% } %>
    </div>
    
    <nav class="menu">
      <ul>
        <li><img src="${pageContext.request.contextPath}/images/users.png" class="dashboard-images"><a href="${pageContext.request.contextPath}/UserProfileController">My profile</a></li>
        <li class="dashboard-toggle">
            <span class="arrow">
            	<img src="${pageContext.request.contextPath}/images/down-icon.png">
            </span>
            <img src="${pageContext.request.contextPath}/images/dashboard.png" class="dashboard-images"><a href="${pageContext.request.contextPath}/UserDashboardController">Dashboard </a>
        </li>
        <ul class="submenu">
          <li><img src="${pageContext.request.contextPath}/images/properties.png" class="dashboard-images"><a href="${pageContext.request.contextPath}/PropertyListingController">Property</a></li>
          <li><img src="${pageContext.request.contextPath}/images/report.png" class="dashboard-images"><a href="${pageContext.request.contextPath}/ReportGenerationController">Reports</a></li>
          <li><img src="${pageContext.request.contextPath}/images/contact.png" class="dashboard-images"><a href="${pageContext.request.contextPath}/ContactListingController">Contact</a></li>
        </ul>
        <li><img src="${pageContext.request.contextPath}/images/favorites.png" class="dashboard-images"><a href="${pageContext.request.contextPath}/FavouriteController">Favorites</a></li>
        <button class="logout-btn">
          <img src="${pageContext.request.contextPath}/images/logout.webp" class="icon" />
          Logout
        </button>
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