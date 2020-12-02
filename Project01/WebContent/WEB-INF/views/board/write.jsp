<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath }"/> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
						<form:form action="${root }/board/write_pro" method="post" modelAttribute="writeContentBean" enctype="multipart/form-data">
							<form:hidden path="board_idx"/>
							<div class="form-group">
								<form:label path="content_subject">제목</form:label>
								<form:input path="content_subject" class="form-control" autocomplete="off"/>
								<form:errors path="content_subject" style="color:red"/>
							</div>
							<div class="form-group">
								<form:label path="content_text">내용</form:label>
								<form:textarea path="content_text" class="form-control" rows="10" style="resize:none"/>
								<form:errors path="content_text" style="color:red"/>
							</div>
							<div class="text-center">
								<img id="content_file" width="60%">
							</div>
							<div class="form-group">
								<form:label path="upload_file">첨부 이미지</form:label>
								<form:input type="file" path="upload_file" class="form-control" accept="image/*"/>
							</div>
							<div class="form-group">
								<div class="float-left">
									<a href="${root }/board/main?board_idx=${board_idx}&page=${page}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="btn btn-primary">목록보기</a>
								</div>
								<div class="float-right">
									<form:button class="btn btn-primary">작성하기</form:button>
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
	<script src="${pageContext.request.contextPath }/JS/board_write.js"></script>
</body>
</html>