<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project01</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<!-- Bootstrap-Select CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.18/css/bootstrap-select.min.css"/>
	<!--  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/board_main.css"/>
</head>
<body>

	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>
	
	<div class="container" style="margin-top: 100px">
		<div class="card shadow">
			<div class="card-body">
				<h4 class="card-title">${board_name }</h4>
				<table id="board_list" class="table table-hover">
					<thead>
						<tr>
							<th class="text-center d-none d-md-table-cell">글번호</th>
							<th class="w-50">제목</th>
							<th class="text-center">작성자</th>
							<th class="text-center d-none d-md-table-cell">작성날짜</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="obj" items="${contentList }">
						<tr>
							<td class="text-center d-none d-md-table-cell">${obj.content_idx }</td>
							<td>
								<div class="text-ellipsis">
									<a href="${root }/board/read?board_idx=${board_idx}&content_idx=${obj.content_idx}&page=${page}&searchType=${searchType}&searchKeyword=${searchKeyword}">${obj.content_subject }</a>
								</div>
							</td>
							<td class="text-center">${obj.content_writer_name }</td>
							<td class="text-center d-none d-md-table-cell">${obj.content_date }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<form action="${root }/board/main" method="get">
					<input type="hidden" name="board_idx" value="${board_idx }">
					<div class="form-group row justify-content-center">
						<div class="input-group col-sm-10">
							<select class="selectpicker" name="searchType" data-width="auto">
								<option value="all" ${searchType == 'all' ? 'selected' : ''}>전체</option>
								<option value="content_subject" ${searchType == 'content_subject' ? 'selected' : ''}>제목</option>
								<option value="content_text" ${searchType == 'content_text' ? 'selected' : ''}>내용</option>
								<option value="content_writer_name" ${searchType == 'content_writer_name' ? 'selected' : ''}>작성자</option>
							</select>
							<input type="text" class="form-control" name="searchKeyword" value="${searchKeyword }" autocomplete="off">
							<div class="input-group-append">
								<button class="btn btn-primary"><i class="fa fa-search"></i></button>
							</div>
						</div>
					</div>
				</form>

				<div>
					<ul class="pagination justify-content-center">
						<c:if test="${pageBean.prevPage > 0 }">
						<li class="page-item">
							<a href="${root }/board/main?board_idx=${board_idx}&page=${pageBean.prevPage}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link">이전</a>
						</li>
						</c:if>
						
						<c:forEach var="idx" begin="${pageBean.min }" end="${pageBean.max }">
						<c:choose>
						<c:when test="${idx == pageBean.currentPage }">
						<li class="page-item active">
							<a href="${root }/board/main?board_idx=${board_idx}&page=${idx}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link">${idx }</a>
						</li>
						</c:when>
						<c:otherwise>
						<li class="page-item">
							<a href="${root }/board/main?board_idx=${board_idx}&page=${idx}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link">${idx }</a>
						</li>
						</c:otherwise>
						</c:choose>
						</c:forEach>
						
						<c:if test="${pageBean.max < pageBean.pageCnt }">
						<li class="page-item">
							<a href="${root }/board/main?board_idx=${board_idx}&page=${pageBean.nextPage}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link">다음</a>
						</li>
						</c:if>
					</ul>
				</div>

				<div>
					<div class="float-left">
						<a href="${root }/board/main?board_idx=${board_idx}" class="btn btn-warning">초기화</a>
					</div>
					<div class="float-right">
						<a href="${root }/board/write?board_idx=${board_idx}&page=${page}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="btn btn-primary">글쓰기</a>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/common/footer.jsp"/>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<!-- Bootstrap-Select JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.18/js/bootstrap-select.min.js"></script>
</body>
</html>