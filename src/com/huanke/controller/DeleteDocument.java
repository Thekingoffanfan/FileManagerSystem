package com.huanke.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.dao.DocumentDao;
import com.huanke.dao.impl.DocumentDaoImpl;
import com.huanke.model.Document;

/**
 * Servlet implementation class DeleteDocument
 */
public class DeleteDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDocument() {
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
		// 统一编码格式为"utf-8"
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 获得要删除文档的id
		String tempDocumentId = request.getParameter("id");
		System.out.println(tempDocumentId);
		int documentId = Integer.parseInt(tempDocumentId);

		// 获得登录用户名的Id
		int userId = 0;
		if (request.getSession().getAttribute("userId") != null) {
			userId = (Integer) request.getSession().getAttribute("userId");
		}

		// 查找出要删除文档的path，并删除
		DocumentDao deleteDocument = new DocumentDaoImpl();
		List<Document> documentsList = deleteDocument.getDocById(userId, documentId);
		Iterator<Document> documentsIt = documentsList.iterator();
		while (documentsIt.hasNext()) {
			Document document = documentsIt.next();
			File delete = new File(document.getDocumentPath());
			delete.delete();
		}
		// 从数据库中删除要删除文档的信息
		deleteDocument.deletDocById(documentId);

		// 把删除后的查询结果传给queryResults.jsp，并返回
		request.setAttribute("queryFeedback", "删除成功！");
		request.getRequestDispatcher("/QueryDocument").forward(request, response);

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
