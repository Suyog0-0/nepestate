<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Login.css">
</head>
<body>
    <div class="container">
        <div class="logo">
            <img src="Logo.png" alt="Logo">
        </div>
        
        <div class="login-box">
            <h2>LOGIN</h2>
            
            <form>
                <div class="input-group">
                    <label for="username">Username <span class="required">*</span></label>
                    <input type="text" id="username" required>
                </div>
                
                <div class="input-group">
                    <label for="password">Password <span class="required">*</span></label>
                    <div class="password-container">
                        <input type="password" id="password" required>
                        <span class="toggle-password" onclick="togglePassword()">
                            <img id="eyeIcon" src="eyeicon.png" alt="Show/Hide Password">
                        </span>
                    </div>
                </div>
                
                <div class="remember-forgot">
                    <div class="remember">
                        <input type="checkbox" id="remember">
                        <label for="remember">Remember me</label>
                    </div>
                    <div class="forgot">
                        <a href="#">Forgot Password?</a>
                    </div>
                </div>
                
                <button type="submit" class="login-btn">Login</button>
                
                <div class="already-account">
                    Already have an account? <a href="#">Register</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        function togglePassword() {
            const passwordInput = document.getElementById('password');
            const eyeIcon = document.getElementById('eyeIcon');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                // Change eye icon if needed
                // eyeIcon.src = 'eye-slash.png';
            } else {
                passwordInput.type = 'password';
                // Change eye icon back if needed
                // eyeIcon.src = 'eye.png';
            }
        }
    </script>
</body>
</html>