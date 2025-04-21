<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Product.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div style="display: flex;">
        <div class="filter">
            <div>
                <h2>Filter Properties</h2>
                    <div class="filter_button">
                        <div>
                            <h4 style="margin-bottom: 10px;">Properties By</h4>
                            <button>By Nepestate</button>
                            <button>By Owners</button>
                        </div>
                    </div>
            </div>
            
            <div>
                <h2 style="margin-top: 25px;">Location</h2>
                <div>
                    <label for="location"></label>
                    <div>
                        <input list="location-options" id="locations" name="locations" class="combobox">
                        <datalist id="location-options">
                            <option value="Kathmandu">
                            <option value="Lalitpur">
                            <option value="Bhaktapur">
                        </datalist>
                    </div>
                    
                </div>
            </div>
    
            <div>
                <h2 style="margin-top: 25px;">Category</h2>
                <div>
                    <button>Housing</button>
                    <button>Appartment</button>
                    <button style="margin-top: 10px; margin-left:70px">House</button>
                </div>
            </div>
    
            <div>
                <h2 style="margin-top: 25px;">Filter by Price</h2>
                <div>
                    <button>Low-High</button>
                    <button>High-Low</button>
                </div>
            </div>
        </div>
        <div class="listing">
            <a href="#">
                <div class="card" >

                    <img src="${pageContext.request.contextPath}/images/house.jpeg">

                    <div class="card-content">
                      <h3>Rs.5,00,00,000</h3>
                      <span class="tag">Home</span>
                    <div class="location">Kamalpokhari, Kathmandu</div>
                    <div class="description">🏠 Beautiful home for sale in a scenic and peaceful location!</div>
                    <div class="status">✅ Available</div>
                    </div>
                </div>
            </a>
            
            <a href="#">
                <div class="card" >

                    <img src="${pageContext.request.contextPath}/images/house.jpeg">

                    <div class="card-content">
                      <h3>Rs.5,00,00,000</h3>
                      <span class="tag">Home</span>
                    <div class="location">Kamalpokhari, Kathmandu</div>
                    <div class="description">🏠 Beautiful home for sale in a scenic and peaceful location!</div>
                    <div class="status">✅ Available</div>
                    </div>
                </div>
            </a>

            <a href="#">
                <div class="card" >

                    <img src="${pageContext.request.contextPath}/images/house.jpeg">

                    <div class="card-content">
                      <h3>Rs.5,00,00,000</h3>
                      <span class="tag">Home</span>
                    <div class="location">Kamalpokhari, Kathmandu</div>
                    <div class="description">🏠 Beautiful home for sale in a scenic and peaceful location!</div>
                    <div class="status">✅ Available</div>
                    </div>
                </div>
            </a>

            <a href="#">
                <div class="card" >

                    <img src="${pageContext.request.contextPath}/images/house.jpeg">

                    <div class="card-content">
                      <h3>Rs.5,00,00,000</h3>
                      <span class="tag">Home</span>
                    <div class="location">Kamalpokhari, Kathmandu</div>
                    <div class="description">🏠 Beautiful home for sale in a scenic and peaceful location!</div>
                    <div class="status">✅ Available</div>
                    </div>
                </div>
            </a>

            <a href="#">
                <div class="card" >

                    <img src="${pageContext.request.contextPath}/images/house.jpeg">
                    <div class="card-content">
                      <h3>Rs.5,00,00,000</h3>
                      <span class="tag">Home</span>
                    <div class="location">Kamalpokhari,Kathmandu</div>
                    <div class="description">🏠 Beautiful home for sale in a scenic and peaceful location!</div>
                    <div class="status">✅ Available</div>
                    </div>
                </div>
            </a>
        </div>
    </div>
</body>
<jsp:include page="footer.jsp" />
</html>