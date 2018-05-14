<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>户信息列表</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
window.onload = function() {
	effect_touch_big();
}

$(function(){
	$("#zu").val("${zu}");
	$("#name").val("${name}")
})

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
	<h1 align="center">祝站村一户一档全员信息统计表</h1>
<div style="margin-left: auto;margin-right:auto;width: 1400px">
	<!--  查询  -->
	<form action="<%=basePath %>/getResidenceList.do" method="post">
		组: <input type="text" id="zu" name="zu" />
		姓名:<input type="text" id="name" name="name" />
		<input type="submit" value="查询" />
	</form>

	<br />

	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center" width="1540px">
	<thead>
		<tr align="center" class="tr-head">
			<td>序号</td>
			<td>户主</td>
			<td>人数</td>
			<td>组</td>
			<td>家庭类型</td>
			<td>村(社区)</td>
			<td>备注</td>
			<td width="10%"><input type="button" onclick="location='<%=basePath %>/export2Zip.do?zu=${zu }'" value="导出"></td>
			<td>
				<a href="<%=basePath %>/loadResidence.do?id=0" target="_blank">增加</a>
			</td>
		</tr>
	</thead>
	<tbody>
	<c:set var="index" value="0" />
	<c:set var="sum" value="0" />
		<c:forEach items="${list}" var="data">
			<c:set var="index" value="${index+1 }" />
			<c:set var="sum" value="${sum+data.count }" />
			<tr class="tr-class">
				<td>${index }</td>
				<td><a href="<%=basePath %>/getResidence.do?residenceId=${data.residenceId}" target="_blank">${data.master }</a></td>
				<td>${data.count }</td>
				<td>${data.zu }</td>
				<td>${data.familyType }</td>
				<td>${data.cun }</td>
				<td>${data.rr}</td>
				<td><input type="button" value="导出"  onclick="location='exportExcel.do?id=${data.id}'" /></td>
				<td>
					<a href="<%=basePath %>/loadResidence.do?id=${data.residenceId}" target="_blank">修改</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="2">合计</td>
			<td>${sum }</td>
			<td colspan="5">&nbsp;</td>
		</tr>
		</tbody>
	</table>
	</div>
</body>
</html>
