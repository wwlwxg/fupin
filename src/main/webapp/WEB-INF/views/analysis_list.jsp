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
	var ren = 0;
	var hu = 0;
	var earth = 0.0;
	var canji = 0;
	var sixteen_nan = 0;
	var sixteen_nv = 0;
	var sixteen_nan_nv = 0;
	var sixty_nan = 0;
	var sixty_nv = 0;
	var sixty_nan_nv = 0;
	var sixfive_nan = 0;
	var sixfive_nv = 0;
	var sixfive_nan_nv = 0;
	var eight_nan = 0;
	var eight_nv = 0;
	var eight_nan_nv = 0;
	var hun_nan = 0;
	var hun_nv = 0;
	var hun_nan_nv = 0;
	var hunBig_nan = 0;
	var hunBig_nv = 0;
	var hunBig_nan_nv = 0
	
	$(".ren").each(function(i){
		ren += parseFloat($(this).text());
	});
	$(".hu").each(function(i){
		hu += parseFloat($(this).text());
	});
	$(".earth").each(function(i){
		earth += parseFloat($(this).text());
	});
	$(".canji").each(function(i){
		canji += parseFloat($(this).text());
	});
	$(".sixteen_nan").each(function(i){
		sixteen_nan += parseFloat($(this).text());
	});	
	$(".sixteen_nv").each(function(i){
		sixteen_nv += parseFloat($(this).text());
	});
	$(".sixteen_nan_nv").each(function(i){
		sixteen_nan_nv += parseFloat($(this).text());
	});
	$(".sixty_nan").each(function(i){
		sixty_nan += parseFloat($(this).text());
	});
	$(".sixty_nv").each(function(i){
		sixty_nv += parseFloat($(this).text());
	});
	$(".sixty_nan_nv").each(function(i){
		sixty_nan_nv += parseFloat($(this).text());
	});
	$(".sixfive_nan").each(function(i){
		sixfive_nan += parseFloat($(this).text());
	});
	$(".sixfive_nv").each(function(i){
		sixfive_nv += parseFloat($(this).text());
	});
	$(".sixfive_nan_nv").each(function(i){
		sixfive_nan_nv += parseFloat($(this).text());
	});
	$(".eight_nan").each(function(i){
		eight_nan += parseFloat($(this).text());
	});
	$(".eight_nv").each(function(i){
		eight_nv += parseFloat($(this).text());
	});
	$(".eight_nan_nv").each(function(i){
		eight_nan_nv += parseFloat($(this).text());
	});
	$(".hun_nan").each(function(i){
		hun_nan += parseFloat($(this).text());
	});
	$(".hun_nv").each(function(i){
		hun_nv += parseFloat($(this).text());
	});
	$(".hun_nan_nv").each(function(i){
		hun_nan_nv += parseFloat($(this).text());
	});
	$(".hunBig_nan").each(function(i){
		hunBig_nan += parseFloat($(this).text());
	});
	$(".hunBig_nv").each(function(i){
		hunBig_nv += parseFloat($(this).text());
	});
	$(".hunBig_nan_nv").each(function(i){
		hunBig_nan_nv += parseFloat($(this).text());
	});
	
	$(".sum_ren").text(ren);
	$(".sum_hu").text(hu);
	$(".sum_earth").text(earth.toFixed(2));
	$(".sum_canji").text(canji);
	$(".sum_sixteen_nan").text(sixteen_nan);
	$(".sum_sixteen_nv").text(sixteen_nv);
	$(".sum_sixteen_nan_nv").text(sixteen_nan_nv);
	$(".sum_sixty_nan").text(sixty_nan);
	$(".sum_sixty_nv").text(sixty_nv);
	$(".sum_sixty_nan_nv").text(sixty_nan_nv);
	$(".sum_sixfive_nan").text(sixfive_nan);
	$(".sum_sixfive_nv").text(sixfive_nv);
	$(".sum_sixfive_nan_nv").text(sixfive_nan_nv);
	$(".sum_eight_nan").text(eight_nan);
	$(".sum_eight_nv").text(eight_nv);
	$(".sum_eight_nan_nv").text(eight_nan_nv);
	$(".sum_hun_nan").text(hun_nan);
	$(".sum_hun_nv").text(hun_nv);
	$(".sum_hun_nan_nv").text(hun_nan_nv);
	$(".sum_hunBig_nan").text(hunBig_nan);
	$(".sum_hunBig_nv").text(hunBig_nv);
	$(".sum_hunBig_nan_nv").text(hunBig_nan_nv);
	
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
%>
<body>
	<h1 align="center">祝站村一户一档全员信息统计表</h1>
