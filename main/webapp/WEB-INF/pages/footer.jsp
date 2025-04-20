<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="footer-container">
    <div class="footer-logo">
        <img src="${pageContext.request.contextPath}/images/whitelogo.png" class="footer-logo-img">
    </div>	

    <div class="footer-section">
        <h1>QUICK LINKS</h1>
        <ul>
			<li><a href="${pageContext.request.contextPath}/HomepageController">Home</a></li>            
			<li><a href="${pageContext.request.contextPath}/ProductController">Properties</a></li>
            <li><a href="${pageContext.request.contextPath}/ContactUsController">Contact Us</a></li>
            <li><a href="${pageContext.request.contextPath}AboutUsController">About Us</a></li>
        </ul>
    </div>

    <div class="footer-section">
        <h1>CATEGORIES</h1>
        <ul>
            <li>Flat</li>
            <li>Apartment</li>
            <li>Residencial</li>
            <li>Colony</li>
        </ul>
    </div>

    <div class="footer-section">
        <h1>SCAN QR CODE</h1>
        <img src="${pageContext.request.contextPath}/images/qrcode.png" class="footer-qrcode">
    </div>

    <div class="footer-section">
        <h1>FOLLOW US</h1>
        <div class="footer-socials">
            <img src="${pageContext.request.contextPath}/images/facebook.png" class="footer-social-icon">
            <img src="${pageContext.request.contextPath}/images/instagram.png" class="footer-social-icon">
            <img src="${pageContext.request.contextPath}/images/twitterwhite.png" class="footer-social-icon">
            <img src="${pageContext.request.contextPath}/images/linkedin.png" class="footer-social-icon">
        </div>
    </div>
</div>