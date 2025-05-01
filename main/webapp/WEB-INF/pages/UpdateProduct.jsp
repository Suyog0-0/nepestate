<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Navbar.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UpdateProduct.css" />
	<style>
    .form-label {
      font-family: Arial, sans-serif;
      font-size: 18px;
      color: #6c757d;
      font-weight: 500;
    }
    .image-preview img {
      max-width: 350px;
      border-radius: 10px;
    }
    .subColumn{
      display: flex;
      flex-direction: column;
      gap: 5px;
      margin-bottom: 20px;
  </style>
<body>
<jsp:include page="Navbar.jsp"/>
<!-- Main Form -->
  <div class="mainSquare">

    <div class="subRow">
      <span style="font-family: Arial, sans-serif; font-size: 30px; font-weight:700;">UPDATE PROPERTY</span>
    </div>

    <div class="subRowFirst">
      <span style="font-family: Arial, sans-serif; font-size: 24px;">Basic Details</span>
    </div>

    
    <div style="display: flex; gap: 290px;margin-top:40px; ">
      <div>
        <div class="subColumn">
          <label class="form-label">Title</label>
          <input type="text" class="underline-long">

        </div>

        <div style="display: flex; gap: 200px;margin-top:40px;">
          <div class="subColumn">
            <label class="form-label">Property Type</label>
            <div class="button-group">
              <input type="checkbox" id="colony" class="feature-checkbox">
              <label for="colony" class="feature-label">Colony</label>
              
              <input type="checkbox" id="apartment" class="feature-checkbox">
              <label for="apartment" class="feature-label">Apartment</label>
              
              <input type="checkbox" id="house" class="feature-checkbox">
              <label for="house" class="feature-label">House</label>
            </div>
          </div>


          <div class="subColumn">
            <label class="form-label">Price</label>
            <input type="text" class="underline-short underline-short-price">
          </div>
        </div>

        <div class="subRowDetails">
          <div class="subColumn">
            <label class="form-label">Address</label>
            <input type="text" class="underline-short">
          </div>
          <div class="subColumn">
            <label class="form-label">City</label>
            <input type="text" class="underline-short">
          </div>
          <div class="subColumn">
            <label class="form-label">Area</label>
            <input type="text" class="underline-short">
          </div>
  
        </div>
      </div>

      <div class="image-preview">
        <img src="${pageContext.request.contextPath}/images/updateImage.png" alt="Background Image">
      </div>
    </div>

  
    <div class="subRow">
      <div class="subColumn">
        <label class="form-label">Description</label>
        <textarea name="description" class="property-description"></textarea>
        </div>
      </div>
     
    <div class="subRow">
      <div class="subColumn">
        <label class="form-label">Amenities</label>
        <div class="button-group">

          <input type="checkbox" id="cctv" class="feature-checkbox">
      <label for="cctv" class="feature-label">CCTV</label>
      
      <input type="checkbox" id="parking" class="feature-checkbox" checked>
      <label for="parking" class="feature-label">Parking</label>
      
      <input type="checkbox" id="cafeteria" class="feature-checkbox">
      <label for="cafeteria" class="feature-label">Cafeteria</label>
      
      <input type="checkbox" id="lift" class="feature-checkbox" checked>
      <label for="lift" class="feature-label">Lift</label>
      
      <input type="checkbox" id="garden" class="feature-checkbox">
      <label for="garden" class="feature-label">Garden</label>
      
      <input type="checkbox" id="swimming-pool" class="feature-checkbox">
      <label for="swimming-pool" class="feature-label">Swimming Pool</label>
      
      <input type="checkbox" id="gym" class="feature-checkbox"checked>
      <label for="gym" class="feature-label">Gym</label>
      
      <input type="checkbox" id="wifi" class="feature-checkbox">
      <label for="wifi" class="feature-label">WI-FI</label>
      
      <input type="checkbox" id="dining-room" class="feature-checkbox">
      <label for="dining-room" class="feature-label">Dining Room</label>
      
      <input type="checkbox" id="fencing" class="feature-checkbox" checked>
      <label for="fencing" class="feature-label">Fencing</label>
      
      <input type="checkbox" id="tv-cable" class="feature-checkbox">
      <label for="tv-cable" class="feature-label">TV Cable</label>
      
      <input type="checkbox" id="electricity-backup" class="feature-checkbox">
      <label for="electricity-backup" class="feature-label">Electricity Backup</label>
      
      <input type="checkbox" id="conditioning" class="feature-checkbox"checked>
      <label for="conditioning" class="feature-label">Conditioning</label>
      
      <input type="checkbox" id="fire-alarm" class="feature-checkbox" checked>
      <label for="fire-alarm" class="feature-label">Fire Alarm</label>
      
      <input type="checkbox" id="fire-place" class="feature-checkbox">
      <label for="fire-place" class="feature-label">Fire Place</label>
      
      <input type="checkbox" id="solar-water" class="feature-checkbox">
      <label for="solar-water" class="feature-label">Solar Water</label>
      
      <input type="checkbox" id="security" class="feature-checkbox" checked>
      <label for="security" class="feature-label">Security</label>
    
      <input type="checkbox" id="jacuzzi" class="feature-checkbox">
      <label for="jacuzzi" class="feature-label">Jacuzzi</label>
      
      <input type="checkbox" id="kitchen" class="feature-checkbox">
      <label for="kitchen" class="feature-label">Kitchen</label>
      
      <input type="checkbox" id="drainage" class="feature-checkbox">
      <label for="drainage" class="feature-label">Drainage</label>
      
      <input type="checkbox" id="washing-machine" class="feature-checkbox">
      <label for="washing-machine" class="feature-label">Washing Machine</label>
  
      </div>
    </div>
  </div>


      <div class="subRow">
        <button class="update-button">UPDATE PROPERTY</button>
      </div>
    </div>

<br>
<br>


 <div class="footerSection">
    <jsp:include page="Footer.jsp" />
</div>

 
</body>
</html>