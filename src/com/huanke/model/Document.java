package com.huanke.model;

/**
 * 
 * @author Administrator
 *
 */
public class Document {
	private int userId = 1;
	private int dId = 1;;
	private String documentName = null;
	private String documentPath = null;
	private String md5 = null;

	public Document() {

	}

	public Document(int userId, String documentName, String documentPath, String md5) {
		this.userId = userId;
		this.documentName = documentName;
		this.documentPath = documentPath;
		this.md5 = md5;
	}

	/**
	 * 
	 * @param documentName
	 * @param documentPath
	 */
	public Document(int userId, int dId, String documentName, String documentPath, String md5) {
		this.userId = userId;
		this.dId = dId;
		this.documentName = documentName;
		this.documentPath = documentPath;
		this.md5 = md5;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int id) {
		this.dId = id;
	}

	/**
	 * 
	 * @return String
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * 
	 * @param documentName
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * 
	 * @return String
	 */
	public String getDocumentPath() {
		return documentPath;
	}

	/**
	 * 
	 * @param documentPath
	 */
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

}
