var SUCCESS_00001 = "SUCCESS_00001";

$.ajaxSetup({
	async : true, // 是否异步，默认true
	timeout : 10000, // 超时时间，单位毫秒，0表示不设置超时
	dataType : "json", // 服务端返回类型
	success : function(data, textStatus, jqXhr) { // 调用成功的回调方法，参数分别为：data（根据dataType和dataFilter确定而来）、textStatus、jqXhr
	},
	error : function(jrXhr, textStatus, errThrown) { // 系统异常
		alert('系统异常');
	}
});

$(document).ready(function() {
	// 缓存表格头HTML
	goodsTableHeadHtml = $("#goodsTableHead").html();
	seckillTableHeadHtml = $("#seckillTableHead").html();
	seckillRedisTableHeadHtml = $("#seckillRedisTableHead").html();
	
	/* 初始化数据 */
	// 查询商品列表
	queryGoodsList();
	// 查询秒杀数据
	getSeckillList();
	// 查询秒杀Redis数据
	getSeckillRedisDisplayVo();
	
});

// 查询商品列表
function queryGoodsList() {
	$.ajax({
		url : "/ec/goods/getList",
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		success : function(data) {
			/* 错误信息提示与数据处理 */
			if (!data || data.code != SUCCESS_00001) {
				if (data && data.msg) {
					alert(data.msg);
					return;
				}
				alert("系统异常");
				return;
			}
			data = data.data;
			
			$("#goodsTable").html('');
			$("#goodsTable").append(goodsTableHeadHtml);
			
			if (!data) {
				return;
			}
			
			var html = "";
			for (var i = 0; i < data.length; i++) {
				html = html + "<tr>";
				html = html + "<td>" + data[i].id + "</td>";
				html = html + "<td>" + data[i].name + "</td>";
				html = html + "<td>" + data[i].description + "</td>";
				html = html + "</tr>";
			}
			
			$("#goodsTable").append(html);
		}
	});
}

// 查询
function addSeckill() {
	var url = "/ec/seckill/add";
	
	$.ajax({
		url : url,
		type : "POST", // 请求方法
		contentType : "application/json", // 请求类型
		data : JSON.stringify({ // 内置的JSON转换方法
			goodsId : $("#goodsId").val(),
			num : $("#goodsNum").val()
		}),
		dataType : "json", // 服务端返回类型
		success : function(data, textStatus, jqXhr) { // 调用成功的回调方法，参数分别为：data（根据dataType和dataFilter确定而来）、textStatus、jqXhr
			getSeckillList();
		},
		error : function(jrXhr, textStatus, errThrown) { // 系统异常
			alert('系统异常');
		}
	});
}

// 查询秒杀数据
function getSeckillList() {
	var url = "/ec/seckill/getList";
	
	$.ajax({
		url : url,
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		success : function(data) {
			/* 错误信息提示与数据处理 */
			if (!data || data.code != SUCCESS_00001) {
				if (data && data.msg) {
					alert(data.msg);
					return;
				}
				alert("系统异常");
				return;
			}
			data = data.data;
			
			$("#seckillTable").html('');
			$("#seckillTable").append(seckillTableHeadHtml);
			
			if (!data) {
				return;
			}
			
			var html = "";
			for (var i = 0; i < data.length; i++) {
				html = html + "<tr>";
				html = html + "<td>" + data[i].seckillId + "</td>";
				html = html + "<td>" + data[i].goodsName + "</td>";
				html = html + "<td>" + data[i].num + "</td>";
				html = html + "<td>" + data[i].startTime + "</td>";
				html = html + "<td>" + data[i].endTime + "</td>";
				html = html + "<td><button type='button' onclick='deleteSeckill(" + data[i].seckillId + ")''>删除</button></td>";
				html = html + "</tr>";
			}
			
			$("#seckillTable").append(html);
		}
	});
}

// 查询秒杀Redis数据
function getSeckillRedisDisplayVo() {
	var url = "/ec/seckill/getSeckillRedisDisplayVo";
	
	$.ajax({
		url : url,
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		success : function(data) {
			/* 错误信息提示与数据处理 */
			if (!data || data.code != SUCCESS_00001) {
				if (data && data.msg) {
					alert(data.msg);
					return;
				}
				alert("系统异常");
				return;
			}
			data = data.data;
			
			$("#seckillRedisTable").html('');
			$("#seckillRedisTable").append(seckillRedisTableHeadHtml);
			
			if (!data) {
				return;
			}
			
			var html = "";
			for (var i = 0; i < data.length; i++) {
				html = html + "<tr>";
				html = html + "<td>" + data[i].key + "</td>";
				html = html + "<td>" + data[i].value + "</td>";
				html = html + "</tr>";
			}
			
			$("#seckillRedisTable").append(html);
		}
	});
}

// 删除秒杀
function deleteSeckill(id) {
	var url = "/ec/seckill/deleteById?id=" + id;
	
	$.ajax({
		url : url,
		type : "POST",
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		success : function(data) {
			/* 错误信息提示与数据处理 */
			if (!data || data.code != SUCCESS_00001) {
				if (data && data.msg) {
					alert(data.msg);
					return;
				}
				alert("系统异常");
				return;
			}
			data = data.data;
			
			getSeckillList();
		}
	});
}

// 同步秒杀到Redis
function syncToRedis() {
	var url = "/ec/seckill/syncToRedis";
	
	$.ajax({
		url : url,
		type : "POST",
		contentType : "application/x-www-form-urlencoded",
		dataType : "json",
		success : function(data) {
			/* 错误信息提示与数据处理 */
			if (!data || data.code != SUCCESS_00001) {
				
				// 重新同步数据库
				syncToRedis();
				
				if (data && data.msg) {
					alert(data.msg);
					return;
				}
				alert("系统异常");
				return;
			}
			
			data = data.data;
			getSeckillList();
			alert('同步成功');
		}
	});
}

// 抢购
function buy() {
	var url = "/ec/seckill/buy";
	
	$.ajax({
		url : url,
		type : "POST",
		data : JSON.stringify({ // 内置的JSON转换方法
			goodsId : $("#buyGoodsId").val(),
			num : $("#buyGoodsNum").val()
		}),
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			/* 错误信息提示与数据处理 */
			if (!data || data.code != SUCCESS_00001) {
				if (data && data.msg) {
					alert(data.msg);
					return;
				}
				alert("系统异常");
				return;
			}
			data = data.data;
			
			getSeckillList();
		}
	});
}