package com.huanke.dao;

import java.util.List;

import com.huanke.Document;

public interface DocumentDao {

	public void addDocument(Document document);

	public void deletDocument(Document document);

	List<Document> getDocByName(String DocumentTitle);

	// 得到总页数
	int allPage(int row, String DocumentTitle);

	// 根据分页显示
	List<Document> getDocumentPage(int page, int pagesize);

	// 分页显示（模糊查询）
	List<Document> fuzzyQuery(int page, int pagesize, String DocumentTitle);

}
