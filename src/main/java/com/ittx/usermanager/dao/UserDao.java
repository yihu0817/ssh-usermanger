package com.ittx.usermanager.dao;

import java.util.List;

import com.ittx.usermanager.model.User;

public interface UserDao {
	public void addUser(User user); 
	public List<User> getAllUser(String userName,int userSex,int startIndex,int size);
	public void batchDelete(String userIds);
	public User getUserById(int id);
	public void updateUser(User user);
	public int getTotal(String userName,int userSex);
}
