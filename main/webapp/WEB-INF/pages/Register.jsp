<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/Register.css" />
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
                <p class="error-message">${error}</p>
            </c:if>
            
            <c:if test="${not empty success}">
                <p class="success-message">${success}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/RegisterController" method="post">
                <div class="name-row">
                    <div class="form-group half">
                        <label for="firstName">First Name <span class="required">*</span></label>
                        <input type="text" id="firstName" name="firstName" value="${firstName}" required>
                    </div>
                    <div class="form-group half">
                        <label for="lastName">Last Name <span class="required">*</span></label>
                        <input type="text" id="lastName" name="lastName" value="${lastName}" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="dob">Date of Birth <span class="required">*</span></label>
                    <div class="date-selects">
                        <select id="day" name="day" required>
                            <option value="">Day</option>
                            <!-- days populated by script -->
                        </select>
                        <select id="month" name="month" required>
                            <option value="">Month</option>
                            <!-- months populated by script -->
                        </select>
                        <select id="year" name="year" required>
                            <option value="">Year</option>
                            <!-- years populated by script -->
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number <span class="required">*</span></label>
                    <input type="tel" id="phone" name="phone" value="${phone}"
                           placeholder="123-456-7890" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email Address <span class="required">*</span></label>
                    <input type="email" id="email" name="email" value="${email}"
                           placeholder="example@mail.com" required>
                </div>
                
                <div class="form-group">
                    <label for="username">Username <span class="required">*</span></label>
                    <input type="text" id="username" name="username" value="${username}" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password <span class="required">*</span></label>
                    <div class="password-field">
                        <input type="password" id="password" name="password" required>
                        <button type="button" class="toggle-password"
                                onclick="togglePasswordVisibility('password')">
                            <img src="eye-icon.png" alt="Show/Hide Password">
                        </button>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="reTypePassword">Retype-Password <span class="required">*</span></label>
                    <div class="password-field">
                        <input type="password" id="reTypePassword" name="reTypePassword" required>
                        <button type="button" class="toggle-password"
                                onclick="togglePasswordVisibility('reTypePassword')">
                            <img src="eye-icon.png" alt="Show/Hide Password">
                        </button>
                    </div>
                </div>
                
                <div class="button-container">
                    <button type="submit" class="submit-btn">Register</button>
                </div>
                
                <div class="form-footer">
                    <p>Already have an account? 
                       <a href="login.html" class="highlight-link">Login here</a>
                    </p>
                </div>
            </form>
        </div>
    </div>

    <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Populate days
        const daySelect = document.getElementById('day');
        for (let i = 1; i <= 31; i++) {
            const opt = document.createElement('option');
            opt.value = i; opt.textContent = i;
            daySelect.appendChild(opt);
        }
        // Populate months
        const monthSelect = document.getElementById('month');
        const months = ['January','February','March','April','May','June',
                        'July','August','September','October','November','December'];
        months.forEach((m,i) => {
            const opt = document.createElement('option');
            opt.value = i+1; opt.textContent = m;
            monthSelect.appendChild(opt);
        });
        // Populate years (100 years back to 18 years ago)
        const yearSelect = document.getElementById('year');
        const currentYear = new Date().getFullYear();
        for (let y = currentYear - 100; y <= currentYear - 18; y++) {
            const opt = document.createElement('option');
            opt.value = y; opt.textContent = y;
            yearSelect.appendChild(opt);
        }
    });

    function togglePasswordVisibility(id) {
        const inp = document.getElementById(id);
        inp.type = inp.type === 'password' ? 'text' : 'password';
    }
    </script>
</body>
</html>