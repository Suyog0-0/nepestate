<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/navbar.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UpdateProduct.css" />
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
<jsp:include page="navbar.jsp"/>
<!-- Main Form -->
  <div class="mainSquare">

    <div class="subRow">
      <span style="font-family: Arial, sans-serif; font-size: 28px;">UPDATE PROPERTY</span>
    </div>

    <div class="subRowFirst">
      <span style="font-family: Arial, sans-serif; font-size: 24px;">Basic Details</span>
    </div>

    <!-- Property Info + Image Row -->
    <div style="display: flex; gap: 450px;  ">
      <div>
        <div class="subColumn">
          <label class="form-label">Title</label>
          <div class="underline-long">House for Sale At Tripureshwor</div>
        </div>

        <div style="display: flex; gap: 180px;">
          <div class="subColumn">
            <label class="form-label">Property Type</label>
            <div class="button-group">
              <button class="button-active">Colony</button>
              <button class="button">House</button>
              <button class="button">Apartment</button>
            </div>
          </div>

          <div class="subColumn">
            <label class="form-label">Price</label>
            <div class="underline-short">Rs 1,50,00,000</div>
          </div>
        </div>

        <div class="subRowDetails">
          <div class="subColumn">
            <label class="form-label">Address</label>
            <div class="underline-short">Tripureshwor</div>
          </div>
          <div class="subColumn">
            <label class="form-label">City</label>
            <div class="underline-short">Kathmandu</div>
          </div>
          <div class="subColumn">
            <label class="form-label">Area</label>
            <div class="underline-short">2000 sq ft</div>
          </div>
          <div class="subColumn">
            <label class="form-label">Municipality</label>
            <div class="underline-short">Kathmandu</div>
          </div>
          <div class="subColumn">
            <label class="form-label">Ward No.</label>
            <div class="underline-short">24</div>
          </div>
        </div>
      </div>

      <div class="image-preview">
        <img src="updateImage.png" alt="Property Image" />
      </div>
    </div>

    <!-- Description -->
    <div class="subRow">
      <div class="subColumn">
        <label class="form-label">Description</label>
        <div class="property-description">
          This beautiful house at Tripureshwor is located at a prime location which is very decently priced at Rs 1,50,00,000.
          The house is found right next to the road but is found to be very quiet and peaceful.
        </div>
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

    <!-- Submit -->
    <div class="subRow">
      <button class="update-button">UPDATE PROPERTY</button>
    </div>

  </div>
</body>
</html>