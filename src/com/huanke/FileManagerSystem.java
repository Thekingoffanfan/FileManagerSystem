package com.huanke;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huanke.sql.SQLConnection;

/**
 * Servlet implementation class sql_connection
 */
public class FileManagerSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private final String jdbcDriver = "com.mysql.jdbc.Driver";
	// private final String db_url = "jdbc:mysql://localhost:3306/lixtudy";
	// private String user = "root";
	// private String password = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileManagerSystem() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = new SQLConnection().createSqlConntection();
		Statement stat = null;
		PrintWriter out = response.getWriter();
		String title = "文件管理系统";
		String docType = "<!DOCTYPE HTML>\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body>\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n");
		try {
			stat = conn.createStatement();
			String sql = "select username, userpassword from checklogin";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				String username = rs.getString("username");
				String userPassword = rs.getString("userpassword");
				out.println("用户名：" + username);
				out.println("密码：" + userPassword);
				out.println("<br>");
			}
			out.println("</body></html>");
			rs.close();
			stat.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 最后是用于关闭资源的块
			try {
				if (stat != null)
					stat.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		// doGet(request, response);
	}

}
