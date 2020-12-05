<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Project01</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
	<!-- Font Awesome CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<!--  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/main.css"/>
</head>
<body>

	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>

	<div class="container" style="margin-top: 100px;">
		<div class="row">
		<c:forEach var="sub_list" items="${contentList }" varStatus="idx">
			<div class="col-lg-6" style="margin-top: 20px;">
				<div class="card shadow">
					<div class="card-body">
						<h4 class="card-title">${boardList[idx.index].board_name }</h4>
						<table id="board_list" class="table table-hover">
							<thead>
								<tr>
									<th class="w-50">제목</th>
									<th class="text-center w-25">작성자</th>
									<th class="text-center w-25 d-none d-xl-table-cell">작성날짜</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="obj" items="${sub_list }">
								<tr>
									<td>
										<div class="text-ellipsis">
											<a href="${root }/board/read?board_idx=${boardList[idx.index].board_idx }&content_idx=${obj.content_idx }">${obj.content_subject }</a>
										</div>
									</td>
									<td class="text-center">${obj.content_writer_name }</td>
									<td class="text-center d-none d-xl-table-cell">${obj.content_date }</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						
						<div class="text-right">
							<a href="${root }/board/main?board_idx=${boardList[idx.index].board_idx }" class="btn btn-primary">더보기</a>
						</div>
					</div>
				</div>
			</div>		
		</c:forEach>
		</div>
	</div>

    <c:import url="/WEB-INF/views/common/footer.jsp"/>
    
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>