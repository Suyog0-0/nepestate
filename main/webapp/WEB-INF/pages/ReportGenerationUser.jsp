<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String userType = (String) request.getAttribute("userType");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Generation</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navbar.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ReportGeneration.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Footer.css">
<style>
    .report-container {
        margin: 20px 0;
        padding: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        background-color: #f9f9f9;
    }
    .report-section {
        margin-bottom: 20px;
    }
    .report-section h3 {
        border-bottom: 1px solid #ccc;
        padding-bottom: 5px;
        margin-bottom: 10px;
    }
    .report-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
    }
    .report-table th, .report-table td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    .report-table th {
        background-color: #f2f2f2;
    }
    .report-summary {
        font-weight: bold;
        margin-top: 10px;
    }
</style>
</head>
<body>
<jsp:include page="Navbar.jsp" />
<div style="display:flex;">

<c:choose>
<c:when test="${userType == 'admin'}">
<jsp:include page="AdminSidebar.jsp"/>
</c:when>
<c:when test="${userType == 'customer'}">
<jsp:include page="UserSidebar.jsp"/>
</c:when>
</c:choose>

<div class="rightbox">
    <div style="margin-left: 7%;">
        <div>
            <h1>Reports</h1>
        </div>
        
        <form method="get" action="${pageContext.request.contextPath}/ReportGenerationController">
            <label for="reportType">Report Type:</label>
            <div>
                <input list="report-generation" id="reportType" name="reportType" class="combobox" value="Property Report">
                <datalist id="report-generation">
                    <option value="Property Report">
                </datalist>
            </div>
            <div>
                <button type="submit" class="generate-button">Generate</button>
            </div>
        </form>
        
        <!-- Display Report Results -->
        <c:if test="${reportGenerated == true}">
            <div class="report-container">
                <h2>Property Report for ${userType == 'admin' ? 'All Properties' : 'Your Properties'}</h2>
                
                <div class="report-section">
                    <h3>Summary</h3>
                    <p>Total Properties: <strong>${reportData.totalProperties}</strong></p>
                    <p>Total Property Value: <strong><fmt:formatNumber value="${reportData.totalPropertyValue}" type="currency" currencySymbol="Rs. "/></strong></p>
                </div>
                
                <div class="report-section">
                    <h3>Properties by Status</h3>
                    <table class="report-table">
                        <tr>
                            <th>Status</th>
                            <th>Count</th>
                        </tr>
                        <c:forEach items="${reportData.statusCounts}" var="entry">
                            <tr>
                                <td>${entry.key}</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="report-section">
                    <h3>Properties by Type</h3>
                    <table class="report-table">
                        <tr>
                            <th>Type</th>
                            <th>Count</th>
                        </tr>
                        <c:forEach items="${reportData.typeCounts}" var="entry">
                            <tr>
                                <td>${entry.key}</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                
                <div class="report-section">
                    <h3>Properties by City</h3>
                    <table class="report-table">
                        <tr>
                            <th>City</th>
                            <th>Count</th>
                        </tr>
                        <c:forEach items="${reportData.cityCounts}" var="entry">
                            <tr>
                                <td>${entry.key}</td>
                                <td>${entry.value}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:if>
    </div>
</div>
</div>

</body>
</html>