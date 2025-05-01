<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/AboutUs.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>
<jsp:include page="Navbar.jsp" />
</div>
    <div class="container">
        <div class="leftbox">
            <h2>About Us</h2>
            <p>
            The Trusted property marketplace NepEstate welcomes you to join its services for real estate market navigation throughout Nepal.NepEstate streamlines property transactions for all users who need to buy, sell or rent properties.
            </p>
            <p style="padding-top:30px ;">
            NepEestate provides a verified listing platform that connects agents and sellers alongside buyers through a single system which delivers expert guidance and current market trends throughout Nepal. NepEstate stands dedicated to help you uncover properties which will become your future home.
            </p>
        </div>
        <div class="image">
            <img src="${pageContext.request.contextPath}/images/figma.jpg" height="480px">
        </div>
    </div>
    <jsp:include page="Footer.jsp" />
</body>
</html>