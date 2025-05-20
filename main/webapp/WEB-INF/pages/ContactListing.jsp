<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.nepestate.model.CustomerModel" %>

<%
    List<CustomerModel> interestedCustomers = (List<CustomerModel>) request.getAttribute("interestedCustomers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Listing</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ContactListing.css">
</head>
<body>
	<%
    String popupMessage = (String) request.getAttribute("popupMessage");
    if (popupMessage != null) {
%>
    <div style="background-color: #d4edda; color: #155724; padding: 15px; text-align: center; margin: 10px; border: 1px solid #c3e6cb;">
        <%= popupMessage %>
    </div>
<%
    }
%>
	<jsp:include page="Navbar.jsp"/>
	
	
	<div class="main-container">
    
    <!-- Sidebar -->
    <jsp:include page="UserSidebar.jsp"/>
	
    <!-- Content -->
    <main class="contact-list">
      <div class="contact-header">
        <h1>Contact List</h1>
        <div class="contact-controls">
        </div>
      </div>

      <table class="contact-table">
        <thead>
          <tr>
            <th><input type="checkbox"></th>
            <th>User Image</th>
            <th>ID</th>
            <th>Name</th>
            <th>Username</th>
            <th>Phone Number</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
        <%
            if (interestedCustomers != null) {
                for (CustomerModel customer : interestedCustomers) {
        %>
          <tr>
            <td><input type="checkbox" /></td>
            <td><img src="uploads/<%= customer.getCustomer_ProfilePicture() %>" class="user-img" /></td>
            <td><%= customer.getCustomerID() %></td>
            <td><%= customer.getCustomer_FirstName() %></td>
            <td><%= customer.getCustomer_Username() %></td>
            <td><%= customer.getCustomer_PhoneNumber() %></td>
            <td class="actions">
            <form method="post" action="ContactListingController" style="display:inline;">
		    <input type="hidden" name="customerId" value="<%= customer.getCustomerID() %>"/>
		    <input type="hidden" name="action" value="notify"/>
              <button type="submit" style="border:none; background:none;">
		        <img src="${pageContext.request.contextPath}/images/tick mark icon.webp" alt="Notify"/>
		    </button>
		  </form>
		  <form method="post" action="ContactListingController" style="display:inline;">
			    <input type="hidden" name="customerId" value="<%= customer.getCustomerID() %>"/>
			    <input type="hidden" name="action" value="delete"/>
			    <button type="submit" style="border:none; background:none;">
			        <img src="${pageContext.request.contextPath}/images/cross mark.jpg" alt="Remove"/>
			    </button>
		  </form>
            </td>
          </tr>
          <%
        } // closes for loop
    } else {
	%>
  <tr><td colspan="7">No interested customers found.</td></tr>
<%
    } // closes if
%>
        </tbody>
      </table>

      <div class="entries-dropdown">
    </div>
    </main>
  </div>
	
</body>
</html>