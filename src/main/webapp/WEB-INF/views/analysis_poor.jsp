<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>统计表</title>
<link type="text/css" href="css/sign.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
window.onload = function() {
	effect_touch_big();
	parse();
}


function effect_touch_big() {
	$("tr").hover(function(){$(this).addClass("effect_touch_big");},function(){$(this).removeClass("effect_touch_big");})
}

function parse() {
	var ren_1 = 0;
	var ren_2 = 0;
	var bing_1 = 0;
	var bing_2 = 0;
	var can_1 = 0.0;
	var can_2 = 0.0;
	var xue_1 = 0;
	var xue_2 = 0;
	var jishu_1 = 0;
	var jishu_2 = 0;
	
	$(".ren").each(function(i){
		var temp = $(this).text().split("/");
		ren_1 += parseFloat(temp[0]);
		ren_2 += parseFloat(temp[1]);
		
	});
	$(".bing").each(function(i){
		var temp = $(this).text().split("/");
		bing_1 += parseFloat(temp[0]);
		bing_2 += parseFloat(temp[1]);
	});
	$(".can").each(function(i){
		var temp = $(this).text().split("/");
		can_1 += parseFloat(temp[0]);
		can_2 += parseFloat(temp[1]);
	});
	$(".xue").each(function(i){
		var temp = $(this).text().split("/");
		xue_1 += parseFloat(temp[0]);
		xue_2 += parseFloat(temp[1]);
	});
	$(".jishu").each(function(i){
		var temp = $(this).text().split("/");
		jishu_1 += parseFloat(temp[0]);
		jishu_2 += parseFloat(temp[1]);
	});	
	var s="<a target='_blank' href=\
		<c:url value='${basePath }/getPoorResidenceList.do'>\
	<c:param name='belong' value='1'></c:param>\
	<c:param name='poor_cause' value='因病致贫'></c:param>\
	<c:param name='family_type' value='${data.key }'></c:param>\
	</c:url>\
	'>";
	var s1 = "</a>";
	$(".sum_ren a").text(ren_1+"/"+ren_2);
	$(".sum_bing a").text(bing_1+"/"+bing_2);
	$(".sum_can a").text(can_1+"/"+can_2);
	$(".sum_xue a").text(xue_1+"/"+xue_2);
	$(".sum_jishu a").text(jishu_1+"/"+jishu_2);
	
}
</script>
<style type="text/css">
tr{
	height: 50px;
}
.grey{
	background-color: #ccc;
}
</style>
</head>
<%
String path=request.getContextPath();
String ipPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String basePath = ipPath +path;
request.setAttribute("basePath", basePath);
%>
<body>
	<h1 align="center">2018年度脱贫(27户)分类统计表</h1>
<div style="margin-left: auto;margin-right:auto;width: 1400px">
	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center" width="1540px">
	<thead>
		<tr align="center" class="tr-head">
			<td rowspan="2">贫困属性</td>
			<td rowspan="2">户数/人数</td>
			<td colspan="4">主要致贫原因</td>
		</tr>
		<tr align="center" class="tr-head">
			<td>因病户/人数</td>
			<td>因残户/人数</td>
			<td>因学户/人数</td>
			<td>缺技或缺地户/人数</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="data">
			<tr class="tr-class">
				<td>${data.key }</td>
				<td class="ren"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='family_type' value='${data.key }'></c:param>
									</c:url>
									">
									${data.value[0]+data.value[2]+data.value[4]+data.value[6]}/${data.value[1]+data.value[3]+data.value[5]+data.value[7]}
								</a></td>
				<td class="bing"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='因病致贫'></c:param>
									<c:param name='family_type' value='${data.key }'></c:param>
									</c:url>
									">
									${data.value[0]}/${data.value[1] }
								</a></td>
				<td class="can"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='因残致贫'></c:param>
									<c:param name='family_type' value='${data.key }'></c:param>
									</c:url>
									">
									${data.value[2]}/${data.value[3] }
								</a></td>
				<td class="xue"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='因学致贫'></c:param>
									<c:param name='family_type' value='${data.key }'></c:param>
									</c:url>
									">
									${data.value[4]}/${data.value[5] }
								</a></td>
				<td class="jishu"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='缺技术致贫'></c:param>
									<c:param name='family_type' value='${data.key }'></c:param>
									</c:url>
									">${data.value[6]}/${data.value[7] }
								</a></td>
			</tr>
		</c:forEach>
	<tr class="grey">
		<td>合计</td>
		<td class="sum_ren"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									</c:url>
									"></a></td>
		<td class="sum_bing"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='因病致贫'></c:param>
									</c:url>
									"></a></td>
		<td class="sum_can"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='因残致贫'></c:param>
									</c:url>
									"></a></td>
		<td class="sum_xue"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='因学致贫'></c:param>
									</c:url>
									"></a></td>
		<td class="sum_jishu"><a target="_blank" href="
									<c:url value='${basePath }/getPoorResidenceList.do'>
									<c:param name='belong' value='1'></c:param>
									<c:param name='poor_cause' value='缺技术致贫'></c:param>
									</c:url>
									"></a></td>
	</tr>
		</tbody>
	</table>
	</div>
</body>
</html>
