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
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/user_modify_profile.css"/>
</head>
<body>
	
	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>
	
		<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="card shadow">
					<div class="card-body">
						<form:form action="${root }/user/modify_profile_pro" method="post" modelAttribute="modifyUserBean" enctype="multipart/form-data">
							<div class="box-profile-img text-center">
							<button type="button" class="btn-reset-profile-img"><i class="fas fa-trash-alt"></i></button>
							<c:choose>
							<c:when test="${modifyUserBean.user_profile_img != null }">
							<img id="attached-image" src="${bucket }/${modifyUserBean.user_profile_img}">
							</c:when>
							<c:otherwise>
							<img id="attached-image" src="${root }/image/profile.jpg">
							</c:otherwise>
							</c:choose>
							<button type="button" class="btn-reg-profile-img"><i class="fas fa-camera"></i></button>
							</div>
							<div class="form-group" style="display: none;">
								<form:hidden path="user_profile_img"/>
								<form:input type="file" path="upload_profile_img" class="form-control" accept="image/*"/>
							</div>
							<div class="box-user-info text-center">
								<h3>${modifyUserBean.user_name }</h3>
								<span>${modifyUserBean.user_email }</span>
								<hr>
								<div class="input-group">
									<form:textarea path="user_introduction" class="form-control" rows="3" style="resize: none;"/>
								</div>
							</div>
							<hr>
							<div class="form-group">
								<div>
									<a href="${root }/user/mypage" class="btn btn-primary float-left">My Page</a>
									<form:button class="btn btn-primary float-right">적용하기</form:button>
								</div>
							</div>
						</form:form>
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
	<!--  -->
	<script src="${pageContext.request.contextPath }/JS/user_modify_profile.js"></script>
</body>
</html>