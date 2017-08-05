<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script>
		function check() {
			var addDocumentTitle = document.getElementsById("documentTitle").value;
			if(addDocumentTitle=="") {
				alert("输入文档不能为空！");
				document.getElementById("documentTitle").focus;
				return false;
			} else {
				return true;
			}
		}
	</script>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>文档管理系统</title>
		<style type="text/css">
			body {
				text-align: center
			}
			h1 {
				color: red
			}
			a {
  				color:blue;
			}
		</style>
	</head>
	<body>
		<h1>文档管理系统上传界面</h1>
		<h2>文档上传</h2>
	</body>
	<form action="UploadFile" method="post"  enctype="multipart/form-data" onsubmit="return check()">
		请输入所要上传文档的标题：<input type="text" name="documentTitle" id="documentTitle" size="30">
		<input type="file" name="document" id="document"><br/><br/>
		请输入所要上传文档的标题：<input type="text" name="documentTitle1" id="documentTitle1" size="30">
		<input type="file" name="document2"><br/><br/>
		<input type="submit" name="upload" value="上传">
	</form><br/>
	<a href='startPage.jsp'>导航页</a>	
</html>