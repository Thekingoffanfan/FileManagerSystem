package com.huanke.dao;

import java.util.List;

import com.huanke.model.Document;

public interface DocumentDao {

	public void addDocument(Document document);

	public void deletDocById(int documenId);

	public List<Document> getDocById(int documentId);

	// 得到总页数
	public int allPage(int row, String DocumentTitle);

	// 根据分页显示
	public List<Document> getDocumentPage(int page, int pagesize);

	public List<Document> fuzzyQuery(String condition);

	// 分页显示（模糊查询）
	public List<Document> fuzzyQuery(int page, int pagesize, String documentTitle);

}
