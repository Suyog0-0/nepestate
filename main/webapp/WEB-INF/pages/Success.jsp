<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Success.css">
</head>
<body>
    <div class="success-container">
        <div class="success-content">
            <div class="success-icon">✔️</div>
            <h1>Operation Successful!</h1>
            <p>${sessionScope.success}</p>
            <a href="${pageContext.request.contextPath}/HomeController" class="back-button">Back to Home</a>
        </div>
    </div>
</body>
</html>