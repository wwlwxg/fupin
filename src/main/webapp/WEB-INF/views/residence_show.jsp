<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>户信息列表</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<style type="text/css">
.head{
	background-color:#eee;
	font-weight:bold;
	font-family:黑体,"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu;
}
thead {
	background-color:#eee;
	font-weight:bold;
	font-family:楷体,"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu;
}
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
	<h1 align="center">孝南区户情档案(一户一档)</h1>
<div style="margin-left: auto;margin-right:auto;width: 1342px">
	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center">
	<thead>
		<tr align="left">
			<td colspan="12"><pre><b>乡镇：${bean.xiangzhen };&#9;&#9;&#9;村(社区):${bean.cun };&#9;&#9;&#9;湾组:${bean.zu };&#9;&#9;&#9;建档/更新日期：${bean.build_date }</b></pre></td>
		</tr>
	</thead>
		<tr class="head">
			<td rowspan="${fn:length(bean.list)+1}">家庭成员信息</td>
			<td>户主和与户主关系</td>
			<td>姓名</td>
			<td>性别</td>
			<td>文化程度</td>
			<td>政治面貌</td>
			<td>户籍性质</td>
			<td>健康状况</td>
			<td>身份证号码</td>
			<td>从业及地址</td>
			<td>联系电话</td>
			<td>备注</td>
		</tr>
	
		
		<c:forEach var="data" items="${bean.list }">
			<tr height="50px">
				<td>${data.relation }</td>
				<td>${data.name }</td>
				<td>${data.sex }</td>
				<td>${data.education }</td>
				<td>${data.politics }</td>
				<td>${data.registration_nature }</td>
				<td>${data.health_state }</td>
				<td>${data.id_no }</td>
				<td>${data.address }</td>
				<td>${data.phone }</td>
				<td>${data.remarks }</td>
			</tr>
		</c:forEach>
		
		<tr class="head">
			<td rowspan="2">家庭信息</td>
			<td>家庭类型</td>
			<td colspan="2">门牌</td>
			<td colspan="2">农户星级</td>
			<td colspan="2">家庭住房情况</td>
			<td>承包土地情况</td>
			<td colspan="2">主要收入来源</td>
			<td>户情状态</td>
		</tr>
		<tr>
			<td>${bean.family_type }</td>
			<td colspan="2">${bean.door_plate }</td>
			<td colspan="2">${bean.star_level }</td>
			<td colspan="2">${bean.house_summary }</td>
			<td>${bean.earth_summary }</td>
			<td colspan="2">${bean.income }</td>
			<td>${bean.family_state }</td>
		</tr>
		<tr>
			<td  class="head" colspan="2">各级走访慰问情况</td>
			<td colspan="10">${bean.weiwen }</td>
		</tr>
		<tr>
			<td class="head" colspan="2">有关情况说明</td>
			<td colspan="10">${bean.situation_explanation }</td>
		</tr>
		<tr>
			<td class="head" colspan="12">说明：本档案按照《孝南区户情档案填表说明》填写，用黑色签字笔或钢笔填写。</td>
		</tr>
	
	<tbody>
	</div>
</body>
</html>
