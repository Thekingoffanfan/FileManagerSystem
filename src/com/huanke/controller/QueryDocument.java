package com.huanke.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.dao.DocumentDao;
import com.huanke.dao.impl.DocumentDaoImpl;
import com.huanke.model.Document;

/**
 * Servlet implementation class Query
 */
public class QueryDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 总页数
	private int pageCount = 0;
	// 每页能显示的最大记录数
	private final int pageSize = 5;
	// 当前是第几页
	private int pageNow = 1;

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
		// 设定编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 从表单中获得查询条件
		String condition = request.getParameter("query");

		// 文档数据库操作
		DocumentDao document = new DocumentDaoImpl();
		List<Document> results = null;

		String temp_pageNow = request.getParameter("pageNow");
		if (temp_pageNow != null) {
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}
		// 获得记录总页数
		pageCount = document.allPage(pageSize, condition);
		// 分页加模糊查询
		results = document.fuzzyQuery(pageNow, pageSize, condition);

		// results = document.fuzzyQuery(condition);

		// 如果没有匹配到信息，反馈报错信息
		if (results.isEmpty()) {
			request.setAttribute("feedback", "没有匹配的信息，请重新输入关键字！");
		}

		// 将数据发到JSP
		RequestDispatcher rd = request.getRequestDispatcher("/queryResult.jsp");
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("results", results);// 存值
		rd.forward(request, response);// 开始跳转
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
