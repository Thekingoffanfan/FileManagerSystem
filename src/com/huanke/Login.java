package com.huanke;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.dao.UserDao;
import com.huanke.dao.impl.Md5Encryption;
import com.huanke.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class LogIn
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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

		// 设置输出html,编码格式为utf-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 生成所要输出的html文本头
		PrintWriter out = null;
		out = response.getWriter();
		String doctype = "<!DOCTYPE HTML>\n";
		out.println(doctype + "<html>\n");

		// 生成用户数据
		String userName = null;
		String userPassword = null;
		userName = request.getParameter("userName");
		userPassword = request.getParameter("userPassword");
		String pwd = Md5Encryption.encryption(userPassword);
		User userLogin = null;
		userLogin = new User(userName, pwd);

		// 数据库操作
		UserDao adminOperation = null;
		adminOperation = new UserDaoImpl();
		// boolean checkResult = false;
		try {
			boolean checkResult = adminOperation.isExist(userLogin);
			if (checkResult == true) {
				out.println("<script>alert('登录成功');window.location.href='register.jsp';</script>");
			} else {
				out.println("<script>alert('用户名或密码错误！');window.location.href='main.jsp';</script> ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</html>\n");
	}
}
