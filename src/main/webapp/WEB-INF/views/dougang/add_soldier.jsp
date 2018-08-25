<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>复员退伍情况</title>
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
	<h1 align="center">新增复员退伍情况页</h1>
	<div style="margin-left: auto; margin-right: auto; width: 800px">
		<form action="<%=basePath%>/addSoldier.do" method="post"
			id="fund_edit_form">
			<input type="hidden" name="id" value="${bean.id }" />
			<input type="hidden" name="manId" value="${manId }" />
			<table class="tbDetail" cellspacing="0" cellpadding="2"
				align="center">
				<tr>
					<td>当兵地点</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="place" value="${bean.place }" /></td>
				</tr>
				<tr>
					<td>入伍时间</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="dateJoin" value="${bean.dateJoin }" 
						onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/></td>
				</tr>
				<tr>
					<td>退伍时间</td>
					<td width="60%"><input style="width: 100%; height: 100%"
						type="text" name="dateLeave" value="${bean.dateLeave }" 
						onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})"/></td>
				</tr>
				<tr>
					<td>优抚情况</td>
					<td width="60%"><input style="width: 100%; height: 100%"
						type="text" name="careSituation" value="${bean.careSituation }" />
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
