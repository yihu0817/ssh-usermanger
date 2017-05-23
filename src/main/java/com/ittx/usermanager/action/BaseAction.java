package com.ittx.usermanager.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements RequestAware,SessionAware,ServletContextAware{
	protected ServletContext context;
	protected Map<String, Object> sessionMap;
	protected Map<String, Object> requestMap;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	public BaseAction(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		session = request.getSession();
	}
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap= session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap=request;
		
	}
	
}
