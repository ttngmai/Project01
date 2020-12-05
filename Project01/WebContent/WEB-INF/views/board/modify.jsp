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
</head>
<body>
	
	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>

	<div class="container" style="margin-top:100px">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="card shadow">
					<div class="card-body">
						<form:form action="${root }/board/modify_pro" method="post" modelAttribute="modifyContentBean" encType="multipart/form-data">
							<form:hidden path="content_idx"/>
							<form:hidden path="board_idx"/>
							<input type="hidden" name="page" value="${page }">
							<input type="hidden" name="searchType" value="${searchType }">
							<input type="hidden" name="searchKeyword" value="${searchKeyword }">
							<div class="form-group">
								<form:label path="content_subject">제목</form:label>
								<form:input path="content_subject" class="form-control"/>
								<form:errors path="content_subject" style="color:red"/>
							</div>
							<div class="form-group">
								<form:label path="content_text">내용</form:label>
								<form:textarea path="content_text" class="form-control" rows="10" style="resize:none"/>
								<form:errors path="content_text" style="color:red"/>
							</div>
							<div class="form-group">
								<c:if test="${modifyContentBean.content_file != null }">
								<label for="board_file">첨부 이미지</label>
								<div class="text-center" style="margin: 1rem 0;">
									<img id="attached-img" class="col-sm-8" src="${bucket }/${modifyContentBean.content_file}"/>	
								</div>
								</c:if>
								<form:hidden path="content_file"/>
								<form:input type="file" path="upload_file" class="form-control" accept="image/*"/>			
							</div>
							<div class="form-group">
								<div class="float-left">
									<a href="${root }/board/read?board_idx=${board_idx}&content_idx=${content_idx}&page=${page}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="btn btn-danger">취소</a>
								</div>
								<div class="float-right">
									<form:button class="btn btn-primary">수정완료</form:button>
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
	<script src="${pageContext.request.contextPath }/JS/board_modify.js"></script>
</body>
</html>
