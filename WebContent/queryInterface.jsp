<%@page import="java.util.Iterator"%>
<%@page import="com.huanke.model.Document"%>
<%@page import="java.util.List"%>
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
	<h1>文档管理系统查询界面</h1>
	<h2>用户查询</h2>
	<form action="QueryDocument" method="post">
		输入查询关键字：<input type="text" name="query" id="query"> <input
			type="submit" name="querySubmit" id="querySubmit" value="查询">
	</form>
	<% 
	List<Document> documentsList = (List<Document>)request.getAttribute("GoodsList"); 
	for (Document documents :documentsList)	{
		out.println("<tr>");
		out.print("<td>" + documents.getDocumentName() + "</td>");
		out.print("<td>" + documents.getDocumentPath() + "</td>");
		out.println("<td><a href='DeleteDocument?id=" + documents.getdId()
		+ "' onclick='deletDocument()'>删除</a></td></tr>");
	}
	%>
</body>
</html>