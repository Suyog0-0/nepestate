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
	href="${pageContext.request.contextPath}/css/Login.css" />
<body>
    <div class="container">
        <div class="form-container">
            <div class="form-header">
                <div class="line"></div>
                <h2>LOGIN</h2>
                <div class="line"></div>
            </div>
            <c:if test="${not empty error}">
                <p class="error-message">${error}</p>
            </c:if>
            
            <c:if test="${not empty success}">
                <p class="success-message">${success}</p>
            </c:if>
           
            <form action="${pageContext.request.contextPath}/LoginController" method="post">
             <div class="user-type-dropdown">
                    <label for="userType">Login as <span class="required">*</span></label>
                    <select id="userType" name="userType" required>
                        <option value="Customer">Customer</option>
                        <option value="Administrator">Administrator</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="username">Username <span class="required">*</span></label>
                    <input type="text" id="username" name="username" value required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password <span class="required">*</span></label>
                    <div class="password-field">
                        <input type="password" id="password" name="password" required>
                        <button type="button" class="toggle-password" onclick="togglePasswordVisibility('password')">
                            <img src="eye-icon.png" alt="Show/Hide Password">
                        </button>
                    </div>
                </div>
                
                <div class="remember-forgot">
                    <div class="remember-me">
                        <input type="checkbox" id="remember" name="remember">
                        <label for="remember">Remember me</label>
                    </div>
                    <a href="#" class="forgot-link">Forgot Password?</a>
                </div>
                
                <div class="button-container">
                    <button type="submit" class="submit-btn">Login</button>
                </div>
                
                <div class="form-footer">
                    <p>Already have an account? <a href="Register.html" class="highlight-link">Login here</a></p>
                </div>
            </form>
        </div>
    </div>

    <script>
        function togglePasswordVisibility(inputId) {
            const passwordInput = document.getElementById(inputId);
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
        }
    </script>
</body>
</html>

