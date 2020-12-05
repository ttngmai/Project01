function getContextPath() {
    var hostIndex = location.href.indexOf(location.host) + location.host.length;
    return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
}

var root = getContextPath();

function setThumbnail(event) {
	var profile_img = $('#attached-image');
	
	if (event.target.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function(event) {
			profile_img.attr('src', event.target.result);
		}
		
		reader.readAsDataURL(event.target.files[0]);
	} else {
		profile_img.attr('src', root + '/image/profile.jpg');
		$('#user_profile_img').val('');
	}
}

$('#upload_profile_img').change(function(event) {
	setThumbnail(event);
});

$('.btn-reset-profile-img').click(function() {
	$('#upload_profile_img').val('').change();
});

$('.btn-reg-profile-img').click(function() {
	$('#upload_profile_img').click();
});