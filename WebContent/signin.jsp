<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文档管理系统</title>
	<style type="text/css">
 		h1 {color:blue}
		body {text-align: center}
	</style>
</head>
	<body>
		<h1>文档管理系统注册</h1>
		<form name="registerForm" id="registerForm" action="FileManagerSystem" method="get">
			用户名：<input type="text" name="addUserName" id="addUserName"><br><br>
			密&nbsp;&nbsp;码：<input type="password" name="addPassword" id="addPassword"><br><br>
			确认密码：<input type="password" name="checkPassword" id="checkPassword"><br><br>
			<input type="submit" name="signIn" id="signIn" value="注册">
		</form>
	    
	</body>
	<script>
	window.location="http://localhost:8083/FileManagerSystem_1/main.jsp"
	</script>
</html>