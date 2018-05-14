<%@page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path=request.getContextPath();
String ipPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String basePath = ipPath +path+"/";
%>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery-1.9.1/jquery.min.js" ></script>
<!-- script begin -->
<script>
window.onload = function() {
	effect_touch_big();
}

function effect_touch_big() {
	$("tr").hover(function(){$(this).addClass("effect_touch_big");},function(){$(this).removeClass("effect_touch_big");})
}
</script>
<!-- script end -->
<style>
.effect_touch_big{
	background-color: yellow;
}
.tr-head {
	background-color: green;
}
table{
	border-collapse:collapse;
	border:1px solid black;
}
td {
	empty-cells:show;
	border:1px solid black;
}
</style>
</head>

<body>


<p><a href="Year">返回上一级</a></p>
<c:choose>
<c:when test="${year >= 2014}">
<h1 align="center">孝南区${year}年区本级政府投资项目</h1>
<h1 align="center">(<a href="<%=ipPath %>/upload/${year}/${file_path }.pdf" target="_blank">${file_path}</a>)</h1>
</c:when>
<c:otherwise>
<h1 align="center">孝南区2013年及以前区本级政府投资项目</h1>
<h1 align="center">(无)</h1>
</c:otherwise>
</c:choose>


<table align="center" border="1" cellspacing="0" cellpadding="10">
  <tr class="tr-head">
    <td width="40" align="center">序号</td>
    <td colspan="2" align="center">业主单位</td>
    <td width="108" align="center">项目名称</td>
    <td align="center">项目类型</td>
    <td width="671" align="center">项目概况</td>
    <td width="102" align="center">计划投资概算</td>
    <td align="center">支付情况</td>
    <td width="70" align="center">备注</td>
    <c:if test="${sessionScope.user.permission == AUTHORITY_MODIFY_PROJECT }">
    <td align="center">操作</td>
    </c:if>
  </tr>
  <tr>
    <td colspan="4" align="center">（总计${project_num}项）</td>
    <td colspan="2" align="center">以下${project_num}项为区本级政府投资项目</td>
    <td align="center">${sum }</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <c:if test="${sessionScope.user.permission == AUTHORITY_MODIFY_PROJECT }">
    <td align="center">
    	[<a href="ProjectInforEditUI?id=0&year=${year }">增加</a>]
    </td>
    </c:if>
  </tr>
  
  <c:set var="index" value="0" />
  <c:forEach  items="${project_map}" var ="list" varStatus="map_num">
  <c:forEach  items="${list.value}" var ="project_directory" varStatus="list_num">
  
  <!-- 
  <c:set var="sum" value="0"></c:set>
  <c:set var="sum" value="${sum+map_num['index'] * fn:length(list.value) + list_num['count']}"></c:set>
  -->
  <c:set var="index" value="${index+1}" />
  <tr>
    <td height="54" align="center">${index }</td><!-- 序号 -->
    <td width="69" align="center">${project_directory.constructEmployer}</td><!-- 业主单位 -->
    <td width="33" align="center">${list_num['count']}</td><!-- 业主单位项目序号 -->
    <td align="center"><a href="ProjectInfo?id=${project_directory.id }" target="_blank">${project_directory.name }</a></td><!-- 项目名称 -->
    <td align="center">${pa.getProjectType(project_directory.projectType)}</td>				<!-- 项目性质 -->
    <td align="center">${project_directory.projectOverview }</td><!-- 项目概况 -->
    <td align="center">${project_directory.sourceContractAmount }</td><!-- 计划投资概算 -->
    <td align="center"><a href="ProjectFundDetailServlet?id=${project_directory.id }" target='_blank'>付款明细</a></td>				<!-- 支付情况 -->
    <td align="center">${project_directory.remarks}</td><!-- 备注 -->
    <c:if test="${sessionScope.user.permission == AUTHORITY_MODIFY_PROJECT }">
    <td align="center">[<a href="ProjectInforEditUI?id=${project_directory.id }&year=${project_directory.year }">编辑</a>]</td>
    </c:if>
  </tr>
  </c:forEach>
  </c:forEach>
</table>

</body>
</html>