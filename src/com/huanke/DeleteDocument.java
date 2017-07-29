package com.huanke;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.dao.DocumentDao;
import com.huanke.dao.impl.DocumentDaoImpl;
import com.huanke.mode.Document;

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

		// 服务器输出流
		PrintWriter out = response.getWriter();

		// 获得要删除文档的id
		String tempDocumentId = request.getParameter("id");
		int documentId = Integer.parseInt(tempDocumentId);

		// 从查询结果中把要删除的信息删除
		List<Document> deleteDoc = (List<Document>) request.getSession().getAttribute("sendResults");
		Iterator<Document> deleteDocIt = deleteDoc.iterator();
		while (deleteDocIt.hasNext()) {
			Document doc = deleteDocIt.next();
			if (doc.getdId() == documentId) {
				deleteDocIt.remove();
			}
		}

		// 查找出要删除文档的path，并删除
		DocumentDao deleteDocument = new DocumentDaoImpl();
		List<Document> documentsList = deleteDocument.getDocById(documentId);
		Iterator<Document> documentsIt = documentsList.iterator();
		while (documentsIt.hasNext()) {
			Document document = documentsIt.next();
			File delete = new File(document.getDocumentPath());
			delete.delete();
		}
		// 从数据库中删除要删除文档的信息
		deleteDocument.deletDocById(documentId);

		// 把删除后的查询结果传给queryResults.jsp，并返回
		request.setAttribute("test", deleteDoc);
		request.getRequestDispatcher("/queryResult.jsp").forward(request, response);

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
