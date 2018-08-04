<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>Dubbo</title>
	<%@ include file="/common/header.jsp" %>
</head>
<script type="text/javascript">

	$(document).ready(function() {
		// 空
	});

	function questConsumerParameterCorrect() {
		$.ajax({
			url : "/user/dubbo/getByCriteria?id=123&name=nk",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			success : function(data) {
				alert(data.code);
			}
		});
	}

	function questConsumerParameterError() {
		$.ajax({
			url : "/user/dubbo/getByCriteria",
			contentType : "application/x-www-form-urlencoded",
			dataType : "json",
			success : function(data) {
				alert(data.code);
			}
		});
	}
	
</script>
<body>

<a href="http://127.0.0.1:7001">Dubbo控制台（记得在本地安装启动dubbo-admin）</a>
<hr />

请求消费者（此消费者会请求提供者）：
<button type="button" onclick="questConsumerParameterCorrect()">请求（参数正确）</button>
<button type="button" onclick="questConsumerParameterError()">请求（参数错误）</button>
<hr />

快速链接：<br/>
<a href="./console.html">返回Console主页</a>

</body>
</html>