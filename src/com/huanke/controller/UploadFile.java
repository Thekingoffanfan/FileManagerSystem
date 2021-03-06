package com.huanke.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.huanke.dao.DocumentDao;
import com.huanke.dao.impl.DocumentDaoImpl;
import com.huanke.model.Document;
import com.huanke.util.Md5Encryption;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	// 文件存储路径
	private String filePath = null;

	/**
	 * 上传数据及保存文件
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 获得登录用户名的Id
		int userId = 0;
		if (request.getSession().getAttribute("userId") != null) {
			userId = (Integer) request.getSession().getAttribute("userId");
		}
		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中(3M)
		factory.setSizeThreshold(MEMORY_THRESHOLD);

		// System.out.println(request.getContextPath());
		String tempFilePath = request.getContextPath() + File.separator + "temp";
		// 设置临时存储目录，系统默认路径，F:/java/workspace/FileManagerSystem_1/ServiceData/temp”
		factory.setRepository(new File(tempFilePath));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值,40M
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据),50M
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		Date todayDate = new Date();
		String todayDateString = dateToStr(todayDate);

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = request.getContextPath() + "/ServiceData" + File.separator + todayDateString;

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		try {
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request);
			String fieldValue = null;

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {

					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						if (!fileName.isEmpty()) {
							// 获得文件内容，对文件内容生成md5摘要加密，输入流
							InputStream in = item.getInputStream();
							String fileMd5 = Md5Encryption.getMD5(in);
							filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);

							// 生成绝对路径用于上传到数据库中
							filePath = storeFile.getAbsolutePath();
							DocumentDao documentSql = new DocumentDaoImpl();
							if (documentSql.isExistByMd5(fileMd5)) {
								List<Document> docList = documentSql.getDocByMD5(fileMd5);
								Iterator<Document> docIt = docList.iterator();
								if (docIt.hasNext()) {
									filePath = docIt.next().getDocumentPath();
								}
							} else {
								// 保存文件到硬盘
								System.out.println("上传到服务器！");
								item.write(storeFile);
							}

							Document document = new Document(userId, fieldValue, filePath, fileMd5);
							// 检查完是否有重复文件再把文档 信息添加到数据库中，否则逻辑顺序颠倒就会出错
							documentSql.addDocument(document);

							// 在控制台输出文件的上传绝对路径
							System.out.println(filePath);
							// 跳转回查询界面
							request.setAttribute("queryFeedback", "文档上传成功");
							request.getRequestDispatcher("/queryResult.jsp").forward(request, response);
						}

						// 处理表单中的字段，获得标题
					} else {
						fieldValue = item.getString("utf-8");
					}
				}
			}
		} catch (

		Exception ex) {
			request.setAttribute("queryResult", "错误信息: " + ex.getMessage());
		}
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
}