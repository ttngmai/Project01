<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<c:set var="bucket" value="https://s3.ap-northeast-2.amazonaws.com/project01-upload/upload"/>
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
	<!--  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/user_mypage.css"/>
</head>
<body>
	
	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>

	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="card shadow">
					<div class="card-body">
						<div class="box-profile-img text-center">
						<c:choose>
						<c:when test="${readUserBean.user_profile_img != null }">
						<img src="${bucket }/${readUserBean.user_profile_img}">
						</c:when>
						<c:otherwise>
						<img src="${root }/image/profile.jpg">
						</c:otherwise>
						</c:choose>
						</div>
						<div class="box-user-info text-center">
							<h3>${readUserBean.user_name }</h3>
							<span>${readUserBean.user_email }</span>
							<hr>
							<div style="white-space: pre;">${readUserBean.user_introduction }</div>
						</div>
						<hr>
						<div class="box-modify-btns text-center">
							<a href="${root }/user/modify_profile" class="btn-modify-profile btn btn-primary">프로필 수정</a>
							<a href="${root }/user/modify_password" class="btn-modify-password btn btn-primary">비밀번호 변경</a>
							<a href="${root }/user/modify_email" class="btn-modify-email btn btn-primary">이메일 변경</a>
						</div>			
					</div>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/common/footer.jsp"/>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>