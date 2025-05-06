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
		<link rel="stylesheet" type="text/css" 
		href="${pageContext.request.contextPath}/css/Footer.css">
	<style>
	    .form-label {
	      font-family: Arial, sans-serif;
	      font-size: 18px;
	      color: #8b8b8b;
	      font-weight: 500;
	    }
	    .subColumn{
	      display: flex;
	      flex-direction: column;
	      gap: 5px;
	  }
	  </style>
	
	<body>
	<jsp:include page="Navbar.jsp"/>
	<div class="mainSquare mainSquareHeight">
	<form action="${pageContext.request.contextPath}/PostPropertyController" method="post">
	    <div class="subRow">
	      <span style="font-family: Arial, sans-serif; font-size: 30px; font-weight:700;">POST PROPERTY</span>
	    </div>
	
	    <div class="subRowFirst">
	      <span style="font-family: Arial, sans-serif; font-size: 24px;">Basic Details</span>
	    </div>
			<c:if test="${not empty error}">
				<p class="error-message">${error}</p>
			</c:if>
			
			<c:if test="${not empty success}">
				<p class="success-message">${success}</p>
			</c:if>
	    
	    <div style="display: flex; gap:170px;margin-top:40px; ">
	     
	        <div class="subColumn">
	          <label class="form-label">Title</label>
	          <input type="text" name="title" class="underline-long" value="${title}">
	        </div>
	          <div class="subColumn">
	            <label class="form-label">Price</label>
	            <input type="text" name="price" class="underline-short" value="${price}">
	          </div> 
	      </div>
	      <div style="margin-top:40px;">
	        <label class="form-label">Property Type</label>
	        <div class="button-group">
	          
	            <input type="radio" id="colony" name="propertyType" value="colony" class="feature-checkbox" 
	                <c:if test="${propertyType eq 'colony'}">checked</c:if>>
	            <label for="colony" class="feature-label">Colony</label>
	            
	            <input type="radio" id="apartment" name="propertyType" value="apartment" class="feature-checkbox"
	                <c:if test="${propertyType eq 'apartment'}">checked</c:if>>
	            <label for="apartment" class="feature-label">Apartment</label>
	            
	            <input type="radio" id="house" name="propertyType" value="house" class="feature-checkbox"
	                <c:if test="${propertyType eq 'house'}">checked</c:if>>
	            <label for="house" class="feature-label">House</label>
	        </div>
	    </div>
	
	        <div class="subRowDetails">
	          <div class="subColumn">
	            <label class="form-label">Address</label>
	            <input type="text" name="address" class="underline-short"  value="${address}">
	          </div>
	          <div class="subColumn">
	            <label class="form-label">City</label>
	            <input type="text" name="city" class="underline-short"  value="${city}">
	          </div>
	          <div class="subColumn">
	            <label class="form-label">Area</label>
	            <input type="text" name="area" class="underline-short"  value="${area}"> 
	          </div>
	        </div>
	
	        <div style=" display: flex; margin-top: 50px; gap: 19.5%;">
	          <div class="subColumn">
	            <label class="form-label">Upload Thumbnail</label>
	            <input type="file" name="thumbnail" id="thumbnail" style="display:none;">
	            <label for="thumbnail">
	              <img src="${pageContext.request.contextPath}/images/uploadImage.png" style="max-width:36.48px;margin-left:28px;padding:20px;cursor:pointer;" alt="Upload Thumbnail">
	            </label>
	          </div>
	          <div class="subColumn">
	            <label class="form-label">Upload Photos</label>
	            <input type="file" name="photos" id="photos" multiple style="display:none;">
	            <label for="photos">
	              <img src="${pageContext.request.contextPath}/images/uploadImage.png" style="max-width:36.48px;margin-left:24px;padding:20px;cursor:pointer;" alt="Upload Photos">
	            </label>
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
	      			<c:set var="amenities" value="${selectedAmenities}" />
					<input type="checkbox" id="cctv" name="amenity_cctv" value="cctv" class="feature-checkbox"
	                    <c:if test="${amenities.contains('cctv')}">checked</c:if>>
	                <label for="cctv" class="feature-label">CCTV</label>
	                
	                <input type="checkbox" id="parking" name="amenity_parking" value="parking" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('parking')}">checked</c:if>>
	                <label for="parking" class="feature-label">Parking</label>
	                
	                <input type="checkbox" id="cafeteria" name="amenity_cafeteria" value="cafeteria" class="feature-checkbox"
	                    <c:if test="${amenities.contains('cafeteria')}">checked</c:if>>
	                <label for="cafeteria" class="feature-label">Cafeteria</label>
	                
	                <input type="checkbox" id="lift" name="amenity_lift" value="lift" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('lift')}">checked</c:if>>
	                <label for="lift" class="feature-label">Lift</label>
	                
	                <input type="checkbox" id="garden" name="amenity_garden" value="garden" class="feature-checkbox"
	                    <c:if test="${amenities.contains('garden')}">checked</c:if>>
	                <label for="garden" class="feature-label">Garden</label>
	                
	                <input type="checkbox" id="swimming-pool" name="amenity_swimming_pool" value="swimming-pool" class="feature-checkbox"
	                    <c:if test="${amenities.contains('swimming-pool')}">checked</c:if>>
	                <label for="swimming-pool" class="feature-label">Swimming Pool</label>
	                
	                <input type="checkbox" id="gym" name="amenity_gym" value="gym" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('gym')}">checked</c:if>>
	                <label for="gym" class="feature-label">Gym</label>
	                
	                <input type="checkbox" id="wifi" name="amenity_wifi" value="wifi" class="feature-checkbox"
	                    <c:if test="${amenities.contains('wifi')}">checked</c:if>>
	                <label for="wifi" class="feature-label">WI-FI</label>
	                
	                <input type="checkbox" id="dining-room" name="amenity_dining_room" value="dining-room" class="feature-checkbox"
	                    <c:if test="${amenities.contains('dining-room')}">checked</c:if>>
	                <label for="dining-room" class="feature-label">Dining Room</label>
	                
	                <input type="checkbox" id="fencing" name="amenity_fencing" value="fencing" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('fencing')}">checked</c:if>>
	                <label for="fencing" class="feature-label">Fencing</label>
	                
	       
	                <input type="checkbox" id="tv-cable" name="amenity_tv_cable" value="tv-cable" class="feature-checkbox"
	                    <c:if test="${amenities.contains('tv-cable')}">checked</c:if>>
	                <label for="tv-cable" class="feature-label">TV Cable</label>
	                
	                <input type="checkbox" id="electricity-backup" name="amenity_electricity_backup" value="electricity-backup" class="feature-checkbox"
	                    <c:if test="${amenities.contains('electricity-backup')}">checked</c:if>>
	                <label for="electricity-backup" class="feature-label">Electricity Backup</label>
	                
	                <input type="checkbox" id="conditioning" name="amenity_conditioning" value="conditioning" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('conditioning')}">checked</c:if>>
	                <label for="conditioning" class="feature-label">Conditioning</label>
	                
	                <input type="checkbox" id="fire-alarm" name="amenity_fire_alarm" value="fire-alarm" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('fire-alarm')}">checked</c:if>>
	                <label for="fire-alarm" class="feature-label">Fire Alarm</label>
	                
	                <input type="checkbox" id="fire-place" name="amenity_fire_place" value="fire-place" class="feature-checkbox"
	                    <c:if test="${amenities.contains('fire-place')}">checked</c:if>>
	                <label for="fire-place" class="feature-label">Fire Place</label>
	                
	                <input type="checkbox" id="solar-water" name="amenity_solar_water" value="solar-water" class="feature-checkbox"
	                    <c:if test="${amenities.contains('solar-water')}">checked</c:if>>
	                <label for="solar-water" class="feature-label">Solar Water</label>
	                
	                <input type="checkbox" id="security" name="amenity_security" value="security" class="feature-checkbox"
	                    <c:if test="${empty amenities ? true : amenities.contains('security')}">checked</c:if>>
	                <label for="security" class="feature-label">Security</label>
	                
	                <input type="checkbox" id="jacuzzi" name="amenity_jacuzzi" value="jacuzzi" class="feature-checkbox"
	                    <c:if test="${amenities.contains('jacuzzi')}">checked</c:if>>
	                <label for="jacuzzi" class="feature-label">Jacuzzi</label>
	                
	                <input type="checkbox" id="kitchen" name="amenity_kitchen" value="kitchen" class="feature-checkbox"
	                    <c:if test="${amenities.contains('kitchen')}">checked</c:if>>
	                <label for="kitchen" class="feature-label">Kitchen</label>
	                
	                <input type="checkbox" id="drainage" name="amenity_drainage" value="drainage" class="feature-checkbox"
	                    <c:if test="${amenities.contains('drainage')}">checked</c:if>>
	                <label for="drainage" class="feature-label">Drainage</label>
	                
	                <input type="checkbox" id="washing-machine" name="amenity_washing_machine" value="washing-machine" class="feature-checkbox"
	                    <c:if test="${amenities.contains('washing-machine')}">checked</c:if>>
	                <label for="washing-machine" class="feature-label">Washing Machine</label>
	
	            
	              </div>
	            </div>
	          </div>
	            <div class="subRow">
	                <button type="submit" name="submit" class="update-button">POST PROPERTY</button>
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