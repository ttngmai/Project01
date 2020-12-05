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
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div class="card shadow">
				<div class="card-body">
					<h4 class="card-title">이메일 변경</h4>
					<form:form action="${root }/user/modify_email_pro" method="post" modelAttribute="modifyUserBean">
						<div class="form-group">
							<form:label path="user_email">이메일</form:label>
							<form:input path="user_email" class="form-control"/>
							<form:errors path="user_email" style="color: red;"/>
						</div>
						<div class="form-group">
							<div>
								<a href="${root }/user/mypage" class="btn btn-primary float-left">My Page</a>
								<form:button class="btn btn-primary float-right">메일 전송</form:button>
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
	
</body>
</html>