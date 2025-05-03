<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Navbar.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UpdateProperty.css" />
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
  <div class="mainSquare">
  <form action="${pageContext.request.contextPath}/UpdatePropertyController" method="post">
<input type="hidden" name="propertyId" value="${propertyId}">
    <div class="subRow">
      <span style="font-family: Arial, sans-serif; font-size: 30px; font-weight:700;">UPDATE PROPERTY</span>
    </div>

    <c:if test="${not empty error}">
      <div class="subRow">
        <p class="error-message">${error}</p>
      </div>
    </c:if>
    
    <c:if test="${not empty success}">
      <div class="subRow">
        <p class="success-message">${success}</p>
      </div>
    </c:if>
    <div class="subRowFirst">
      <span style="font-family: Arial, sans-serif; font-size: 24px;">Basic Details</span>
    </div>

    
    <div style="display: flex; gap: 290px;margin-top:40px; ">
      <div>
        <div class="subColumn">
          <label class="form-label">Title</label>
          <input type="text" name="title" class="underline-long" value="${title}">

        </div>

        <div style="display: flex; gap: 200px;margin-top:40px;">
          <div class="subColumn">
            <label class="form-label">Property Type</label>
            <div class="button-group">
              <input type="radio" id="colony" name="propertyType" value="Colony" class="feature-checkbox" ${propertyType eq 'Colony' ? 'checked' : ''}>
                  <label for="colony" class="feature-label">Colony</label>
                  
                  <input type="radio" id="apartment" name="propertyType" value="Apartment" class="feature-checkbox" ${propertyType eq 'Apartment' ? 'checked' : ''}>
                  <label for="apartment" class="feature-label">Apartment</label>
                  
                  <input type="radio" id="house" name="propertyType" value="House" class="feature-checkbox" ${propertyType eq 'House' ? 'checked' : ''}>
                  <label for="house" class="feature-label">House</label>
            </div>
          </div>


          <div class="subColumn">
            <label class="form-label">Price</label>
            <input type="text" name="price" class="underline-short underline-short-price" value="${price}">
          </div>
        </div>

        <div class="subRowDetails">
          <div class="subColumn">
            <label class="form-label">Address</label>
            <input type="text" name="address" class="underline-short" value="${address}">
          </div>
          <div class="subColumn">
            <label class="form-label">City</label>
            <input type="text" name="city" class="underline-short" value="${city}">
          </div>
          <div class="subColumn">
            <label class="form-label">Area</label>
            <input type="text" name="area" class="underline-short" value="${area}">
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
        <textarea name="description" class="property-description">${description}</textarea>
        </div>
      </div>
     
    <div class="subRow">
      <div class="subColumn">
        <label class="form-label">Amenities</label>
        <div class="button-group">

     <input type="checkbox" id="cctv" name="amenity_cctv" class="feature-checkbox" ${amenities.contains('cctv') ? 'checked' : ''}>
          <label for="cctv" class="feature-label">CCTV</label>
          
          <input type="checkbox" id="parking" name="amenity_parking" class="feature-checkbox" ${amenities.contains('parking') ? 'checked' : ''}>
          <label for="parking" class="feature-label">Parking</label>
          
          <input type="checkbox" id="cafeteria" name="amenity_cafeteria" class="feature-checkbox" ${amenities.contains('cafeteria') ? 'checked' : ''}>
          <label for="cafeteria" class="feature-label">Cafeteria</label>
          
          <input type="checkbox" id="lift" name="amenity_lift" class="feature-checkbox" ${amenities.contains('lift') ? 'checked' : ''}>
          <label for="lift" class="feature-label">Lift</label>
          
          <input type="checkbox" id="garden" name="amenity_garden" class="feature-checkbox" ${amenities.contains('garden') ? 'checked' : ''}>
          <label for="garden" class="feature-label">Garden</label>
          
          <input type="checkbox" id="swimming-pool" name="amenity_swimming_pool" class="feature-checkbox" ${amenities.contains('swimming-pool') ? 'checked' : ''}>
          <label for="swimming-pool" class="feature-label">Swimming Pool</label>
          
          <input type="checkbox" id="gym" name="amenity_gym" class="feature-checkbox" ${amenities.contains('gym') ? 'checked' : ''}>
          <label for="gym" class="feature-label">Gym</label>
          
          <input type="checkbox" id="wifi" name="amenity_wifi" class="feature-checkbox" ${amenities.contains('wifi') ? 'checked' : ''}>
          <label for="wifi" class="feature-label">WI-FI</label>
          
          <input type="checkbox" id="dining-room" name="amenity_dining_room" class="feature-checkbox" ${amenities.contains('dining-room') ? 'checked' : ''}>
          <label for="dining-room" class="feature-label">Dining Room</label>
          
          <input type="checkbox" id="fencing" name="amenity_fencing" class="feature-checkbox" ${amenities.contains('fencing') ? 'checked' : ''}>
          <label for="fencing" class="feature-label">Fencing</label>
          
          <input type="checkbox" id="tv-cable" name="amenity_tv_cable" class="feature-checkbox" ${amenities.contains('tv-cable') ? 'checked' : ''}>
          <label for="tv-cable" class="feature-label">TV Cable</label>
          
          <input type="checkbox" id="electricity-backup" name="amenity_electricity_backup" class="feature-checkbox" ${amenities.contains('electricity-backup') ? 'checked' : ''}>
          <label for="electricity-backup" class="feature-label">Electricity Backup</label>
          
          <input type="checkbox" id="conditioning" name="amenity_conditioning" class="feature-checkbox" ${amenities.contains('conditioning') ? 'checked' : ''}>
          <label for="conditioning" class="feature-label">Conditioning</label>
          
          <input type="checkbox" id="fire-alarm" name="amenity_fire_alarm" class="feature-checkbox" ${amenities.contains('fire-alarm') ? 'checked' : ''}>
          <label for="fire-alarm" class="feature-label">Fire Alarm</label>
          
          <input type="checkbox" id="fire-place" name="amenity_fire_place" class="feature-checkbox" ${amenities.contains('fire-place') ? 'checked' : ''}>
          <label for="fire-place" class="feature-label">Fire Place</label>
          
          <input type="checkbox" id="solar-water" name="amenity_solar_water" class="feature-checkbox" ${amenities.contains('solar-water') ? 'checked' : ''}>
          <label for="solar-water" class="feature-label">Solar Water</label>
          
          <input type="checkbox" id="security" name="amenity_security" class="feature-checkbox" ${amenities.contains('security') ? 'checked' : ''}>
          <label for="security" class="feature-label">Security</label>
        
          <input type="checkbox" id="jacuzzi" name="amenity_jacuzzi" class="feature-checkbox" ${amenities.contains('jacuzzi') ? 'checked' : ''}>
          <label for="jacuzzi" class="feature-label">Jacuzzi</label>
          
          <input type="checkbox" id="kitchen" name="amenity_kitchen" class="feature-checkbox" ${amenities.contains('kitchen') ? 'checked' : ''}>
          <label for="kitchen" class="feature-label">Kitchen</label>
          
          <input type="checkbox" id="drainage" name="amenity_drainage" class="feature-checkbox" ${amenities.contains('drainage') ? 'checked' : ''}>
          <label for="drainage" class="feature-label">Drainage</label>
          
          <input type="checkbox" id="washing-machine" name="amenity_washing_machine" class="feature-checkbox" ${amenities.contains('washing-machine') ? 'checked' : ''}>
          <label for="washing-machine" class="feature-label">Washing Machine</label>
  
      </div>
    </div>
  </div>


      <div class="subRow">
        <button type="submit" class="update-button">UPDATE PROPERTY</button>
      </div>
      </form>
    </div>

<br>
<br>


 <div class="footerSection">
    <jsp:include page="Footer.jsp" />
</div>

 
</body>
</html>