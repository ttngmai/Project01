<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- SweetAlert CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css">
	<!--  -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/board_read.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/CSS/board_comment.css"/>
</head>
<body>
	
	<c:import url="/WEB-INF/views/common/top_nav.jsp"/>

	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="card shadow">
					<div class="card-body">
						<h3>${readContentBean.content_subject }</h3>
						<hr>
                        <div class="box-content-info">
                        	<div class="float-left">
	                       		<a href="#" style="width: 48px; height: 48px;">
	                       			<c:choose>
                        			<c:when test="${readContentBean.content_writer_profile_img != null }">
	                       			<img src="${root }/upload/${readContentBean.content_writer_profile_img}" width="48px" height="48px" style="border-radius: 50%;">
									</c:when>
									<c:otherwise>
									<img src="${root }/image/profile.jpg" width="48px" height="48px" style="border-radius: 50%;">
									</c:otherwise>
									</c:choose>
	                       		</a>
                        	</div>
                            <div class="float-left" style="margin-left: 0.5em;">
                                <div><b>${readContentBean.content_writer_name }</b></div>
                                <div><span class="content_date">${readContentBean.content_date }</span></div>                       
                            </div>
                            <div class="box-content-info2">
                            	<span>
                            		<i class="far fa-comment"></i><span class="content-comment-cnt">${readContentBean.comment_cnt }</span>
                            	</span>
                                <span>
                                	<i class="far fa-thumbs-up"></i><span class="content-like-cnt">${readContentBean.like_cnt }</span>
                                </span>
                                <span>
                                	<i class="far fa-thumbs-down"></i><span class="content-dislike-cnt">${readContentBean.dislike_cnt }</span>
                                </span>
                            </div>
                        </div>
                        <hr>
                        <c:if test="${loginUserBean.user_idx == readContentBean.content_writer_idx }">
                        <div class="float-right" style="margin-bottom: 1rem;">   
							<a href="${root }/board/modify?board_idx=${board_idx}&content_idx=${content_idx}&page=${page}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="btn-modify-content"><i class="fas fa-pen"></i></a>
							<a href="#" class="btn-delete-content" data-board_idx="${board_idx }" data-content_idx="${content_idx }"><i class="fas fa-trash-alt"></i></a>
                        </div>
						</c:if>
						<textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize: none;" disabled="disabled">${readContentBean.content_text }</textarea>
						<c:if test="${readContentBean.content_file != null }">
						<div class="text-center">
							<img src="${root }/upload/${readContentBean.content_file}" width="60%"/>
						</div>
						</c:if>
						<c:if test="${loginUserBean.user_idx != readContentBean.content_writer_idx }">
					    <div class="text-center" style="margin-top: 1rem;">
					    	<c:choose>
	                        <c:when test="${readContentBean.like_flag.equals('L') }">
	                        <button type="button" class="btn-like-content btn btn-outline-dark checked"
	                        		data-like_obj_idx="${content_idx }" data-user_idx="${loginUserBean.user_idx }" data-like_flag="L">
	                        		<i class="far fa-thumbs-up"></i>추천
	                       	</button>
	                       	</c:when>
	                       	<c:otherwise>
	                       	<button type="button" class="btn-like-content btn btn-outline-dark"
	                        		data-like_obj_idx="${content_idx }" data-user_idx="${loginUserBean.user_idx }" data-like_flag="L">
	                        		<i class="far fa-thumbs-up"></i>추천
	                       	</button>
	                       	</c:otherwise>
	                       	</c:choose>
					    	<c:choose>
	                        <c:when test="${readContentBean.like_flag.equals('D') }">
	                        <button type="button" class="btn-dislike-content btn btn-outline-dark checked"
	                        		data-like_obj_idx="${content_idx }" data-user_idx="${loginUserBean.user_idx }" data-like_flag="D">
	                        		<i class="far fa-thumbs-down"></i>비추천
	                        </button>
	                        </c:when>
	                        <c:otherwise>
                       	    <button type="button" class="btn-dislike-content btn btn-outline-dark"
	                       		data-like_obj_idx="${content_idx }" data-user_idx="${loginUserBean.user_idx }" data-like_flag="D">
                       			<i class="far fa-thumbs-down"></i>비추천
                       		</button>
	                        </c:otherwise>
	                        </c:choose>
                        </div>
                        </c:if>
						<div class="text-right" style="margin-top: 1rem;">
							<a href="${root }/board/main?board_idx=${board_idx}&page=${page}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="btn btn-primary">목록보기</a>
						</div>
						<hr>
                        <div class="textarea-comment input-group" data-comment_parent_idx="0">
                            <textarea id="comment_text" name="comment_text" class="form-control" rows="3" style="resize: none;"></textarea>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary btn-write-comment">작성</button>
                            </div>
                        </div>
						<hr>
						<div class="comment-list"></div>
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
	<!-- SweetAlert JS -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
	<!--  -->
	<script src="${pageContext.request.contextPath }/JS/board_read.js"></script>
	
	<%@ include file="comment.jsp" %>
</body>
</html>