package com.ittx.usermanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库父类
 * 
 * 1.加载数据库驱动
 * 2.建立连接
 * 3.关闭连接
 * @author scxh
 *
 */
public class ConnectDB {
	private static final String URL = "jdbc:mysql://localhost:3306/usermanager?characterEncoding=utf8&useSSL=true";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	public ConnectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysql驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 建立数据库连接
	 */
	public Connection getConnection() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connect;
	}

	/**
	 * 关闭数据库连接
	 */
	public void closeConnection(Connection connect) {
		try {
			if (connect != null)
				connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
