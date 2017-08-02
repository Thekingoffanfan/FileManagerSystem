package com.huanke.dao;

import java.util.List;

import com.huanke.model.Document;

public interface DocumentDao {

	// 添加文档
	public void addDocument(Document document);

	// 通过文档ID精确删除
	public void deletDocById(int documenId);

	// 通过文档ID精确查询
	public List<Document> getDocById(int documentId, int userId);

	// 得到总页数
	public int allPage(int row, int userId, String DocumentTitle);

	// 分页显示（模糊查询）
	public List<Document> fuzzyQuery(int page, int pagesize, int userId, String documentTitle);

}
