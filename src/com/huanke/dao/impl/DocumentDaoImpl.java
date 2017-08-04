package com.huanke.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.huanke.dao.DocumentDao;
import com.huanke.model.Document;

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
		String sql = "insert into document(userId,documentTitle,path,md5) values(?,?,?,?)";
		ps = this.getPreparedStatement(conn, sql);
		try {
			ps.setInt(1, document.getUserId());
			ps.setString(2, document.getDocumentName());
			ps.setString(3, document.getDocumentPath());
			ps.setString(4, document.getMd5());
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
	public List<Document> getDocById(int userId, int documentId) {
		Connection conn = this.createSqlConntection("lixtudy");
		PreparedStatement ps = null;
		List<Document> documentList = new ArrayList<Document>();
		ResultSet results = null;
		String sql = "select * from document where id = ? and userId = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, documentId);
			ps.setInt(2, userId);
			results = ps.executeQuery();
			while (results.next()) {
				documentList.add(new Document(results.getInt(1), results.getInt(2), results.getString(3),
						results.getString(4), results.getString(5)));
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

	@Override
	public void deletDocById(int documentId) {
		// TODO Auto-generated method stub
		Connection conn = this.createSqlConntection("lixtudy");
		String sql = "delete from document where id=?";
		PreparedStatement ps = this.getPreparedStatement(conn, sql);
		try {
			ps.setInt(1, documentId);
			ps.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			this.closeConnection(conn);
			this.closePreparedStatement(ps);
		}
	}

	/**
	 * 计算出分页以后的总页数，需要程动态计算
	 * 
	 * @param row
	 *            每页想要记录的行数，用户自己规定
	 * @param documentTitle
	 *            查询的关键字，用于查询出总的记录条数
	 */
	@Override
	public int allPage(int row, int userId, String documentTitle) {
		int rowCount = 0;
		Connection conn = this.createSqlConntection("lixtudy");
		PreparedStatement ps = null;
		ResultSet results = null;
		String sql = "select count(documentTitle) from document where documentTitle like '%" + documentTitle
				+ "%' and userId = ?";
		ps = this.getPreparedStatement(conn, sql);
		try {
			ps.setInt(1, userId);
			results = ps.executeQuery();
			if (results.next()) {
				rowCount = results.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
			this.closePreparedStatement(ps);
			this.closeResultSet(results);
		}
		return (rowCount - 1) / row + 1;
	}

	/**
	 * 分页显示 +模糊查询(non-Javadoc)
	 * 
	 * @param page:页数
	 * @param pagesize:每页显示的数量
	 * @param gname:商品名
	 * @return List<Document>
	 * @see com.fruits.dao.IGoods#getGoods(int, int, java.lang.String)
	 */

	@Override
	public List<Document> fuzzyQuery(int page, int pagesize, int userId, String documentTitle) {
		// TODO Auto-generated method stub
		List<Document> documentsList = new ArrayList<Document>();
		String sql = "select * from document where documentTitle like '%" + documentTitle
				+ "%'and userId = ? limit ?,?";
		Connection conn = this.createSqlConntection("lixtudy");
		PreparedStatement ps = this.getPreparedStatement(conn, sql);
		ResultSet results = null;
		int a = (page - 1) * pagesize;
		int b = pagesize;
		try {
			ps.setInt(1, userId);
			ps.setInt(2, a);
			ps.setInt(3, b);
			results = ps.executeQuery();
			while (results.next()) {
				documentsList.add(new Document(results.getInt(1), results.getInt(2), results.getString(3),
						results.getString(4), results.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeConnection(conn);
			this.closePreparedStatement(ps);
			this.closeResultSet(results);
		}
		return documentsList;
	}

	/**
	 * 通过文件内容生成的MD5来检查文件已经存在
	 * 
	 * @param String
	 *            md5
	 * @return boolean
	 */
	@Override
	public boolean isExistByMd5(String md5) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = this.createSqlConntection("lixtudy");
		String sql = "select * from document where md5 = '" + md5 + "'";
		PreparedStatement ps = this.getPreparedStatement(conn, sql);
		ResultSet results = null;
		results = ps.executeQuery();
		if (results.next()) {
			closeConnection(conn);
			closePreparedStatement(ps);
			closeResultSet(results);
			return true;
		} else {
			closeConnection(conn);
			closePreparedStatement(ps);
			closeResultSet(results);
			return false;
		}
	}
}
