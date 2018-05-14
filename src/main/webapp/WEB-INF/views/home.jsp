<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
</head>
<body>

	<P><c:if test="${issuccess }">
		恭喜！成功了！
	</c:if>
	<c:if test="${!issuccess }" >
		失敗了！！！
	</c:if>
	</P>
</body>
</html>
