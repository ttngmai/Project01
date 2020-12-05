function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}

function checkUserIdExist() {
	var root = getContextPath();
	var user_id = $('#user_id').val();
	var idRegexp = /^[a-zA-Z0-9]{4,20}$/;
	
	if (!(idRegexp.test(user_id))) {
		Swal.fire('Warning', '영문대소문자, 숫자 4 ~ 20글자로 구성되야 합니다.', 'warning');
		return;
	}
	
	$.ajax({
		url : root + '/user/checkUserIdExist/' + user_id,
		type : 'get',
		dataType : 'text',
		success : function(result) {
			if (result.trim() == 'true') {
				Swal.fire('Success', '사용할 수 있는 아이디입니다.', 'success');
				$('#userIdExist').val('true');
			} else {
				Swal.fire('Warning', '사용할 수 없는 아이디입니다.', 'warning');
				$('#userIdExist').val('false');
			}
		}
	});
}

function resetUserIdExist() {
	$('#userIdExist').val('false');
}