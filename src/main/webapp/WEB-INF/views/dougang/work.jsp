<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>贫困户务工情况</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/sign_js.js"></script>
<script>
window.onload = function() {
	effect_touch_big();
}

function effect_touch_big() {
	$("tr").hover(function(){$(this).addClass("effect_touch_big");},function(){$(this).removeClass("effect_touch_big");})
}
</script>
<style type="text/css">
tr{
	height: 50px;
}
</style>
</head>
<%
String path=request.getContextPath();
String ipPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String basePath = ipPath +path;
%>
<body>
	<h1 align="center">${name }务工情况</h1>
<p><a href="getHu.do?id=${huId }">返回上一级</a></p>
<div style="margin-left: auto;margin-right:auto;width: 1400px">

	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center" width="1540px">
	<thead>
		<tr align="center" class="tr-head">
			<td>序号</td>
			<td>年度</td>
			<td>务工时长(月)</td>
			<td>务工地点</td>
			<td>收入</td>
			<td>备注</td>
			<td>
				<input type="button" onclick="open_dougang_work_window('0',${manId})" value="增加" >
			</td>
		</tr>
	</thead>
	<tbody>
	<c:set var="index" value="0" />
	<c:set var="sum" value="0" />
		<c:forEach items="${list}" var="data">
			<c:set var="index" value="${index+1 }" />
			<c:set var="sum" value="${data.income + sum }" />
			<tr class="tr-class">
				<td>${index }</td>
				<td>${data.year }</td>
				<td>${data.workTime }</td>
				<td>${data.place }</td>
				<td>${data.income}</td>
				<td>${data.remarks}</td>
				<td>
					<a href="#" onclick="open_dougang_work_window('${data.id }',${manId})" >修改</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">合计</td>
			<td>${sum }</td>
			<td colspan="2">&nbsp;</td>
		</tr>
		</tbody>
	</table>
	</div>
</body>
</html>
