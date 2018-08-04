<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>异常处理</title>
	<%@ include file="/common/header.jsp" %>
</head>
<script type="text/javascript">

	$(document).ready(function() {
		// 空
	});
	
	function businessException() {
		$.ajax({
			url : "/exception/exceptionMock?exceptionClassName=BusinessException",
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
	
	function illegalArgumentException() {
		$.ajax({
			url : "/exception/exceptionMock?exceptionClassName=IllegalArgumentException",
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
	
	function noException() {
		$.ajax({
			url : "/exception/exceptionMock?exceptionClassName=NoException",
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

</script>
<body>

<button type="button" onclick="businessException()">模拟业务异常</button><br/>
<button type="button" onclick="illegalArgumentException()">模拟运行时异常</button><br/>
<button type="button" onclick="formAjax('/exception/exceptionMock?exceptionClassName=NoException')">模拟无异常</button><br/>

<%@ include file="/common/footer.jsp" %>

</body>
</html>