<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Property</title>
</head>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/navbar.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UpdateProduct.css" />
	<link rel="stylesheet" type="text/css" 
	href="${pageContext.request.contextPath}/css/Footer.css">
<style>
    .form-label {
      font-family: Arial, sans-serif;
      font-size: 18px;
      color: #8b8b8b;
    }
    .image-preview img {
      max-width: 300px;
      border-radius: 10px;
    }
    .subColumn {
      margin-bottom: 20px;
    }
  </style>

<body>

<jsp:include page="Navbar.jsp" />

<div class="mainSquare">

    <div class="subRow">
      <span style="font-family: Arial, sans-serif; font-size: 28px;">POST PROPERTY</span>
    </div>

    <div class="subRowFirst">
      <span style="font-family: Arial, sans-serif; font-size: 24px;">Basic Details</span>
    </div>

    <!-- Property Info + Image Row -->
    <!-- <div style="display: flex; gap: 450px;  "> -->
      <div class="subRow">
        <div class="subColumn">
          <label class="form-label">Title</label>
          <input type="text" class="underline-long">
        </div>
          <div class="subColumn">
            <label class="form-label">Price</label>
            <input type="text" class="underline-short">
          </div>
        
      </div>

        <div class="subRow">
          <div class="subColumn">
            <label class="form-label">Property Type</label>
            <div class="button-group">
              <button class="button-active">Colony</button>
              <button class="button">House</button>
              <button class="button">Apartment</button>
            </div>
          </div>
        </div>

        <div class="subRowDetails">
          <div class="subColumn">
            <label class="form-label">Address</label>
            <input type="text" class="underline-short">
          </div>
          <div class="subColumn">
            <label class="form-label">City</label>
            <input type="text"class="underline-short">
          </div>
          <div class="subColumn">
            <label class="form-label">Area</label>
            <input type="text" class="underline-short">
          </div>
          <div class="subColumn">
            <label class="form-label">Municipality</label>
            <input type="text" class="underline-short">
          </div>
          <div class="subColumn">
            <label class="form-label">Ward No.</label>
            <input type="text" class="underline-short">
          </div>
        </div>
        <div class="subRow">
          <div class="subColumn">
            <label class="form-label">Upload Thumbnail</label>
            <img src="${pageContext.request.contextPath}/images/uploadImage.png" style="max-width:36.48px;margin-left:35px;padding:10px" > 
          </div>
          <div class="subColumn">
            <label class="form-label">Upload Photos</label>
            <img src="${pageContext.request.contextPath}/images/uploadImage.png" style="max-width:36.48px;margin-left:35px;padding:10px" >
          </div>
        </div>
        <div class="subRow">
            <div class="subColumn">
              <label class="form-label">Description</label>
              <input type="text"class="property-description">
            </div>
          </div>
      
          <!-- Amenities -->
          <div class="subRow">
            <div class="subColumn">
              <label class="form-label">Amenities</label>
              <div class="button-group">
                <button class="button">CCTV</button>
                <button class="button-active">Parking</button>
                <button class="button">Cafeteria</button>
                <button class="button-active">Lift</button>
                <button class="button">Garden</button>
                <button class="button">Swimming Pool</button>
                <button class="button-active">Gym</button>
                <button class="button">WI-FI</button>
                <button class="button">Dining Room</button>
                <button class="button-active">Fencing</button>
                <button class="button">TV Cable</button>
                <button class="button">Electricity Backup</button>
                <button class="button-active">Conditioning</button>
                <button class="button-active">Fire Alarm</button>
                <button class="button">Fire Place</button>
                <button class="button">Solar Water</button>
                <button class="button-active">Security</button>
                <button class="button">Jacuzzi</button>
                <button class="button">Kitchen</button>
                <button class="button-active">Fencing</button>
                <button class="button">Drainage</button>
                <button class="button">Washing Machine</button>
              </div>
            </div>
            </div>
            <div class="subRow">
                <button class="update-button">POST PROPERTY</button>
              </div>
          
      </div>

<div class="footerSection">
    <jsp:include page="Footer.jsp" />
</div>
<br>
<br>
</body>
</html>