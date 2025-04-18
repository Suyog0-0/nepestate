<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
 <link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Register.css">
</head>
<body>
    <div class="container">
        <div class="logo">
            <!-- Replace with your logo -->
            <img src="Logo.png" alt="Logo">
        </div>
        
        <div class="register-box">
            <h2>CREATE ACCOUNT</h2>
            
            <form>
                <div class="name-row">
                    <div class="input-group">
                        <label for="firstname">First Name <span class="required">*</span></label>
                        <input type="text" id="firstname" required>
                    </div>
                    
                    <div class="input-group">
                        <label for="lastname">Last Name <span class="required">*</span></label>
                        <input type="text" id="lastname" required>
                    </div>
                </div>
                
                <div class="input-group">
                    <label for="dob">Date of Birth <span class="required">*</span></label>
                    <div class="date-selectors">
                        <select id="day" required>
                            <option value="" disabled selected>Day</option>
                            <!-- Days 1-31 would be added here -->
                        </select>
                        
                        <select id="month" required>
                            <option value="" disabled selected>Month</option>
                            <!-- Months would be added here -->
                        </select>
                        
                        <select id="year" required>
                            <option value="" disabled selected>Year</option>
                            <!-- Years would be added here -->
                        </select>
                    </div>
                </div>
                
                <div class="input-group">
                    <label for="email">Email Address <span class="required">*</span></label>
                    <input type="email" id="email" placeholder="abc@gmail.com" required>
                </div>
                
                <div class="input-group">
                    <label for="username">Username <span class="required">*</span></label>
                    <input type="text" id="username" required>
                </div>
                
                <div class="input-group">
                    <label for="password">Password <span class="required">*</span></label>
                    <div class="password-container">
                        <input type="password" id="password" required>
                        <span class="toggle-password" onclick="togglePassword('password')">
                            <img src="eyeicon.png" alt="Show/Hide Password">
                        </span>
                    </div>
                </div>
                
                <div class="input-group">
                    <label for="repassword">Retype-Password <span class="required">*</span></label>
                    <div class="password-container">
                        <input type="password" id="repassword" required>
                        <span class="toggle-password" onclick="togglePassword('repassword')">
                            <img src="eyeicon.png" alt="Show/Hide Password">
                        </span>
                    </div>
                </div>
                
                <button type="submit" class="register-btn">Register</button>
                
                <div class="already-account">
                    Already have an account? <a href="index.html">Login here</a>
                </div>
            </form>
        </div>
    </div>

    <script>
        function togglePassword(fieldId) {
            const passwordInput = document.getElementById(fieldId);
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
            } else {
                passwordInput.type = 'password';
            }
        }
    </script>
</body>
</html>