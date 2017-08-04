package com.huanke.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlBaseOperation {
	/**
	 * 创建于数据库的链接
	 * 
	 * @return conn
	 */
	public Connection createSqlConntection(String databaseName) {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
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
	public PreparedStatement getPreparedStatement(Connection conn, String sql) {
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
