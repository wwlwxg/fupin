<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>户信息列表</title>
<script type="text/javascript" src="js/sign_js.js"></script>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
<form action="<%=basePath %>/addResidence.do" method="post">
	<input type="hidden" name="id" value="${bean.id }" />
	<input type="hidden" name="master" value="${bean.master }" />
	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center">
	<thead>
		<tr align="left">
			<td colspan="13"><pre><b>乡镇：<input width="30px" type="text" name="xiangzhen" value="${bean.xiangzhen }"/>;&#9;&#9;&#9;村(社区):<input type="text" name="cun" value="${bean.cun }"/>;&#9;&#9;&#9;湾组:<input type="text" name="zu" value="${bean.zu }"/>;&#9;&#9;&#9;建档/更新日期：<input type="text" name="build_date" value="${bean.build_date }" onfocus="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" /></b></pre></td>
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
			<td><input type="button" onclick="open_edit_window('<%=basePath %>','0','${bean.id }')" value="新增"></td>
		</tr>
		<c:forEach items="${bean.list }" var="data">
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
				<td><input type="button" onclick="open_edit_window('<%=basePath %>','${data.id }','${bean.id }')" value="修改"></td>
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
			<td colspan="2">户情状态</td>
		</tr>
		<tr>
			<td><input style="width:100%" type="text" name="family_type" value="${bean.family_type }"  /></td>
			<td colspan="2"><input style="width:100%" type="text" name="door_plate" value="${bean.door_plate }" /></td>
			<td colspan="2"><input style="width:100%" type="text" name="star_level" value="${bean.star_level }" /></td>
			<td colspan="2"><input style="width:100%" type="text" name="house_summary" value="${bean.house_summary }" /></td>
			<td><input style="width:100%" type="text" name="earth_summary" value="${bean.earth_summary }" /></td>
			<td colspan="2"><input style="width:100%" type="text" name="income" value="${bean.income }"/></td>
			<td colspan="2"><input style="width:100%" type="text" name="family_state" value="${bean.family_state }" /></td>
		</tr>
		<tr>
			<td  class="head" colspan="2">各级走访慰问情况</td>
			<td colspan="11"><input style="width:100%" type="text" name="weiwen" value="${bean.weiwen }" /></td>
		</tr>
		<tr>
			<td class="head" colspan="2">有关情况说明</td>
			<td colspan="11"><input style="width:100%" type="text" name="situation_explanation" value="${bean.situation_explanation }" /></td>
		</tr>
		<tr>
			<td class="head" colspan="13">说明：本档案按照《孝南区户情档案填表说明》填写，用黑色签字笔或钢笔填写。</td>
		</tr>
		<tr>
			<td align="right" colspan="13"><input type="submit" value="提交"/></td>
		</tr>
	</tbody>
	</table>
	</form>
	</div>
</body>
</html>
