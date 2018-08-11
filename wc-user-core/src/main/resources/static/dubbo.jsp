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

</script>
<body>

<a href="http://127.0.0.1:7001">Dubbo控制台（记得在本地安装启动dubbo-admin）</a>
<hr />

请求消费者（此消费者会请求提供者）：
<button type="button" onclick="formAjaxByJson('/user/dubbo/getByCriteria?id=123&name=nk')">查询请求（参数正确）（此请求属幂等）</button>
<button type="button" onclick="formAjaxByJson('/user/dubbo/getByCriteria')">查询请求（参数错误）</button>
<button type="button" onclick="formAjaxByJson('/user/dubbo/insert')">插入请求（此请求属非幂等）</button>
<button type="button" onclick="formAjaxByJson('/session/springsession/buildSession?buildSession=true')">请求接口，创建Session</button>
<button type="button" onclick="formAjaxByJson('/session/springsession/buildSession?buildSession=false')">请求接口，不创建Session</button>
<hr />

快速链接：<br/>
<a href="./console.html">返回Console主页</a>

</body>
</html>