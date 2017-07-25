package com.huanke.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

import com.huanke.User;

/**
 * MySql Operation
 */
public class SQLConnection {
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SQLConnection() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 向数据库中插入数据，并会断开连接
	 * 
	 * @param user
	 */
	public void insertData(User user) {
		String name = user.getUserName();
		String password = user.getUserPassword();
		Connection conn = null; // 一个连接对象
		conn = this.createSqlConntection(); // 得到一个连接
		PreparedStatement ps = null; // 用于插入数据a
		// sql语句，向表user里面，插入name和pass的值
		String sql = "insert into checklogin(username,userpassword) values(?,?)";
		ps = this.getPrepareStatement(conn, sql);
		try {
			ps.setString(1, name);
			ps.setString(2, password);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 与数据库中的信息进行匹配查询,需手动释放ResultSet链接
	 * 
	 * @param User
	 *            user
	 * @return ResultSet
	 */
	public ResultSet qurey(String user) {
		Connection conn = this.createSqlConntection();
		PreparedStatement ps = null;
		ResultSet result = null;
		String sql = "select * from checklogin where username = '" + user + "'";
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// try {
			// conn.close();
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
		return result;
	}

	/**
	 * 检测是否有与输入匹配的信息
	 * 
	 * @param User
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean isExist(User user) throws SQLException {
		Connection conn = this.createSqlConntection();
		PreparedStatement queryUser = null;
		ResultSet resultOfQueryUser = null;
		String sql = "select * from checklogin where username='" + user.getUserName() + "'and userpassword='"
				+ user.getUserPassword() + "'";
		queryUser = conn.prepareStatement(sql);
		resultOfQueryUser = queryUser.executeQuery();
		if (resultOfQueryUser.next()) {
			this.closeResultSet(resultOfQueryUser);
			this.closePreparedStatement(queryUser);
			this.closeConnection(conn);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 创建于数据库的链接
	 * 
	 * @return conn
	 */
	public Connection createSqlConntection() {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/lixtudy?useSSL=false";
		String user = "root";
		String password = "";
		Connection conn = null;
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(db_url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭与数据库之间的链接
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		conn = null;
	}

	/**
	 * 获得Statement
	 * 
	 * @param conn
	 * @return Statement
	 */
	public Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return stmt;
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stmt
	 */
	public void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		stmt = null;
	}

	/**
	 * 
	 * @param Connection
	 * @param String
	 * @return PreparedStatement
	 */
	public PreparedStatement getPrepareStatement(Connection conn, String sql) {
		PreparedStatement pps = null;
		try {
			pps = conn.prepareStatement(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return pps;
	}

	public void closePreparedStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		ps = null;
	}

	/**
	 * 获得ResultSet
	 * 
	 * @param Statement
	 * @return ResultSet
	 */
	public ResultSet getResultSet(Statement stmt) {
		ResultSet rs = null;
		try {
			rs = stmt.getResultSet();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return rs;
	}

	/**
	 * 关闭ResultSet
	 * 
	 * @param ResultSet
	 */
	public void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		rs = null;
	}

	public void executeUpdata(Connection conn, String sql) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
