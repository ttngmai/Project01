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
                    <img src="${root }/image/mail.jpg" alt="send_mail" width="100%" style="max-width: 500px;"/>
                    <h1>계정이 생성되었습니다!</h1>
                    <p>${user_email } 로 인증 메일이 전송되었습니다.</p>
                    <p>인증 메일 확인을 하시면 회원가입이 완료됩니다.</p>
                </div>
            </div>
        </div>
	</div>				
	
	<c:import url="/WEB-INF/views/common/footer.jsp"/>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>