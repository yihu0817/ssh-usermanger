package com.ittx.usermanager.service;

import java.util.HashMap;
import java.util.List;

import com.ittx.usermanager.model.User;

public interface UserService {
	public void addUser(HashMap<String,String> parameterMap);  //添加用户
	public List<User> getAllUser(String userName,int userSex,int startIndex,int size);  //获取用户列表
	public void batchDelete(String userIds);
	public User getUserById(int id);
	public int getTotal(String userName,int userSex);
}
