<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="modal" id="modal-modify-comment" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-lg modal-dialog-centered">
		<div class="modal-content">
			<input type="hidden" name="comment_idx"/>
			
			<div class="modal-header">
				<h4 class="modal-title">댓글 수정</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body">
				<div class="input-group">
					<textarea name="comment_text" class="form-control" rows="5" style="resize: none;"></textarea>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-modify-save" data-dismiss="modal">저장</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			</div>

		</div>
	</div>
</div>

<script>
	var content_idx = '${content_idx}';
	
	function writeComment(textareaComment, comment_parent_idx, comment_text) {
		$.ajax({
			url : '${root}/comment/write',
			type : 'post',
			data : {'content_idx' : content_idx,
					'comment_parent_idx' : comment_parent_idx,
					'comment_text' : comment_text},
			success : function(data) {
				var json_data = JSON.parse(data);
				
				$(textareaComment).val('');
				
				if (comment_parent_idx == 0) {
					getCommentList(json_data.last_page);
					$('.content-comment-cnt').html(json_data.comment_cnt);
				} else {
					getReplyList(comment_parent_idx, json_data.last_page);
					$('.reply_cnt[data-comment_idx="' + comment_parent_idx + '"]').html(json_data.reply_cnt);
				}
			}
		});
	}
	
	function modifyComment(comment_idx, comment_text) {
		$.ajax({
			url : '${root}/comment/modify',
			type : 'post',
			data : {'comment_idx' : comment_idx,
					'comment_text' : comment_text},
			success : function() {
				$.ajax({
					url : '${root}/comment/read_one_text',
					type : 'get',
					data : {'comment_idx' : comment_idx},
					success : function(data) {
						$('.comment_text[data-comment_idx="' + comment_idx + '"]').html(data);
					}
				});
			}
		});
	}
	
	function deleteComment(comment_idx) {
		$.ajax({
			url : '${root}/comment/delete',
			type : 'post',
			data : {'comment_idx' : comment_idx},
			success : function() {
				$('.comment_text[data-comment_idx="' + comment_idx + '"]').html('---삭제된 댓글 입니다---');
				$('.box-comment-btns[data-comment_idx="' + comment_idx + '"]').remove();
			}
		});
	}
	
	function getCommentList(page) {
		$.ajax({
			url : '${root}/comment/read',
			type : 'get',
			data : {'content_idx' : content_idx,
					'comment_parent_idx' : 0,
					'page' : page},
			success : function(data) {
				var json_data = JSON.parse(data);
				
				var str = '';
				
				$.each(json_data.comment_list, function(key, value) {
					str += '<div class="comment" data-comment_idx="' + value.comment_idx + '">';
					str += '<div class="comment_writer_name">';
					str += '<b>' + value.comment_writer_name + '</b>';
					if (('${loginUserBean.user_idx}' == value.comment_writer_idx) && (value.comment_delete_status == 'N')) {
						str += '<div class="float-right box-comment-btns" data-comment_idx="' + value.comment_idx + '">';
						str += '<span class="btn-modify-comment" data-toggle="modal" data-target="#modal-modify-comment" ' +
							   'data-comment_idx="' + value.comment_idx + '"><i class="fas fa-pen"></i></span>';
						str += '<span class="btn-delete-comment" data-comment_idx="' + value.comment_idx + '"><i class="fas fa-trash-alt"></i></span>';
	                   	str += '</div>';
					}
                   	str += '</div>';    
					str += '<div class="comment_date">';
					str += '<span>' + value.comment_date + '</span>';
					str += '</div>';
					str += '<div class="comment_text" data-comment_idx="' + value.comment_idx + '" style="white-space: pre;">' + value.comment_text + '</div>';
					str += '<div class="comment-score">';
					str += '<span class="toggle-reply float-left" data-target="' + value.comment_idx + '">'; 
					str += '<i class="far fa-comment"></i>';
					str += '<span class="reply_cnt" data-comment_idx="' + value.comment_idx + '">' + value.reply_cnt + '</span>';
					str += '</span>';
					
					if ('${loginUserBean.user_idx}' != value.comment_writer_idx) {
						str += '<div class="box-like-btns float-right">';
						if (value.like_flag == 'L') {
							str += '<a href="#" class="btn-like-comment checked" ';
						} else {
							str += '<a href="#" class="btn-like-comment" ';
						}
						str += 'data-content_idx="' + value.content_idx + '" data-comment_idx="' + value.comment_idx + '" data-like_flag="L">';
						str += '<i class="far fa-thumbs-up"></i><span class="comment-like-cnt">' + value.like_cnt + '</span>';
						str += '</a>';
						if (value.like_flag == 'D') {
							str += '<a href="#" class="btn-dislike-comment checked" ';
						} else {
							str += '<a href="#" class="btn-dislike-comment" ';
						}
						str += 'data-content_idx="' + value.content_idx + '" data-comment_idx="' + value.comment_idx + '" data-like_flag="D">';
						str += '<i class="far fa-thumbs-down"></i><span class="comment-dislike-cnt">' + value.dislike_cnt + '</span>';
						str += '</a>';
						str += '</div>';
					}
						
					str += '</div>';
					str += '</div>';
					str += '<div data-group="' + value.comment_idx + '" style="display: none;">';
                    str += '<div class="textarea-comment input-group" data-comment_parent_idx="' + value.comment_idx + '">';
                    str += '<textarea id="comment_text" name="comment_text" class="form-control" rows="3" style="resize: none"></textarea>';
                    str += '<div class="input-group-append">';
                    str += '<button type="button" class="btn btn-primary btn-write-comment">작성</button>';
					str += '</div>';
					str += '</div>';
					str += '<div class="reply-list" data-comment_parent_idx="' + value.comment_idx + '"></div>';
					str += '</div>';
				});
				
				str += '<div class="d-md-block">';
				str += '<ul class="pagination justify-content-center comment-pagination">';
				
				if (json_data.page_info.prevPage > 0) {
					str += '<li class="page-item">';
					str += '<a href="#" class="page-link" data-page="' + json_data.page_info.prevPage + '">이전</a>';
					str += '</li>';
				}
				
				for (var idx = json_data.page_info.min; idx <= json_data.page_info.max; idx++) {
					if (idx == json_data.page_info.currentPage) {
						str += '<li class="page-item active">';
						str += '<a href="#" class="page-link" data-page="' + idx + '">' + idx + '</a>';
						str += '</li>';
					} else {
						str += '<li class="page-item">';
						str += '<a href="#" class="page-link" data-page="' + idx + '">' + idx + '</a>';
						str += '</li>';
					}
				}
				
				if (json_data.page_info.max < json_data.page_info.pageCnt) {
					str += '<li class="page-item">';
					str += '<a href="#" class="page-link" data-page="' + json_data.page_info.nextPage + '">다음</a>';
					str += '</li>';
				}
				
				str += '</ul>';
				str += '</div>';
				
				$('.comment-list').html(str);
			}
		});
	}
	
	function getReplyList(comment_parent_idx, page) {
		$.ajax({
			url : '${root}/comment/read',
			type : 'get',
			data : {'content_idx' : content_idx,
					'comment_parent_idx' : comment_parent_idx,
					'page' : page},
			success : function(data) {
				var json_data = JSON.parse(data);
				
				var str = '';
				
				$.each(json_data.comment_list, function(key, value) {
					str += '<div class="reply" data-comment_idx="' + value.comment_idx + '">';
					str += '<div class="comment_writer_name" data-comment_idx="' + value.comment_idx + '">';
					str += '<b>' + value.comment_writer_name + '</b>';
					if ('${loginUserBean.user_idx}' == value.comment_writer_idx && (value.comment_delete_status == 'N')) {
						str += '<div class="float-right box-comment-btns">';
						str += '<span class="btn-modify-comment" data-toggle="modal" data-target="#modal-modify-comment" ' +
							   'data-comment_idx="' + value.comment_idx + '"><i class="fas fa-pen"></i></span>';
						str += '<span class="btn-delete-comment" data-comment_idx="' + value.comment_idx + '"><i class="fas fa-trash-alt"></i></span>';
	                   	str += '</div>';
					}
                   	str += '</div>';    
					str += '<div class="comment_date">';
					str += '<span>' + value.comment_date + '</span>';
					str += '</div>';
					str += '<div class="comment_text" data-comment_idx="' + value.comment_idx + '" style="white-space: pre;">' + value.comment_text + '</div>';
					str += '<div class="comment-score">';
					
					if ('${loginUserBean.user_idx}' != value.comment_writer_idx) {
						str += '<div class="box-like-btns float-right">';
						if (value.like_flag == 'L') {
							str += '<a href="#" class="btn-like-comment checked" ';
						} else {
							str += '<a href="#" class="btn-like-comment" ';
						}
						str += 'data-content_idx="' + value.content_idx + '" data-comment_idx="' + value.comment_idx + '" data-like_flag="L">';
						str += '<i class="far fa-thumbs-up"></i><span class="comment-like-cnt">' + value.like_cnt + '</span>';
						str += '</a>';
						if (value.like_flag == 'D') {
							str += '<a href="#" class="btn-dislike-comment checked" ';
						} else {
							str += '<a href="#" class="btn-dislike-comment" ';
						}
						str += 'data-content_idx="' + value.content_idx + '" data-comment_idx="' + value.comment_idx + '" data-like_flag="D">';
						str += '<i class="far fa-thumbs-down"></i><span class="comment-dislike-cnt">' + value.dislike_cnt + '</span>';
						str += '</a>';
						str += '</div>';
					} else {
						str += '<div style="height: 24px;"></div>';
					}
						
					str += '</div>';
					str += '</div>';
				});
				
				str += '<div class="d-md-block">';
				str += '<ul class="pagination justify-content-center reply-pagination">';
				
				if (json_data.page_info.prevPage > 0) {
					str += '<li class="page-item">';
					str += '<a href="#" class="page-link" data-comment_parent_idx="' + comment_parent_idx +
					       '" data-page="' + json_data.page_info.prevPage + '">이전</a>';
					str += '</li>';
				}
				
				for (var idx = json_data.page_info.min; idx <= json_data.page_info.max; idx++) {
					if (idx == json_data.page_info.currentPage) {
						str += '<li class="page-item active">';
						str += '<a href="#" class="page-link" data-comment_parent_idx="' + comment_parent_idx +
							   '" data-page="' + idx + '">' + idx + '</a>';
						str += '</li>';
					} else {
						str += '<li class="page-item">';
						str += '<a href="#" class="page-link" data-comment_parent_idx="' + comment_parent_idx +
							   '" data-page="' + idx + '">' + idx + '</a>';
						str += '</li>';
					}
				}
				
				if (json_data.page_info.max < json_data.page_info.pageCnt) {
					str += '<li class="page-item">';
					str += '<a href="#" class="page-link" data-comment_parent_idx="' + comment_parent_idx +
						   '" data-page="' + json_data.page_info.nextPage + '">다음</a>';
					str += '</li>';
				}
				
				str += '</ul>';
				str += '</div>';
				
				$('.reply-list[data-comment_parent_idx="' + comment_parent_idx + '"]').html(str);
			}
		});
	}
	
	$(document).on('click', '.btn-write-comment', function() {
		var textareaComment = $(this).closest('.textarea-comment');
		var comment_parent_idx = $(textareaComment).data('comment_parent_idx');
		var comment_text = $(textareaComment).children('[name=comment_text]').val();
		
		writeComment($(textareaComment).children('[name=comment_text]'), comment_parent_idx, comment_text);
	});
	
	$(document).on('click', '.btn-modify-comment', function() {
		var comment_idx = $(this).data('comment_idx');
		
		$('#modal-modify-comment [name="comment_idx"]').val(comment_idx);
		
		$.ajax({
			url : '${root}/comment/read_one_text',
			type : 'get',
			data : {'comment_idx' : comment_idx},
			success : function(data) {
				$('#modal-modify-comment [name="comment_text"]').val(data);
			}
		});
	});
	
	$(document).on('click', '#modal-modify-comment .btn-modify-save', function() {
		var comment_idx = $('#modal-modify-comment [name="comment_idx"]').val();
		var comment_text = $('#modal-modify-comment [name="comment_text"]').val();
		
		modifyComment(comment_idx, comment_text);
	});
	
	$(document).on('click', '.btn-delete-comment', function() {
		var comment_idx = $(this).data('comment_idx');
		
		Swal.fire({
			title : '댓글 삭제',
			text : '댓글을 삭제 하시겠습니까?',
			icon : 'warning',
			showCancelButton : true,
			confirmButtonText : '삭제',
			cancelButtonText : '취소'
		}).then((result) => {
		// button의 value를 result로 받아서 사용
			if (result.isConfirmed) {
				deleteComment(comment_idx);
			}
		});
	});
	
	$(document).on('click', '.toggle-reply', function() {
		var target = $(this).data('target');
		
		getReplyList(target);
		
		$('div').find('[data-group="' + target + '"]').stop().slideToggle(200);
	});
	
	$(document).on('click', '.btn-like-comment, .btn-dislike-comment', function(event) {
		event.preventDefault();
		
		var target = $(this).closest('.box-like-btns');
		var content_idx = $(this).data('content_idx');
		var comment_idx = $(this).data('comment_idx');
		var like_flag = $(this).data('like_flag');
		
		$.ajax({
			url : '${root}/like/' + content_idx + '/' + comment_idx + '/' + like_flag,
			type : 'post',
			success : function(data) {
				if (data.like_flag == 'D') {
					target.children('.btn-like-comment').removeClass('checked');
					target.children('.btn-dislike-comment').addClass('checked');
				} else if (data.like_flag == 'L') {
					target.children('.btn-dislike-comment').removeClass('checked');
					target.children('.btn-like-comment').addClass('checked');
				} else {
					target.children('.btn-like-comment, .btn-dislike-comment').removeClass('checked');
				}
				
				target.find('.comment-like-cnt').html(data.like_cnt);
				target.find('.comment-dislike-cnt').html(data.dislike_cnt);
			}
		});
	});
	
	$(document).on('click', '.comment-pagination .page-link', function(event) {
		event.preventDefault();
		
		var page = $(this).data('page');
		
		getCommentList(page);
	});
	
	$(document).on('click', '.reply-pagination .page-link', function(event) {
		event.preventDefault();

		var comment_parent_idx = $(this).data('comment_parent_idx');
		var page = $(this).data('page');
		
		getReplyList(comment_parent_idx, page);
	});
	
	$(document).ready(function() {
	    getCommentList(1);
	});
</script>