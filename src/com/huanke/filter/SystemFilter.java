package com.huanke.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SystemFilter
 */
public class SystemFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SystemFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();

		// 判断用户访问的是登录操作，不进行自动登录，将jsp文件名拆截出来
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		//
		// System.out.println(uri);
		// System.out.println(contextPath);
		// System.out.println(path);
		// 如果是登录或者注册界面就放行，否则检测用户是否登录
		if (path.equals("/main.jsp") || path.equals("/register.jsp")) {
			System.out.println("登录或注册界面");
			chain.doFilter(request, response);
			return;
		} else {
			String userName = (String) session.getAttribute("userInfo");

			// 用户信息已存在说明已经登录
			if (userName != null) {
				System.out.println("已登录");
				chain.doFilter(request, response);
				return;
			} else {
				System.out.println("未登录");
				request.setAttribute("filterFeedback", "用户未登录，或登录已过期，请重新登录");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Demo1Filter初始化创建....");
	}

}
