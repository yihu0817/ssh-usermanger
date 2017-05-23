package com.ittx.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ittx.usermanager.dao.UserDao;
import com.ittx.usermanager.dao.impl.UserDaoImpl;
import com.ittx.usermanager.model.User;
import com.ittx.usermanager.service.UserService;

import sun.util.logging.resources.logging;
/**
 * 业务层 
 *
 */
public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public List<User> getAllUser(String userName,int userSex,int startIndex,int size) {
		return userDao.getAllUser(userName,userSex,startIndex,size);
	}

	@Override
	public void batchDelete(String userIds) {
		userDao.batchDelete(userIds);
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public void addUser(HashMap<String,String> parameterMap) {
		String id =parameterMap.get("id");
		String name = parameterMap.get("name");
		String age = parameterMap.get("age");
		String sex = parameterMap.get("sex");
		String type = parameterMap.get("type");
		String headerUri = parameterMap.get("headeruri");
		System.out.println("age :"+age+" ; sex :"+sex);
		
		if(type == null || !"modify".equals(type)){ //添加用户
			User user = new User(name, Integer.parseInt(age), Integer.parseInt(sex),headerUri);
			userDao.addUser(user);
		}else{//修改用户
			User user = new User(Integer.parseInt(id),name, Integer.parseInt(age), Integer.parseInt(sex),headerUri);
			userDao.updateUser(user);
		}
	}

	@Override
	public int getTotal(String userName,int userSex) {
		return userDao.getTotal(userName,userSex);
	}

}
