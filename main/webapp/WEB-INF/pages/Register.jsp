<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Register.css" />


</head>
<body>
<div class="container">
        <div class="form-container">
            <div class="form-header">
                <div class="line"></div>
                <h2>CREATE ACCOUNT</h2>
                <div class="line"></div>
            </div>	
	
		<c:if test="${not empty error}">
		    <div class="error-message">
		        ${error}
		    </div>
		</c:if>
				
		<c:if test="${not empty success}">
		    <div class="success-message">
		        ${success}
		    </div>
		</c:if>
		            <form action="${pageContext.request.contextPath}/RegisterController" method='post'>
                <div class="name-row">
                    <div class="form-group half">
                        <label for="firstName">First Name<span class="required">*</span></label>
                        <input type="text" id="firstName" name="firstName" value="${firstName}" placeholder="e.g.Anthony" required>
                    </div>
                    <div class="form-group half">
                        <label for="lastName">Last Name <span class="required">*</span></label>
                        <input type="text" id="lastName" name="lastName" value="${lastName}" placeholder="e.g.Adhikari" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="dob">Date of Birth <span class="required">*</span></label>
                    <div class="date-selects">
                        <select id="day" name="day" required>
                            <option value="">Day</option>
                            <!-- Script will populate days -->
                        </select>
                        <select id="month" name="month" required>
                            <option value="">Month</option>
                            <!-- Script will populate months -->
                        </select>
                        <select id="year" name="year" required>
                            <option value="">Year</option>
                            <!-- Script will populate years -->
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email">Email Address <span class="required">*</span></label>
                    <input type="email" id="email" name="email" value="${email}" placeholder="e.g.example@mail.com" required>
                </div>
                
                <div class="form-group">
    				<label for="phone">Phone Number <span class="required">*</span></label>
    				<input type="text" id="phone" name="phone" value="${phone}" placeholder="e.g.+977 9800000000" required>
				</div>
                
                <div class="form-group">
                    <label for="username">Username <span class="required">*</span></label>
                    <input type="text" id="username" name="username" value="${username}" placeholder="e.g.Anthony Adhikari" required>
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
                
                <div class="form-group">
                    <label for="confirmPassword">Retype-Password <span class="required">*</span></label>
                    <div class="password-field">
                        <input type="password" id="reTypePassword" name="reTypePassword" required>
                        <button type="button" class="toggle-password" onclick="togglePasswordVisibility('reTypePassword')">
							<img src="${pageContext.request.contextPath}/images/showhidepassword.svg" alt="Show/Hide Password">
                        </button>
                    </div>
                </div>
                
                <div class="button-container">
                    <button type="submit" class="submit-btn">Register</button>
                </div>
                
                <div class="form-footer">
                    <p>Already have an account? <a href="${pageContext.request.contextPath}/LoginController" class="highlight-link">Login here</a></p>
                </div>
            </form>
        </div>
    </div>

    <script>
        // Populate date dropdowns
        document.addEventListener('DOMContentLoaded', function() {
            // Days
            const daySelect = document.getElementById('day');
            for (let i = 1; i <= 31; i++) {
                const option = document.createElement('option');
                option.value = i;
                option.textContent = i;
                daySelect.appendChild(option);
            }
            
            // Months
            const monthSelect = document.getElementById('month');
            const months = ['January', 'February', 'March', 'April', 'May', 'June', 
                           'July', 'August', 'September', 'October', 'November', 'December'];
            months.forEach((month, index) => {
                const option = document.createElement('option');
                option.value = index + 1;
                option.textContent = month;
                monthSelect.appendChild(option);
            });
            
            // Years - assuming registration for people at least 18 years old
            const yearSelect = document.getElementById('year');
            const currentYear = new Date().getFullYear();
            for (let i = currentYear - 100; i <= currentYear - 18; i++) {
                const option = document.createElement('option');
                option.value = i;
                option.textContent = i;
                yearSelect.appendChild(option);
            }
        });
        
        function togglePasswordVisibility(inputId) {
            const passwordInput = document.getElementById(inputId);
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
        }
    </script>
</body>
</html>