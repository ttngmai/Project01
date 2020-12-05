function getContextPath() {
    var hostIndex = location.href.indexOf(location.host) + location.host.length;
    return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex + 1));
}

var root = getContextPath();

function setThumbnail(event) {
	var profile_img = $('#attached-img');
	
	if (event.target.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function(event) {
			profile_img.attr('src', event.target.result);
		}
		
		reader.readAsDataURL(event.target.files[0]);
	} else {
		profile_img.removeAttr('src');
		$('#upload_file').val('');
	}
}

$('#upload_file').change(function(event) {
	setThumbnail(event);
});