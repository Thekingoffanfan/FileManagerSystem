package com.huanke.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;

import com.huanke.User;

/**
 * MySql Operation
 */
public class UserSqlServiceImpl extends SqlBaseOperation implements SqlService {
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSqlServiceImpl() {
		super();
	}

	/**
	 * 向数据库中插入数据，并会断开连接
	 * 
	 * @param user
	 */
	public void insertData(String name, String password) {
		Connection conn = null; // 一个连接对象
		conn = this.createSqlConntection("lixtudy"); // 得到一个连接
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
	public ResultSet query(String user) {
		Connection conn = this.createSqlConntection("lixtudy");
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
		Connection conn = this.createSqlConntection("lixtudy");
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
}
