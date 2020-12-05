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
</head>
<body>

	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>

	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="card shadow">
					<div class="card-body">
						<c:if test="${fail == true }">
						<div class="alert alert-danger">
							<h3>로그인 실패</h3>
							<p>아이디 비밀번호를 확인해주세요.</p>
						</div>
						</c:if>
						<form:form action="${root }/user/login_pro" method="post" modelAttribute="tempLoginUserBean">
							<div class="form-group">
								<form:label path="user_id">아이디</form:label>
								<form:input path="user_id" class="form-control" maxlength="20" autocomplete="off"/>
								<form:errors path="user_id" style="color: red;"/>
							</div>
							<div class="form-group">
								<form:label path="user_pw">비밀번호</form:label>
								<form:password path="user_pw" class="form-control" maxlength="12"/>
								<form:errors path="user_pw" style="color: red;"/>
							</div>
							<div class="float-left">
								<a href="${root }/user/join" class="btn btn-warning">회원가입</a>
							</div>
							<div class="float-right">
								<form:button class="btn btn-primary">로그인</form:button>
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
</body>
</html>