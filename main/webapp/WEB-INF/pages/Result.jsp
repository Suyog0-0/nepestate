<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ViewPropertySP.css">
</head>
<body>
    <div class="result-container">
        <h1>${message}</h1>
        <a href="ViewPropertySPController?propertyId=${param.propertyId}">Back to Property</a>
    </div>
</body>
</html>