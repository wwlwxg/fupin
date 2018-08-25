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
</script>
</head>
<%
	String path = request.getContextPath();
	String ipPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = ipPath + path;
%>
<body>
	<h1 align="center">麦草湖村贫困户管理页</h1>
	<div style="margin-left: auto; margin-right: auto; width: 800px">
		<form action="<%=basePath%>/addDougangHu.do" method="post"
			id="fund_edit_form">
			<input type="hidden" name="id" value="${bean.id }" />
			<table class="tbDetail" cellspacing="0" cellpadding="2"
				align="center">
				<tr>
					<td>村</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="cun" value="${bean.cun }" /></td>
				</tr>
				<tr>
					<td>户主姓名</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="master" value="${bean.master }" /></td>
				</tr>
				<tr>
					<td>户人口数</td>
					<td width="60%"><input style="width: 100%; height: 100%"
						type="text" name="count" value="${bean.count }" /></td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="phone" value="${bean.phone }" /></td>
				</tr>
				<tr>
					<td>贫困户属性</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="poorAttri" value="${bean.poorAttri }" /></td>
				</tr>
				<tr>
					<td>主要致贫原因</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="poorCause" value="${bean.poorCause }" /></td>
				</tr>
				<tr>
					<td>纳入时贫困的详细原因</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="detailCause" value="${bean.detailCause }" /></td>
				</tr>
				<tr>
					<td>纳入时间</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="inPoorTime" value="${bean.inPoorTime }" 
						onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" /></td>
				</tr>
				<tr>
					<td>脱贫时间</td>
					<td>
						<input style="width: 100%; height: 100%" type="text"
						name="outPoorTime" value="${bean.outPoorTime }" 
						onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" />
					</td>
				</tr>
				<tr>
					<td>帮扶措施</td>
					<td><input style="width: 100%; height: 100%" type="text"
						name="helpStrategy" value="${bean.helpStrategy }" /></td>
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
