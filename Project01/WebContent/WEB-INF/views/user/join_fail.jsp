<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Project01</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>

	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>
	
	<div class="container" style="margin-top: 100px;">
        <div class="card shadow">
            <div class="card-body">
                <div class="text-center">
                    <img src="${root }/image/question.jpg" alt="question" width="100%" style="max-width: 500px;"/>
                    <h1>유효하지 않은 인증키입니다.</h1>
                </div>
            </div>
        </div>
	</div>	
	
	<c:import url="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>