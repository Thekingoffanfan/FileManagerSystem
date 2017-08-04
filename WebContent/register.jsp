<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
function addCheck(){  
            var username=document.getElementById("addUserName").value;  
            var password=document.getElementById("addPassword").value;  
            var newword=document.getElementById("checkPassword").value;  
            if(username==""){  
                alert("用户名不能为空!");  
                document.getElementById("addUserName").focus();    
                return false;  
                }  
            if(password==""){  
                alert("密码不能为空!");  
                 document.getElementById("addPassword").focus();  
                 return false;  
                 }  
            if(password != newword){  
                alert("两次输入密码不相同!");  
                 document.getElementById("checkPassword").focus();  
                 return false;  
                 }  
        }  
        function validate(){  
            var flag = addCheck();  
            if(flag == false)  
                return false;  
            return true;  
        }  
    </script>  
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文档管理系统</title>
	<style type="text/css">
 		h1 {color: red}
		body {text-align: center}
		a {  color:blue;}
	</style>
</head>
	<body>
		<h1>文档管理系统注册</h1>
		<h2>用户注册</h2>
		<form action="Register" method="post" onsubmit="return validate()">
			用户名：<input type="text" name="addUserName" id="addUserName"><br><br>
			密&nbsp;&nbsp;码：<input type="password" name="addPassword" id="addPassword"><br><br>
			确认密码：<input type="password" name="checkPassword" id="checkPassword"><br><br>
			<input type="submit" name="signIn" id="signIn" value="注册">
		</form><br/>
	<a href='startPage.jsp'>导航页</a>	
	</body>
</html>