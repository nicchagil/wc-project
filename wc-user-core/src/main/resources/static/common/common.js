function formAjax(url) {
	$.ajax({
		url : url,
		contentType : "application/x-www-form-urlencoded",
		success : function(data) {
			if (data.code != 'SUCCESS_00001') {
				alert(data.msg);
				return;
			}
			
			alert(data.data);
		}
	});
}