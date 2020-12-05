<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<!-- SweetAlert CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css"/>
	<!--  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/user_join.css"/>
</head>
<body>

	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>

	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="card shadow">
					<div class="card-body">
						<form:form action="${root }/user/join_pro" method="post" modelAttribute="joinUserBean">
							<form:hidden path="userIdExist"/>
							<div class="form-group">
								<form:label path="user_name">이름<span class="input-constraint">한글 2 ~ 8글자</span></form:label>
								<form:input path="user_name" class="form-control" maxlength="8" autocomplete="off"/>
								<form:errors path="user_name" style="color: red;"/>
							</div>
							<div class="form-group">
								<form:label path="user_id">아이디<span class="input-constraint">영문대소문자, 숫자 4 ~ 20글자</span></form:label>
								<div class="input-group">
									<form:input path="user_id" class="form-control" maxlength="20" onkeypress="resetUserIdExist()" autocomplete="off"/>
									<div class="input-group-append">
										<button type="button" class="btn btn-primary" onclick="checkUserIdExist()">중복확인</button>
									</div>
								</div>
								<form:errors path="user_id" style="color: red;"/>
							</div>
							<div class="form-group">
								<form:label path="user_pw">비밀번호<span class="input-constraint">영문대소문자, 숫자 4 ~ 12글자</span></form:label>
								<form:password path="user_pw" class="form-control" maxlength="12"/>
								<form:errors path="user_pw" style="color: red;"/>
							</div>
							<div class="form-group">
								<form:label path="user_pw2">비밀번호 확인</form:label>
								<form:password path="user_pw2" class="form-control" maxlength="12"/>
								<form:errors path="user_pw2" style="color: red;"/>
							</div>
							<div class="form-group">
								<form:label path="user_email">이메일</form:label>						
								<form:input path="user_email" class="form-control" autocomplete="off"/>
								<form:errors path="user_email" style="color: red;"/>
							</div>
							<div class="form-group">
								<div class="text-right">
									<form:button class="btn btn-primary">회원가입</form:button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/common/footer.jsp"/>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<!-- SweetAlert JS -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
	<!--  -->
	<script src="${pageContext.request.contextPath }/JS/user_join.js"></script>
</body>
</html>








