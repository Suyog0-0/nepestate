<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Login.css" />
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="form-header">
            <div class="line"></div>
            <h2>LOGIN</h2>
            <div class="line"></div>
        </div>
        <form action="${pageContext.request.contextPath}/LoginController" method="post">
            <div class="form-group">
                <label for="username">Username <span class="required">*</span></label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password <span class="required">*</span></label>
                <div class="password-field">
                    <input type="password" id="password" name="password" required>
                    <button type="button" class="toggle-password" onclick="togglePasswordVisibility('password')">
                        <img src="${pageContext.request.contextPath}/images/showhidepassword.svg" alt="Show/Hide Password">
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
                <p>Don't have an account? <a href="Register.html" class="highlight-link">Register here</a></p>
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