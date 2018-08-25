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
	$("#study").find("option[value='${bean.study}']").prop("selected", 'selected');
	$("#work").find("option[value='${bean.work}']").prop("selected", 'selected');
	$("#sick").find("option[value='${bean.sick}']").prop("selected", 'selected');
	$("#emptyNestOld").find("option[value='${bean.emptyNestOld}']").prop("selected", 'selected');
	$("#stayChild").find("option[value='${bean.stayChild}']").prop("selected", 'selected');
	$("#soldier").find("option[value='${bean.soldier}']").prop("selected", 'selected');

})
</script>
</head>
<%
	String path = request.getContextPath();
	String ipPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = ipPath + path;
%>
<body>
	<h1 align="center">麦草湖村贫困人口管理页</h1>
	<div style="margin-left: auto; margin-right: auto; width: 800px">
		<form action="<%=basePath%>/addDougangMan.do" method="post"
			id="fund_edit_form">
			<input type="hidden" name="id" value="${bean.id }" />
			<input type="hidden" name="huId" value="${huId }" />
			<table class="tbDetail" cellspacing="0" cellpadding="2"
				align="center">
				<tr>
					<td>姓名</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="name" value="${bean.name }" /></td>
				</tr>
				<tr>
					<td>性别</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="sex" value="${bean.sex }" /></td>
				</tr>
				<tr>
					<td>证件类型</td>
					<td width="60%"><input style="width: 100%; height: 100%"
						type="text" name="cerType" value="${bean.cerType }" /></td>
				</tr>
				<tr>
					<td>证件号码</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="cerNum" value="${bean.cerNum }" /></td>
				</tr>
				<tr>
					<td>与户主关系</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="relation" value="${bean.relation }" /></td>
				</tr>
				<tr>
					<td>健康状况</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="health" value="${bean.health }" /></td>
				</tr>
				<tr>
					<td>是否学生</td>
					<td>
						<select id="study" name="study">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>是否务工</td>
					<td>
						<select id="work" name="work">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>是否住院</td>
					<td>
						<select id="sick" name="sick">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>是否空巢老人</td>
					<td>
						<select id="emptyNestOld" name="emptyNestOld">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>是否留守儿童</td>
					<td>
						<select id="stayChild" name="stayChild">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>是否复员退伍军人</td>
					<td>
						<select id="soldier" name="soldier">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
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
