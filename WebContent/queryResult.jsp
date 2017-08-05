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

a {
	color: blue;
}
</style>
<script>
	function deletDocument() {
		if (confirm("确定要删除该题目？")) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<%! String value = ""; %>
	<h1>文档管理系统查询界面</h1>
	<h2>用户查询</h2>
	<form action="QueryDocument" method="get">
		<%
			if (request.getAttribute("value") == null) {
				value = "";
			} else {
				value = (String) request.getAttribute("value");
			}
		%>
		输入查询关键字：<input type="text" name="query" id="query" value=<%=value%>>
		<input type="submit" name="querySubmit" id="querySubmit" value="查询">
	</form>
	<%
		//用于报错
		String queryFeedback = (String)request.getAttribute("queryFeedback");
		if (queryFeedback != null) {%>
			<script>alert('<%=queryFeedback%>')</script>
	<%}%>
		<%
			//显示查询结果
		if (request.getAttribute("results") != null) {
			//获得当前页，总页数，用于显示
			int pageNow = 1;
			int pageCount = 0;
			if (request.getAttribute("pageNow") != null) {
				pageNow = (Integer) request.getAttribute("pageNow");
			}
			if (request.getAttribute("pageCount") != null) {
				pageCount = (Integer) request.getAttribute("pageCount");
			}
			List<Document> results = (List) request.getAttribute("results");
			if (!results.isEmpty()) {%>
				<p>查询结果</p>
				<table border='1' align='center'>
				<tr><th>文档标题</th><th>存放路径</th><th></th><tr>
				<%Iterator<Document> resultsIt = results.iterator();
				while (resultsIt.hasNext()) {
					Document doc = (Document) resultsIt.next();%>
					<tr><td> <%=doc.getDocumentName()%> </td>
					<td><%=doc.getDocumentPath()%></td>
					<td><a href='DeleteDocument?id=<%=doc.getdId() %>&query=<%=value %>&pageNow=<%=pageNow %>' onclick='deletDocument()'>删除</a></td></tr>
				<%}%>
				</table>
				<% if (pageNow != 1) {%>
					<a href='QueryDocument?pageNow=<%=(pageNow - 1)%>&query=<%=value %>'>上一页</a>
			<%	}
				// 显示分页
				for (int i = 1; i <= pageCount; i++) {
					// 这个href的写法很重要
					if (i == pageNow) {%>
						<a style='color:red' href='QueryDocument?pageNow=<%=i %>&query=<%=value %>'><%=i %></a>
					<%} else { %>
						<a href='QueryDocument?pageNow=<%=i %>&query=<%=value %>'><%=i %></a>
					<%}
				}
				// 显示下一页
				if (pageNow != pageCount) {%>
					<a  href='QueryDocument?pageNow=<%=(pageNow + 1) %>&query=<%=value %>'>下一页</a>
				<%} 
				// 显示分页信息%>
				&nbsp;&nbsp;&nbsp;当前页 <%=pageNow %> /总页数<%=pageCount %>
			<%}
		}
	%>
	<br />
	<a href='startPage.jsp'>导航页</a>
</body>
</html>