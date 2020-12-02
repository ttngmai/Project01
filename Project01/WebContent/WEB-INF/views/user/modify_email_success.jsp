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
	
	<div class="container" style="margin-top:100px">
        <div class="card shadow">
            <div class="card-body">
                <div class="text-center">
                    <img src="${root }/image/welcome.jpg" alt="welcome" width="100%" style="max-width:500px"/>
                    <h1>메일 인증이 완료되었습니다!</h1>
                    <p><b><a href="${root }/user/login">로그인</a></b> 후 게시판 서비스를 이용하실 수 있습니다.</p>
                </div>
            </div>
        </div>
	</div>	
	
	<c:import url="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>