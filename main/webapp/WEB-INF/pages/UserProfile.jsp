<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UserProfile.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/navbar.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/SideBar.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UpdateProduct.css" />
<body>

<jsp:include page="navbar.jsp"/>

<div style="display:flex"> 
    <div class="sidebar">
        <div class="sidebar-top">
        <img src="Screenshot 2024-07-10 083255.png" alt="Profile Picture" class="profile-pic" />
        <div class="user-info">
            <h3>Durgesh Thapa</h3>
            <p>+977 9841222690</p>
            <p>durgeshthapa@gmail.com</p>
        </div>
        <nav class="nav-menu">
            <a href="#" class="nav-link active">
            <img src="1530975-200.png" class="icon" />
            My Profile
            </a>
            <a href="#" class="nav-link">
            <img src="1413908.png" class="icon" />
            Dashboard
            </a>
            <a href="#" class="nav-link">
            <img src="free-users-icon-267-thumb.png" class="icon" />
            Favorites
            </a>
            <!-- <a href="#" class="nav-link">
            <img src="3093748.png" class="icon" />
            Report
            </a>
            <a href="#" class="nav-link">
            <img src="455705.png" class="icon" />
            Contact
            </a> -->
        </nav>
        </div>
        <button class="logout-btn">
        <img src="https://cdn-icons-png.flaticon.com/512/660/660252.png" class="icon" />
        Logout
        </button>
    </div>
    <div class="mainBox">
            <div class="contentBox">
                <div> 
                    <div style="display: flex;flex-direction:column">
                        <div class="subRow">
                            <div class="subColumn">
                                <label class="form-label">Name</label>
                                <text class="underline-long">Durgesh Thapa</text>
                            </div>
                        </div>
                                <div class="subRow">
                                    <div class="subColumn">
                                        <label class="form-label">Description</label>
                                        <text class="user-description"> I have worked in property dealings for more than 25 years and thererfore, you 
                                            won’t have any problems dealing with me. </text>
                                    </div>
                                </div>
                                <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Phone Number</label>
                                <text class="underline-long">9803421026</text>
                                </div>
                            </div>
                            <div class="subRow">
                                    <div class="subColumn">
                                        <label class="form-label">Email</label>
                                        <text class="underline-long">durgesh.thapa@gmail.com</text>
                                    </div>
                                </div>
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Date of Birth</label>
                                    <text class="underline-long">23/04/1998</text>
                                </div>
                            </div>    
                            <div class="subRow">
                                <div class="subColumn">
                                    <label class="form-label">Location</label>
                                    <text class="underline-long">Kathmandu, Nepal</text>
                                </div>
                            </div>
                            <br>
                            <div class="subRow">
                                <button class="update-button"> UPDATE PROFILE </button>
                            </div>
                    </div>
                    </div> 
           
                <div style="margin-top:50px;">
                    <img src="updateIcon.png" alt="Property Image" />
                </div>
             
        </div>  
    </div>
</div>
<div style="font-family:Arial,sans-serif;font-size:40px;margin-left:30px;margin-top:30px;display:block;">
        Listed Properties (12)
    </div>
<div style="display:flex;gap:60px;margin-top:50px;margin-left:30px;">
    <div class="productbox">
        <img src="ListedProperty.png"> 
        <div style="direction:flex;margin-left:12px;max-width:300px">
            <div class="tag"> Home </div>
            <h3 style="margin-bottom:10px;"> Rs 12,00,000<h3>
            <div style="font-size:12px;color:#666"> 🌄 Beautiful home for sale in a scenic and peaceful location!</div>
            <div style="text-align:center;font-size:10px;color:#666;margin-top:10px"> Kamalpokhari, Kathmandu</div>
        </div> 
    </div>
    <div class="productbox">
        <img src="ListedProperty1.png"> 
        <div style="direction:flex;margin-left:12px;max-width:300px">
            <div class="tag"> Home </div>
            <h3 style="margin-bottom:10px;"> Rs 50,00,000<h3>
            <div style="font-size:12px;color:#666"> ✨ A charming vintage home with timeless architecture and rustic appeal.</div>
            <div style="text-align:center;font-size:10px;color:#666;margin-top:10px"> New Road, Kathmandu</div>
        </div> 
    </div>
    <div class="productbox">
        <img src="ListedProperty2.png"> 
        <div style="direction:flex;margin-left:12px;max-width:300px">
            <div class="tag"> Colony </div>
            <h3 style="margin-bottom:10px;"> Rs 1,75,00,000<h3>
            <div style="font-size:12px;color:#666"> 🏡 A well-planned residential colony with modern homes and serene surroundings</div>
            <div style="text-align:center;font-size:10px;color:#666;margin-top:10px"> Jorpati, Kathmandu</div>
        </div> 
    </div>
    <div class="productbox">
        <img src="ListedProperty4.png"> 
        <div style="direction:flex;margin-left:12px;max-width:300px">
            <div class="tag"> Apartment </div>
            <h3 style="margin-bottom:10px;"> Rs 3,20,00,000<h3>
            <div style="font-size:12px;color:#666"> 🌄; Beautiful home for sale in a scenic and peaceful location!</div>
            <div style="text-align:center;font-size:10px;color:#666;margin-top:10px"> Bhaisepati, Lalitpur </div>
        </div> 
    </div>
    
</div>
</body>
