<%@page import="java.util.Iterator"%>
<%@page import="com.huanke.mode.Document"%>
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
		<form action="Query" method="post">
			输入查询关键字：<input type="text" name="query" id="query">
			<input type="submit" name="querySubmit" id="querySubmit" value="查询">
		</form>
		<%
			List<Document> results = (List)request.getAttribute("test");
			if(results.isEmpty()) {
				out.println("<script>alert('没有匹配的信息，请重新输入关键字')</script>");
			} else {
				 out.println("<p>查询结果</p>");
				 out.println("<table border='1' align='center'>");
			 	 Iterator<Document> resultsIt = results.iterator();
				 while(resultsIt.hasNext())  {
				 Document doc = (Document)resultsIt.next();
				 out.println("<tr><td>"+doc.getDocumentName()+"</td>");
				 out.println("<td>"+doc.getDocumentPath()+"</td>");
		//		 session.setAttribute(doc.getDocumentPath(), doc.getDocumentPath());
				 out.println("<td><a href='main.jsp?id="+Doc+ "onclick="delet()">删除</a></td></tr>");
			 }
		 }
		%>
		</table>	
	</body>
</html>