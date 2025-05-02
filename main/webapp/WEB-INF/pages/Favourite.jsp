<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Favourite</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Favourite.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/UserSidebar.css">
</head>
<body>

    <div class="navbarSection">
        <jsp:include page="Navbar.jsp" />    
    </div>
    
    <div class="maindiv">
        <jsp:include page="UserSidebar.jsp" />
   
        <div class="favouriteContainer">
            <div class="favouriteText">My Favourites</div>
            <div class="favouriteUnderline"></div> <!-- Added underline -->

            <div class="firstFavourite">
                <div class="imageaAndDetailsContainer">
                    <img src="${pageContext.request.contextPath}/images/house1.jpeg" class="house1">
                    <div class="priceandlocation">
                        <div class="price">Rs.2,56,000</div>
                        <div class="location">Thamel, Kathmandu</div>
                        <div class="statusContainer">
                            <div class="status">
                                <img src="${pageContext.request.contextPath}/images/greentick.png" class="tickIcon">
                                <h1 class="statusText">Available</h1>
                            </div>
                            <div class="profileButtonContainer">
                                <button class="buyNowButton">Buy Now</button>
                                <button class="bookmarkButton">About</button>
                            </div>
                        </div>
                    </div>
                    <div class="removeIconContainer">
                        <img src="${pageContext.request.contextPath}/images/removeIcon.png" class="removeIcon">
                    </div>
                </div>
            </div>

            <div class="secondFavourite">
                <div class="imageaAndDetailsContainer">
                    <img src="${pageContext.request.contextPath}/images/house1.jpeg" class="house1">
                    <div class="priceandlocation">
                        <div class="price">Rs.2,56,000</div>
                        <div class="location">Thamel, Kathmandu</div>
                        <div class="statusContainer">
                            <div class="status">
                                <img src="${pageContext.request.contextPath}/images/greentick.png" class="tickIcon">
                                <h1 class="statusText">Available</h1>
                            </div>
                            <div class="profileButtonContainer">
                                <button class="buyNowButton">Buy Now</button>
                                <button class="bookmarkButton">About</button>
                            </div>
                        </div>
                    </div>
                    <div class="removeIconContainer">
                        <img src="${pageContext.request.contextPath}/images/removeIcon.png" class="removeIcon">
                    </div>
                </div>
            </div>

            <div class="thirdFavourite">
                <div class="imageaAndDetailsContainer">
                    <img src="${pageContext.request.contextPath}/images/house1.jpeg" class="house1">
                    <div class="priceandlocation">
                        <div class="price">Rs.2,56,000</div>
                        <div class="location">Thamel, Kathmandu</div>
                        <div class="statusContainer">
                            <div class="status">
                                <img src="${pageContext.request.contextPath}/images/greentick.png" class="tickIcon">
                                <h1 class="statusText">Available</h1>
                            </div>
                            <div class="profileButtonContainer">
                                <button class="buyNowButton">Buy Now</button>
                                <button class="bookmarkButton">About</button>
                            </div>
                        </div>
                    </div>
                    <div class="removeIconContainer">
                        <img src="${pageContext.request.contextPath}/images/removeIcon.png" class="removeIcon">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footerSection">
        <jsp:include page="Footer.jsp" />
    </div>
</body>
</html>