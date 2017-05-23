package com.ittx.usermanager.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ittx.usermanager.model.User;
import com.ittx.usermanager.service.UserService;
import com.ittx.usermanager.service.impl.UserServiceImpl;
import com.ittx.usermanager.util.Pager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 控制层
 * 
 * 1.接收 请求 2.调用业务层 3.响应数据
 *
 */

public class UserAction extends ActionSupport {
	private static final Logger log = Logger.getLogger(UserAction.class);
	private UserService userService = new UserServiceImpl();

	public String addUser() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id =request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String type = request.getParameter("type");
		String headerUri = request.getParameter("headeruri");
		
		HashMap<String, String> parameterMap = new HashMap<>();
		parameterMap.put("id", id);
		parameterMap.put("name", name);
		parameterMap.put("sex", sex);
		parameterMap.put("type", type);
		parameterMap.put("headerUri", headerUri);
		parameterMap.put("age", age);
		userService.addUser(parameterMap);

		return SUCCESS;

	}

	/**
	 * 显示用户列表
	 * @return
	 */
	public String listUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 用户名
		String userName = request.getParameter("username");
		if (userName != null) {
			userName = userName.trim();
		}

		// 性别
		String sexs = request.getParameter("sex");
		int sex = -1;
		if (sexs != null) {
			sex = Integer.parseInt(sexs);
		}
		// 当前页号
		String current = request.getParameter("page");
		int currentPage = 1;

		if (current == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(current);
		}
		// ==============
		Pager pager = new Pager(userService.getTotal(userName, sex), currentPage);
		if (null != userName) {
			pager.addParameter("username", userName);
		}
		if (null != sexs) {
			pager.addParameter("sex", sexs);
		}
		pager.setUrl("user_listUser");

		List<User> userLists = userService.getAllUser(userName, sex, pager.getIStart(), pager.getIStep());

		request.setAttribute("userLists", userLists);
		request.setAttribute("pagerUri", pager.getPageStr());
		request.setAttribute("userName", userName);
		request.setAttribute("sexs", sex);
		
		return "list";
	}
	/**
	 * 修改用户信息
	 * @return
	 */
	public String updateUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId");
		User user = userService.getUserById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		request.setAttribute("type", "modify");
		return "update";
	}
	/**
	 * 删除用户
	 * @return
	 */
	public String deleteUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userIds = request.getParameter("userIds");
		userService.batchDelete(userIds);
		return SUCCESS;
	}

	/**
	 * 返回上传文件保存File地址
	 * 
	 * @param fileItem
	 * @return
	 */
	public File getSaveFile(FileItem fileItem, HashMap<String, String> parameterMap) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = fileItem.getName(); // 不同浏览器
		String filePath = request.getServletContext().getRealPath("/") + "upload";
		File fileDir = new File(filePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File saveFile = new File(fileDir, name);
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("fileDir :" + fileDir + "  headerUri :" + ("/upload/" + name));
		parameterMap.put("headeruri", "/upload/" + name);
		return saveFile;
	}

}