<div style="margin-left: auto;margin-right:auto;width: 1400px">
	<table class="tbDetail" cellspacing="0" cellpadding="2" align="center" width="1540px">
	<thead>
		<tr>
			<td align="right" colspan="23">单位：户、人、亩</td>
		</tr>
		<tr align="center" class="tr-head">
			<td rowspan="2">组</td>
			<td rowspan="2">人数</td>
			<td rowspan="2">户数</td>
			<td rowspan="2">土地(亩)</td>
			<td rowspan="2">残疾</td>
			<td colspan="3">16岁以下</td>
			<td colspan="3">60岁以下</td>
			<td colspan="3">65岁以下</td>
			<td colspan="3">80岁以下</td>
			<td colspan="3">80岁以上</td>
			<td colspan="3">问题数据</td>
		</tr>
		<tr align="center" class="tr-head">
			<td>男</td>
			<td>女</td>
			<td>小计</td>
			<td>男</td>
			<td>女</td>	
			<td>小计</td>
			<td>男</td>
			<td>女</td>
			<td>小计</td>
			<td>男</td>
			<td>女</td>
			<td>小计</td>
			<td>男</td>
			<td>女</td>
			<td>小计</td>
			<td>男</td>
			<td>女</td>	
			<td>小计</td>		
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${map}" var="data">
			<tr class="tr-class">
				<td>${data.key }</td>
				<td class="ren">${data.value[0].renSum + data.value[1].renSum }</td>
				<td class="hu">${data.value[0].huSum}</td>
				<td class="earth">
				<fmt:formatNumber 
							type="number" 
							value="${data.value[0].earthSum}"
							pattern="0.##" maxFractionDigits="2"/> 
				</td>
				<td class="canji">${data.value[0].canji + data.value[1].canji }</td>
				<td class="sixteen_nan">${data.value[0].sixteen }</td>
				<td class="sixteen_nv">${data.value[1].sixteen}</td>
				<td class="sixteen_nan_nv">${data.value[0].sixteen + data.value[1].sixteen }</td>
				<td class="sixty_nan">${data.value[0].sixty - data.value[0].sixteen }</td>
				<td class="sixty_nv">${data.value[1].sixty - data.value[1].sixteen}</td>
				<td class="sixty_nan_nv">${data.value[0].sixty + data.value[1].sixty - data.value[0].sixteen - data.value[1].sixteen }</td>
				<td class="sixfive_nan">${data.value[0].sixFive - data.value[0].sixty }</td>
				<td class="sixfive_nv">${data.value[1].sixFive - data.value[1].sixty}</td>
				<td class="sixfive_nan_nv">${data.value[0].sixFive + data.value[1].sixFive - data.value[0].sixty - data.value[1].sixty }</td>
				<td class="eight_nan">${data.value[0].eighty - data.value[0].sixFive }</td>
				<td class="eight_nv">${data.value[1].eighty - data.value[1].sixFive}</td>
				<td class="eight_nan_nv">${data.value[0].eighty + data.value[1].eighty - data.value[0].sixFive - data.value[1].sixFive }</td>
				<td class="hun_nan">${data.value[0].hun - data.value[0].eighty }</td>
				<td class="hun_nv">${data.value[1].hun - data.value[1].eighty}</td>
				<td class="hun_nan_nv">${data.value[0].hun + data.value[1].hun - data.value[0].eighty - data.value[1].eighty }</td>
				<td class="hunBig_nan">${data.value[0].hunBig}</td>
				<td class="hunBig_nv">${data.value[1].hunBig}</td>
				<td class="hunBig_nan_nv">${data.value[0].hunBig + data.value[1].hunBig}</td>
			</tr>
		</c:forEach>
	<tr class="grey">
		<td>合计</td>
		<td class="sum_ren"></td>
		<td class="sum_hu"></td>
		<td class="sum_earth"></td>
		<td class="sum_canji"></td>
		<td class="sum_sixteen_nan"></td>
		<td class="sum_sixteen_nv"></td>
		<td class="sum_sixteen_nan_nv"></td>
		<td class="sum_sixty_nan"></td>
		<td class="sum_sixty_nv"></td>
		<td class="sum_sixty_nan_nv"></td>
		<td class="sum_sixfive_nan"></td>
		<td class="sum_sixfive_nv"></td>
		<td class="sum_sixfive_nan_nv"></td>
		<td class="sum_eight_nan"></td>
		<td class="sum_eight_nv"></td>
		<td class="sum_eight_nan_nv"></td>
		<td class="sum_hun_nan"></td>
		<td class="sum_hun_nv"></td>
		<td class="sum_hun_nan_nv"></td>
		<td class="sum_hunBig_nan"></td>
		<td class="sum_hunBig_nv"></td>
		<td class="sum_hunBig_nan_nv"></td>
	</tr>
		</tbody>
	</table>
	</div>
</body>
</html>
