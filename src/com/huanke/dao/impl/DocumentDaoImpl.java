package com.huanke.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huanke.Document;
import com.huanke.dao.DocumentDao;

public class DocumentDaoImpl extends SqlBaseOperation implements DocumentDao {

	/**
	 * 向数据库中插入数据，并会断开连接
	 * 
	 * @param user
	 */
	public void addDocument(Document document) {
		Connection conn = null; // 一个连接对象
		conn = this.createSqlConntection("lixtudy"); // 得到一个连接
		PreparedStatement ps = null; // 用于插入数据a

		// sql语句，向表user里面，插入name和pass的值
		String sql = "insert into document(documentTitle,path) values(?,?)";
		ps = this.getPrepareStatement(conn, sql);
		try {
			ps.setString(1, document.getDocumentName());
			ps.setString(2, document.getDocumentPath());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
			this.closePreparedStatement(ps);
		}
	}

	/**
	 * 与数据库中的信息进行匹配查询,需手动释放ResultSet链接
	 * 
	 * @param User
	 *            user
	 * @return ResultSet
	 */
	public List<Document> getDocByName(String DocumentTitle) {
		Connection conn = this.createSqlConntection("lixtudy");
		PreparedStatement ps = null;
		List<Document> documentList = new ArrayList<Document>();
		ResultSet results = null;
		String sql = "select * from document where username = '" + DocumentTitle + "'";
		try {
			ps = conn.prepareStatement(sql);
			results = ps.executeQuery();
			while (results.next()) {
				documentList.add(new Document(results.getString(1), results.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
			this.closePreparedStatement(ps);
			this.closeResultSet(results);
		}
		return documentList;
	}

	/**
	 * 检测是否有与输入匹配的信息
	 * 
	 * @param User
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean isExist(Document document) throws SQLException {
		Connection conn = this.createSqlConntection("lixtudy");
		PreparedStatement queryUser = null;
		ResultSet resultOfQueryUser = null;
		String sql = "select * from document where username='" + document.getDocumentName() + "'and userpassword='"
				+ document.getDocumentPath() + "'";
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

	@Override
	public void deletDocument(Document document) {
		// TODO Auto-generated method stub

	}

	@Override
	public int allPage(int row, String DocumentTitle) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Document> getDocumentPage(int page, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> fuzzyQuery(int page, int pagesize, String DocumentTitle) {
		// TODO Auto-generated method stub
		return null;
	}

}
