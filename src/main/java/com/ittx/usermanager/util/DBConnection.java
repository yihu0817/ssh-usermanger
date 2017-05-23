package com.ittx.usermanager.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class DBConnection {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static{
		InputStream is= DBConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties pro= new  Properties();
		try {
			pro.load(is);
			driver=pro.getProperty("driver");
			url=pro.getProperty("url");
			username=pro.getProperty("username");
			password= pro.getProperty("password");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection connection =null;
		try {
			//加载驱动
			Class.forName(driver);
			//DriverManager驱动程序管理器
			connection =DriverManager.getConnection(url, username, password);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public  static void close(PreparedStatement ps,ResultSet rs,Connection connection){
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
