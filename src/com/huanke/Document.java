package com.huanke;

/**
 * 
 * @author Administrator
 *
 */
public class Document {
	String documentName = null;
	String documentPath = null;

	/**
	 * 
	 * @param documentName
	 * @param documentPath
	 */
	public Document(String documentName, String documentPath) {
		this.documentName = documentName;
		this.documentPath = documentPath;
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
