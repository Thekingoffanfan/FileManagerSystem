package com.huanke;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.dao.DocumentDao;
import com.huanke.dao.impl.DocumentDaoImpl;
import com.huanke.mode.Document;

/**
 * Servlet implementation class Query
 */
public class QueryDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设定编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 从表单中获得查询条件
		String condition = request.getParameter("query");

		// 服务器输出流
		// PrintWriter out = response.getWriter();

		// 文档数据库操作
		DocumentDao document = new DocumentDaoImpl();
		List<Document> results = null;
		results = document.fuzzyQuery(condition);
		// request.setAttribute("results", results);
		// request.getRequestDispatcher("/queryInterface.jsp").forward(request,
		// response);
		// 将数据发到JSP
		RequestDispatcher rd = request.getRequestDispatcher("/queryResult.jsp");
		request.setAttribute("test", results);// 存值
		// System.out.println("chenggong");
		rd.forward(request, response);// 开始跳转
	}

}
