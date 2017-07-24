<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理系统</title>
<style type="text/css">
body {
	text-align: center
}
h1 {
	color: red;
}
</style>
</head>
<body>
	<h1>文档管理系统登录界面</h1>
	<h2>用户登录</h2>
	<form action="FileManagerSystem" method="post">
		用户名：<input type="text" id="username" name="username" value="lixstudy" size="20">
		<br /> 密 &nbsp;&nbsp;码：<input type="password" id=userpassword name="userpassword"
			size="20"> <br /> <br />
			 <input type="submit"  name="logIn" id="logIn" value="登录"> 
			 <input type="button" onclick="window.location.href='register.jsp'" onclick="openpage()" value="注册">
	</form>
	</body>
</html>