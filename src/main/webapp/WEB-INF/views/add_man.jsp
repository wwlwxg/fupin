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
	height: 40px;
}

</style>
</head>
<%
String path=request.getContextPath();
String ipPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String basePath = ipPath +path;
%>
<body>
	<h1 align="center">孝南区人口管理页</h1>
<div style="margin-left: auto;margin-right:auto;width: 800px">
<form action="<%=basePath %>/addMan.do" method="post" id ="fund_edit_form">
	<input type="hidden" name="id" value="${bean.id }" />
	<input type="hidden" name="residence_id" value="${bean.residence_id }" />
	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center">
		<tr class="head">
			<td>户主和与户主关系</td>
			<td><input style="width:100%;height:100%" type="text" name="relation" value="${bean.relation }" /></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td width="60%"><input style="width:100%;height:100%" type="text" name="name" value="${bean.name }" /></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><input style="width:100%;height:100%" type="text" name="sex" value="${bean.sex }" /></td>
		</tr>
		<tr>
			<td>文化程度</td>
			<td><input style="width:100%;height:100%" type="text" name="education" value="${bean.education }" /></td>
		</tr>
		<tr>
			<td>政治面貌</td>
			<td><input style="width:100%;height:100%" type="text" name="politics" value="${bean.politics }" /></td>
		</tr>
		<tr>
			<td>户籍性质</td>
			<td><input style="width:100%;height:100%" type="text" name="registration_nature" value="${bean.registration_nature }" /></td>
		</tr>
		<tr>
			<td>健康状况</td>
			<td><input style="width:100%;height:100%" type="text" name="health_state" value="${bean.health_state }" /></td>
		</tr>
		<tr>
			<td>身份证号码</td>
			<td><input style="width:100%;height:100%" type="text" name="id_no" value="${bean.id_no }" /></td>
		</tr>
		<tr>
			<td>从业及地址</td>
			<td><input style="width:100%;height:100%" type="text" name="address" value="${bean.address }" /></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input style="width:100%;height:100%" type="text" name="phone" value="${bean.phone }" /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input style="width:100%;height:100%" type="text" name="remarks" value="${bean.remarks }" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="提交"/></td>
		</tr>
	</table>
	
	</form>
	</div>
</body>
</html>
