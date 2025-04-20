<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/propertylisting.css">
</head>
<body>

	<!-- Taskbar -->
  <jsp:include page="navbar.jsp"/>

  <!-- Main Layout -->
  <div class="main-container">
    
    <!-- Sidebar -->
    <jsp:include page="adminsidebar.jsp"/>

    <!-- Content -->
    <main class="property-list">
      <div class="property-header">
        <h1>Property List</h1>
        <div class="property-controls">
          <input type="text" placeholder="Search" class="search-bar" />
          <button class="new-btn">+ New Product</button>
        </div>
      </div>

      <table class="property-table">
        <thead>
          <tr>
            <th><input type="checkbox"></th>
            <th>Property Image</th>
            <th>Address</th>
            <th>Type</th>
            <th>Price</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="/images/house1.webp" class="property-img" /></td>
            <td>Thamel</td>
            <td>House</td>
            <td>1 crore</td>
            <td><span class="status available">✔ Available</span></td>
            <td class="actions">
              <img src="/images/edit-246.png" />
              <img src="/images/view-icon-614x460.png" />
              <img src="/images/delete-icon.png" />
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="/images/house2.webp" class="property-img" /></td>
            <td>Baneshwor</td>
            <td>House</td>
            <td>2 crore</td>
            <td><span class="status not-available">✖ Not Available</span></td>
            <td class="actions">
              <img src="/images/edit-246.png" />
              <img src="/images/view-icon-614x460.png" />
              <img src="/images/delete-icon.png" />
            </td>
          </tr>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="/images/house3.webp" class="property-img" /></td>
            <td>Jamal</td>
            <td>Apartment</td>
            <td>3 crore</td>
            <td><span class="status available">✔ Available</span></td>
            <td class="actions">
              <img src="/images/edit-246.png" />
              <img src="/images/view-icon-614x460.png" />
              <img src="/images/delete-icon.png" />
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