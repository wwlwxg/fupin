<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>统计表</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
window.onload = function() {
	effect_touch_big();
	parse();
}

function effect_touch_big() {
	$("tr").hover(function(){$(this).addClass("effect_touch_big");},function(){$(this).removeClass("effect_touch_big");})
}

function parse() {
	var dang = 0;
	var chu = 0;
	var gao = 0.0;
	var da = 0;
	
	$(".dang").each(function(i){
		dang += parseFloat($(this).text());
	});
	$(".chu").each(function(i){
		chu += parseFloat($(this).text());
	});
	$(".gao").each(function(i){
		gao += parseFloat($(this).text());
	});
	$(".da").each(function(i){
		da += parseFloat($(this).text());
	});

	$(".sum_dang").text(dang);
	$(".sum_chu").text(chu);
	$(".sum_gao").text(gao);
	$(".sum_da").text(da);

}

</script>
<style type="text/css">
tr{
	height: 50px;
}
.grey{
	background-color: #ccc;
}
</style>
</head>
<%
String path=request.getContextPath();
String ipPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String basePath = ipPath +path;
%>
<body>
	<h1 align="center">祝站村全员政治面貌及文化结构统计表</h1>
<div style="margin-left: auto;margin-right:auto;width: 1400px">
	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center" width="1540px">
	<thead>
		<tr align="center" class="tr-head">
			<td>组别</td>
			<td>党员</td>
			<td>初中及以下</td>
			<td>高中、中专、技校</td>
			<td>高职、大专及以上</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="data">
			<tr class="tr-class">
				<td>${data.key }</td>
				<td class="dang">${data.value[3] }</td>
				<td class="chu">${data.value[0] }</td>
				<td class="gao">${data.value[1]}</td>
				<td class="da">${data.value[2]}</td>
			</tr>
		</c:forEach>
	<tr class="grey">
		<td>合计</td>
		<td class="sum_dang"></td>
		<td class="sum_chu"></td>
		<td class="sum_gao"></td>
		<td class="sum_da"></td>
	</tr>
		</tbody>
	</table>
	</div>
</body>
</html>
