package com.ittx.usermanager.util;

import java.io.IOException;
import java.net.URLEncoder;

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
 * 第一步: 判断当前请求资源是否需要登录认证
 * 
 * 
 *
 */
public class LoginFilter implements Filter {
	private String[] noUriArrays;

	public void init(FilterConfig fConfig) throws ServletException {
		String noUri = fConfig.getInitParameter("noUri");
		noUriArrays = noUri.split(";");
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* 第一步: 判断当前请求资源是否需要登录认证 */
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String currenUri = req.getRequestURI();
		if (noUriArrays != null && noUriArrays.length > 0) {
			for (String uri : noUriArrays) {
				if (currenUri.endsWith(uri)) { // 判断当前请求URI是否是不需要登录认证的，不需要登录认证直接放行
					chain.doFilter(request, response); // 放行
					return;
				}
			}
		}

		/* 第二步 登录认证 ；登录后保存登录状态，根据登录状态判断是否登录认证 */
		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("loginState");
		if (userName != null) {
			chain.doFilter(request, response);
		} else {
			String msg = URLEncoder.encode("你还没有登录!", "UTF-8");
			resp.sendRedirect("login.jsp?message=" + msg);
		}
	}

}
