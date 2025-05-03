<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Generation</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Navbar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/ReportGeneration.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/Footer.css">
</head>
<body>
	<jsp:include page="Navbar.jsp" />
	<div style="display:flex;">
		<jsp:include page="AdminSidebar.jsp" />
		<div class="rightbox">
            <div style="margin-left: 7%;">
                <div>
                    <h1>Reports</h1>
                    <p>Report Type</p>

                </div>
                <label for="Report Type"></label>
                    <div>
                        <input list="report-generaiton" id="reportGeneration" name="reportGeneraiton" class="combobox">
                        <datalist id="report-generaiton">
                            <option value="Payment Report">
                            <option value="Purchase Report">
                            <option value="Sales Report">
                        </datalist>
                    </div>
                <div>
                <button class="generate-button">Generate</button>

                </div>
            </div>
          </div>
	</div>
	
</body>
</html>