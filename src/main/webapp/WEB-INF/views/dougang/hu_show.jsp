<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>户信息列表</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/sign_js.js"></script>
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
	height: 50px;
}
</style>
</head>
<%
	String path = request.getContextPath();
	String ipPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = ipPath + path;
%>
<body>
	<h1 align="center">${bean.cun }户情档案(一户一档)</h1>
<p><a href="huList.do?cun=${bean.cun }">返回上一级</a></p>
	<div style="margin-left: auto; margin-right: auto; width: 1342px">
		<table class="tbDetail" cellspacing="0" cellpadding="2" align="center">
			<thead>
				<tr align="left">
					<td>户主姓名</td>
					<td>${bean.master }</td>
					<td>户人口数</td>
					<td>${bean.count }</td>
					<td>联系电话</td>
					<td>${bean.phone }</td>
					<td>贫困户属性</td>
					<td>${bean.poorAttri }</td>
					<td>主要致贫原因</td>
					<td>${bean.poorCause }</td>
					<td>纳入时致贫的详细原因</td>
					<td>${bean.detailCause }</td>
					<td>纳入时间</td>
					<td>${bean.inPoorTime }</td>
					<td>脱贫时间</td>
					<td colspan="3">${bean.outPoorTime }</td>
				</tr>
				<tr align="left">
					<td>帮扶措施</td>
					<td colspan="17">${bean.helpStrategy }</td>
				</tr>
			</thead>
			<tr class="head">
				<td colspan="18">家庭成员信息</td>
			</tr>
			<tr>
				<td>序号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>出生日期</td>
				<td>年龄</td>
				<td>证件类型</td>
				<td colspan="2">证件号码</td>
				<td>与户主关系</td>
				<td>健康状况</td>
				<td>是否读书</td>
				<td>是否务工</td>
				<td>是否住院</td>
				<td>是否空巢老人</td>
				<td>是否留守儿童</td>
				<td>是否复员退伍军人</td>
				<td>备注</td>
				<td><input type="button" onclick="open_dougang_man_window('0','${bean.id}')" value="增加" ></td>
			</tr>

			<c:set var="index" value="0" />
			<c:forEach var="data" items="${list }">
				<c:set var="index" value="${index+1 }" />
				<tr height="50px">
					<td>${index }</td>
					<td>${data.name }</td>
					<td>${data.sex }</td>
					<td><fmt:formatDate value="${data.birth }" pattern="YYYY-MM-dd"/></td>
					<td>${data.age }</td>
					<td>${data.cerType }</td>
					<td colspan="2">${data.cerNum }</td>
					<td>${data.relation }</td>
					<td>${data.health }</td> 
					<td>
						<c:if test="${data.study}"><a href="studyList.do?manId=${data.id}">是</a></c:if>
						<c:if test="${!data.study}">否</c:if>
					</td>
					<td>
						<c:if test="${data.work}"><a href="workList.do?manId=${data.id}">是</a></c:if>
						<c:if test="${!data.work}">否</c:if>
					</td>
					<td>
						<c:if test="${data.sick}"><a href="sickList.do?manId=${data.id}">是</a></c:if>
						<c:if test="${!data.sick}">否</c:if>
					</td>
					<td>${data.emptyNestOld?'是':'否' }</td>
					<td>${data.stayChild?'是':'否' }</td>
					<td>
						<c:if test="${data.soldier}"><a href="soldierList.do?manId=${data.id}">是</a></c:if>
						<c:if test="${!data.soldier}">否</c:if>
					</td>
					<td>${data.remarks }</td>
					<td><a href="#" onclick="open_dougang_man_window('${data.id}','${data.huId }')" >修改</a></td>
				</tr>
			</c:forEach>

			<c:set var="index" value="0" />
			<tr class="head">
				<td colspan="18">家庭收入信息</td>
			</tr>
			<tr>
				<td>序号</td>
				<td>年度</td>
				<td>养老金</td>
				<td>低保金</td>
				<td>五保金</td>
				<td>高龄补贴</td>
				<td colspan="2">地力补助</td>
				<td>土地流转</td>
				<td>生产经营收入</td>
				<td>残补</td>
				<td>计生补贴</td>
				<td colspan="5">备注</td>
				<td><input type="button" onclick="open_dougang_income_window('0','${bean.id}')" value="增加" ></td>
			</tr>
			<c:forEach var="income" items="${list_income }">
				<c:set var="index" value="${index+1 }" />
				<tr height="50px">
					<td>${index }</td>
					<td>${income.year }</td>
					<td>${income.jinOld }</td>
					<td>${income.jinLow }</td>
					<td>${income.jinWu }</td>
					<td>${income.jinGao }</td>
					<td colspan="2">${income.earthPower }</td>
					<td>${income.earthTrans }</td>
					<td>${income.product }</td>
					<td>${income.canbu }</td>
					<td>${income.birthPlan }</td>
					<td colspan="5">${income.remarks }</td>
					<td><a href="#" onclick="open_dougang_income_window('${income.id}','${bean.id }')" >修改</a></td>
				</tr>
			</c:forEach>
			<tbody>
		</table>
	</div>
</body>
</html>
