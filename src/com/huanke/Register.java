package com.huanke;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.sql.Md5Encryption;
import com.huanke.sql.SQLConnection;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 规定编码格式
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 服务器输出流,应输出为HTML格式
		PrintWriter out = response.getWriter();
		String docType = "<!DOCTYPE HTML>\n";
		out.println(docType + "<html>\n<body>\n");

		// 获得注册信息，并创建成User类
		String inputUserName = request.getParameter("addUserName");
		String inputPassword = request.getParameter("addPassword");

		String pwd = Md5Encryption.encryption(inputPassword);
		// String password = new String(md5);
		User user1 = new User(inputUserName, pwd);

		// 创建关于数据库操作对象
		SQLConnection sqlOperation = new SQLConnection();

		// 查询匹配信息，返回结果
		ResultSet checkUserName = null;
		checkUserName = sqlOperation.qurey(user1.getUserName());
		try {

			// true则用户已存在，false说明是新用户
			if (checkUserName.next()) {
				out.println(
						"<script language='javascript'>alert('该用户已存在，请重新注册！');window.location.href='register.jsp';</script>");
				out.println("</html>");
			} else {
				sqlOperation.insertData(user1);
				out.println("<script language='javascript'>alert('用户注册成功！');window.location.href='main.jsp';</script>");
				out.println("</html>");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				checkUserName.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
