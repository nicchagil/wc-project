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

function formAjaxByJson(url) {
	$.ajax({
		url : url,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			if (data.code != 'SUCCESS_00001') {
				alert(data.msg);
				return;
			}
			
			alert(data.data);
		}
	});
}