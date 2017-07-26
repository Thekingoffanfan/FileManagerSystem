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
				color: red
			}
		</style>
	</head>
	<body>
		<h1>文档管理系统上传界面</h1>
		<h2>文档上传</h2>
	</body>
	<form action="UploadFile" method="post"  enctype="multipart/form-data">
		请输入所要上传文档的标题：<input type="text" name="documentTitle" id="documentTitle" size="30"> <br/><br/>
		请选择所要上传的文件：<input type="file" name="document" id="document" multiple="multiple">
		<input type="submit" name="upload" value="上传">
	</form>
</html>