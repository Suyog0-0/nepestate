<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Error.css">
</head>
<body>
    <div class="error-container">
        <div class="error-content">
            <div class="error-icon">⚠️</div>
            <h1>Oops! Something went wrong</h1>
            <p>${error}</p>
            <a href="${pageContext.request.contextPath}/HomeController" class="back-button">Back to Home</a>
        </div>
    </div>
</body>
</html>