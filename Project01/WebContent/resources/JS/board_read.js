function getContextPath() {
    var hostIndex = location.href.indexOf(location.host) + location.host.length;
    return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
}

var root = getContextPath();

$('.btn-delete-content').on('click', function() {
	var board_idx = $(this).data('board_idx');
	var content_idx = $(this).data('content_idx');
	
	Swal.fire({
		title : '게시글 삭제',
		text : '게시글을 삭제 하시겠습니까?',
		icon : 'warning',
		showCancelButton : true,
		confirmButtonText : '삭제',
		cancelButtonText : '취소'
	}).then((result) => {
	// button의 value를 result로 받아서 사용
		if (result.isConfirmed) {
			location.href = root + '/board/delete?board_idx=' + board_idx + '&content_idx=' + content_idx;
		}
	});
});

$('.btn-dislike-content, .btn-like-content').click(function() {
	var like_obj_idx = $(this).data('like_obj_idx');
	var like_flag = $(this).data('like_flag');
	
	$.ajax({
		url : root + '/like/' + like_obj_idx + '/' + like_flag,
		type : 'post',
		success : function(data) {
			if (data.like_flag == 'D') {
				$('.btn-like-content').removeClass('checked');
				$('.btn-dislike-content').addClass('checked');
			}
			else if (data.like_flag == 'L') {
				$('.btn-dislike-content').removeClass('checked');
				$('.btn-like-content').addClass('checked');
			}
			else {
				$('.btn-dislike-content, .btn-like-content').removeClass('checked');
			}
			
			$('.content-like-cnt')[0].innerHTML = data.like_cnt;
			$('.content-dislike-cnt')[0].innerHTML = data.dislike_cnt;
		}
	});
});

