<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
	
    <div class="maindiv">

        <div class="footerContainer">
            <div class="logo">

                <img src="${pageContext.request.contextPath}/images/whitelogo.png" class="whitelogoIcon">
            </div>

             <div class="quicklinksContainer">
                <h1>QUICK LINKS</h1>
                <ul>

                    <li>Home</li>
                    <li>Category</li>
                    <li>About Us</li>
                    <li class="contactusTextFooter">Contact Us</li>
                </ul>
            </div>

            <div class="categoriesContainer">
                <h1>CATEGORIES</h1>
                <ul>
                    <li>Flat</li>
                    <li>Apartment</li>
                    <li>Residencial</li>
                    <li class="colonyTextFooter">Colony</li>
                </ul>

            </div>

            <div class="qrcodeContainer">
                <h1>SCAN QR CODE</h1>

                <img src="${pageContext.request.contextPath}/images/qrcode.png" class="qrcodeIcon">



            </div>

            <div class="socialsContainer">
                <h1>FOLLOW US</h1>
                <div class="socialsimagesContainer">

                    <img src="${pageContext.request.contextPath}/images/facebook.png" class="facebookIcon">
                    <img src="${pageContext.request.contextPath}/images/instagram.png" class="instagramIcon">
                    <img src="${pageContext.request.contextPath}/images/twitterwhite.png" class="twitterIcon">
                    <img src="${pageContext.request.contextPath}/images/linkedin.png" class="linkedinIcon">

                </div>

            </div>

        </div>


    </div>
</body>
</html>