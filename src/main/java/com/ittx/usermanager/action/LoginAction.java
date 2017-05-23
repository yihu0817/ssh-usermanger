package com.ittx.usermanager.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginAction extends BaseAction {
	private static final Logger log = Logger.getLogger(LoginAction.class);

	public String login() {
		String autoLogin = request.getParameter("autoLogin");
		String userName = request.getParameter("name");
		String passWord = request.getParameter("password");
		if ("admin".equals(userName) && "123".equals(passWord)) {
			// 保存登录用户名密码到cookie
			if ("true".equals(autoLogin)) {
				saveCookie(response, userName, passWord);
			} else {
				clearCookie(response);
			}
			// 保存登录状态信息
			HttpSession session = request.getSession();
			session.setAttribute("loginState", userName);
			// session.setMaxInactiveInterval(2);
			return SUCCESS;

		} else {
//			String msg = URLEncoder.encode("用户名或密码错误!", "UTF-8");
			request.setAttribute("message", "用户名或密码错误!");
			//request.getRequestDispatcher("login.jsp?message=" + msg).forward(request, response);
			return LOGIN;
		}
	}
	
	@Override
	public void validate() {
		log.debug("validte >>>>");
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public String logout(){
		session.invalidate();
		return SUCCESS;
	}
	
	/**
	 * 清除cookie
	 * 
	 * @param response
	 */
	private void clearCookie(HttpServletResponse response) {
		Cookie userNameCookie = new Cookie("userName", null);
		Cookie pswCookie = new Cookie("passWord", null);
		pswCookie.setMaxAge(0);
		userNameCookie.setMaxAge(0);
		response.addCookie(userNameCookie);
		response.addCookie(pswCookie);
	}

	/**
	 * 保存cookie
	 * 
	 * @param response
	 * @param userName
	 * @param passWord
	 */
	private void saveCookie(HttpServletResponse response, String userName, String passWord) {
		Cookie userNameCookie = new Cookie("userName", userName);
		Cookie pswCookie = new Cookie("passWord", passWord);
		response.addCookie(userNameCookie);
		response.addCookie(pswCookie);
	}
	
	
}
