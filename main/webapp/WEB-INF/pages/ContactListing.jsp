<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Listing</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ContactListing.css">
</head>
<body>
	<jsp:include page="Navbar.jsp"/>
	
	
	<div class="main-container">
    
    <!-- Sidebar -->
    <jsp:include page="UserSidebar.jsp"/>

    <!-- Content -->
    <main class="contact-list">
      <div class="contact-header">
        <h1>Contact List</h1>
        <div class="contact-controls">
          <input type="text" placeholder="Search" class="search-bar" />
          <button class="new-btn">+ New Contact</button>
        </div>
      </div>

      <table class="contact-table">
        <thead>
          <tr>
            <th><input type="checkbox"></th>
            <th>User Image</th>
            <th>ID</th>
            <th>Name</th>
            <th>Email Address</th>
            <th>Phone Number</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="${pageContext.request.contextPath}/images/user.png" class="user-img" /></td>
            <td>1</td>
            <td>Ina</td>
            <td>iamina123@gmail.com</td>
            <td>9840366440</td>
            <td class="actions">
              <img src="${pageContext.request.contextPath}/images/tick mark icon.webp" />
              <img src="${pageContext.request.contextPath}/images/cross mark.jpg" />
              <img src="${pageContext.request.contextPath}/images/delete-icon.png" />
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="${pageContext.request.contextPath}/images/unique-visitor-2903360-2408030.webp" class="user-img" /></td>
            <td>2</td>
            <td>Ishan</td>
            <td>iamishana123@gmail.com</td>
            <td>9853366440</td>
            <td class="actions">
              <img src="${pageContext.request.contextPath}/images/tick mark icon.webp" />
              <img src="${pageContext.request.contextPath}/images/cross mark.jpg" />
              <img src="${pageContext.request.contextPath}/images/delete-icon.png" />
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="${pageContext.request.contextPath}/images/user.png" class="user-img" /></td>
            <td>3</td>
            <td>Suyog</td>
            <td>iamdurgeshthapafan@gmail.com</td>
            <td>9853367890</td>
            <td class="actions">
              <img src="${pageContext.request.contextPath}/images/tick mark icon.webp" />
              <img src="${pageContext.request.contextPath}/images/cross mark.jpg" />
              <img src="${pageContext.request.contextPath}/images/delete-icon.png" />
            </td>
          </tr>
        </tbody>
      </table>

      <div class="entries-dropdown">
        Show 
        <select>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3" selected>3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
        </select> 
        entries
    </div>
    </main>
  </div>
	
</body>
</html>