package com.huanke.mode;

/**
 * 
 * @author Administrator
 *
 */
public class Document {
	public int dId = 1;;
	private String documentName = null;
	private String documentPath = null;

	public Document(String documentName, String documentPath) {
		this.documentName = documentName;
		this.documentPath = documentPath;
	}

	/**
	 * 
	 * @param documentName
	 * @param documentPath
	 */
	public Document(int dId, String documentName, String documentPath) {
		this.dId = dId;
		this.documentName = documentName;
		this.documentPath = documentPath;
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
