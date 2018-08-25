<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>户信息列表</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.head {
	background-color: #eee;
	font-weight: bold;
	font-family: 黑体, "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑,
		STHeiti, MingLiu;
}

thead {
	background-color: #eee;
	font-weight: bold;
	font-family: 楷体, "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑,
		STHeiti, MingLiu;
}

tr {
	height: 40px;
}
</style>
<script type="text/javascript">
if('${flag}') {
	alert("保存成功!")
}
$(function(){

})
</script>
</head>
<%
	String path = request.getContextPath();
	String ipPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = ipPath + path;
%>
<body>
	<h1 align="center">麦草湖村贫困户年度收入管理页</h1>
	<div style="margin-left: auto; margin-right: auto; width: 800px">
		<form action="<%=basePath%>/addDougangIncome.do" method="post"
			id="fund_edit_form">
			<input type="hidden" name="id" value="${bean.id }" />
			<input type="hidden" name="huId" value="${huId }" />
			<table class="tbDetail" cellspacing="0" cellpadding="2"
				align="center">
				<tr>
					<td>年度</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="year" value="${bean.year }" /></td>
				</tr>
				<tr>
					<td>养老金</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="jinOld" value="${bean.jinOld }" /></td>
				</tr>
				<tr>
					<td>低保金</td>
					<td width="60%"><input style="width: 100%; height: 100%"
						type="text" name="jinLow" value="${bean.jinLow }" /></td>
				</tr>
				<tr>
					<td>五保金</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="jinWu" value="${bean.jinWu }" /></td>
				</tr>
				<tr>
					<td>高龄补贴</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="jinGao" value="${bean.jinGao }" /></td>
				</tr>
				<tr>
					<td>地力补助</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="earthPower" value="${bean.earthPower }" /></td>
				</tr>
				<tr>
					<td>土地流转</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="earthTrans" value="${bean.earthTrans }" /></td>
				</tr>
				<tr>
					<td>生产经营收入</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="product" value="${bean.product }" /></td>
				</tr>
				<tr>
					<td>残补</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="canbu" value="${bean.canbu }" /></td>
				</tr>
				<tr>
					<td>计生补贴</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="birthPlan" value="${bean.birthPlan }" /></td>
				</tr>
				<tr>
					<td>备注</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="remarks" value="${bean.remarks }" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="提交" /></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>
