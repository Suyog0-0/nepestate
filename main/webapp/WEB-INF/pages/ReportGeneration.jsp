<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report Generation</title>
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/navbar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/adminSidebar.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/ReportGeneration.css">
<link rel="stylesheet" type="text/css" 
href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div style="display:flex;">
		<jsp:include page="adminsidebar.jsp" />
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
                            <option value="a">
                            <option value="b">
                            <option value="c">
                        </datalist>
                    </div>
                <div>

                </div>
            </div>
          </div>
	</div>
	
</body>
</html>