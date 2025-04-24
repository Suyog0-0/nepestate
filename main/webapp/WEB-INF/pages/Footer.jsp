<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
<div class="footer-container">
    <div class="footer-main-content">
        <div class="footer-logo">
            <img src="${pageContext.request.contextPath}/images/whitelogo.png" class="footer-logo-img">
        </div>
        
        <div class="footer-links-container">
            <div class="footer-section">
                <h1>QUICK LINKS</h1>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/HomeController">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/ProductController">Category</a></li>
                    <li><a href="${pageContext.request.contextPath}/AboutUsController">About Us</a></li>
                    <li><a href="${pageContext.request.contextPath}/ContactUsController">Contact Us</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h1>CATEGORIES</h1>
                <ul>
                    <li>Flat</li>
                    <li>Apartment</li>
                    <li>Colony</li>
                </ul>
            </div>
            <div class="footer-section">
                <h1>SCAN QR CODE TO CONTACT US</h1>
                <img src="${pageContext.request.contextPath}/images/qrcode.png" class="footer-qrcode">
            </div>
            <div class="footer-section">
                <h1>SOCIALS</h1>
                <div class="footer-socials">
                    <a href="www.instagram.com"><img src="${pageContext.request.contextPath}/images/instagram.png" class="footer-social-icon"></a>
                    <a href="www.facebook.com"><img src="${pageContext.request.contextPath}/images/facebook.png" class="footer-social-icon"></a>
                    <a href="www.youtube.com"><img src="${pageContext.request.contextPath}/images/youtube.png" class="footer-social-icon"></a>
                    <a href="www.linkedin.com"><img src="${pageContext.request.contextPath}/images/linkedin.png" class="footer-social-icon"></a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="footer-copyright">
        &copy; 2025 NepEstate. All Rights Reserved.
    </div>
</div>