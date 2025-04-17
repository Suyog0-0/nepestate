<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>navbar</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/navbar.css">
	</head>
<body>
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

    <div class="maindiv">
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
        <div class="leftSection">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/images/logo.png" class="logoImage">
            </div>
        </div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
        <div class="centerSection">
            <div class="searchContainer">
                <img src="${pageContext.request.contextPath}/images/searchIcon.png" class="searchIcon">
                <input type="text" class="searchbar" placeholder="What are you looking for?">
            </div>
        </div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
        <div class="rightSection">
            <ul class="navItems">
                <li><a href="#">Home</a></li>
                <li><a href="#">Properties</a></li>
                <li><a href="#">Contact Us</a></li>
                <li><a href="#">About Us </a></li>
                <li><a href="#">|</a></li>
                <li>
                    <div class="registerContainer">
                        <button class="registerButton">Register/SignUp</button>
                        <img src="${pageContext.request.contextPath}/images/register.png" class="registerIcon">
                    </div>
                </li>
            </ul>
        </div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
    </div>
<!-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

</body>
</html>