<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testJson").click(function(){
			var url = "";
			var args = {};
			$.post(url, args, function(data){
				for(var i = 0; i < data.length; i++){
					var id = data[i].id;
					var lastName = data[i].lastName;
					
					alert(id+":"+lastName)
				}
			})
			return false;
		})
	});
</script>
<body>

<a href="testJson">test json</a>
<br/>

<a href="nihao">aassa</a>
<br/>
<form:form action="testPOJO" method="delete" commandName="user">
	username:<form:input path="username"/>
	<br/>
	password:<form:input path="password" />
	<br/>
	email:<form:input path="email"/>
	<br/>
	age:<form:input path="age"/>
	<br/>
	province:<form:input path="address.province" />
	<br/>
	city:<form:input path="address.city" />
	<br/>
	<input type="submit" value="submit"/>
</form:form>
</body>
</html>