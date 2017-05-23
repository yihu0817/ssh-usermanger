package com.ittx.usermanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ittx.usermanager.dao.UserDao;
import com.ittx.usermanager.model.User;
import com.ittx.usermanager.util.ConnectDB;

public class UserDaoImpl extends ConnectDB implements UserDao {
	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	@Override
	public void addUser(User user) {
		String sql = "INSERT INTO user (name,age,sex,headuri)VALUES(?,?,?,?)";
		Connection connection = getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setInt(3, user.getSex());
			ps.setString(4, user.getHeaderUri());
			ps.execute();
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (ps != null)
					ps.close();
				closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<User> getAllUser(String userName,int userSex,int startIndex, int size) {
		String sql = "SELECT * FROM user where 1=1 ";
		StringBuilder sb = new StringBuilder(sql);
		if(userName != null && !"".equals(userName)){
			sb.append(" and name like '"+userName+"'");
		}
		if(userSex != -1){
			sb.append(" and sex = "+userSex);
		}
		sb.append(" limit ?,?");
		
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userLists = new ArrayList<User>();
		try {
			ps = connection.prepareStatement(sb.toString());
			ps.setInt(1, startIndex);
			ps.setInt(2, size);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				int sex = rs.getInt("sex");
				String headerUri = rs.getString("headuri");
				User user = new User(id, name, age, sex,headerUri);
				userLists.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return userLists;
	}

	/**
	 * "10,11,21"
	 */
	@Override
	public void batchDelete(String userIds) {
		String sql = "delete from user where id in(" + userIds + ")";
		Connection connection = getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User getUserById(int ids) {
		String sql = "SELECT * FROM user where id = ?";
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, ids);
			rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				int sex = rs.getInt("sex");
				String headerUri = rs.getString("headuri");
				user = new User(id, name, age, sex,headerUri);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE user SET name = ? ,age = ?, sex = ?,headuri = ? WHERE id = ?";
		Connection connection = getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.setInt(3, user.getSex());
			ps.setString(4, user.getHeaderUri());
			ps.setInt(5, user.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int getTotal(String userName,int userSex) {
		String sqls = "SELECT count(*) FROM user where 1=1 ";
		StringBuilder sb = new StringBuilder(sqls);
		if(userName != null && !"".equals(userName)){
			sb.append(" and name like '"+userName+"'");
		}
		if(userSex != -1){
			sb.append(" and sex = "+userSex);
		}
		log.info("sql :"+sb.toString());
		
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int totalCount = 0; // 总记录数
		try {
			ps = connection.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			
			if (rs.next()) {
				totalCount = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalCount;
	}

}
