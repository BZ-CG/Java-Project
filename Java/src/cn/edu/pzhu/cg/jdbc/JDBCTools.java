package cn.edu.pzhu.cg.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCTools {
	
	private static DataSource dataSource = null;
	//数据库连接池只应被初始化一次
	static{
		dataSource = new ComboPooledDataSource("helloC3p0");
	}
	
	public static Connection getConnection()throws Exception{
			
			//2.0 版本
			return dataSource.getConnection();
			
			//1.0版本
	/*		String driver;
			String url;
			String user;
			String password;
			
		//	InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		//	System.out.println("----"+JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			
			InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties properties = new Properties();
			properties.load(in);
			
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
	
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
			
	*/
	}
	public static void beginTransaction(Connection conn){
		if(conn != null){
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection conn){
		if(conn != null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void commit(Connection conn){
		if(conn != null){
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public static void release(Connection conn,PreparedStatement ps,ResultSet rs){
		if(conn != null){
			try {
				//数据库连接池中的 Connection 对象进行 close 时，并不是真正的进行关闭，而是把数据库连接归还到数据库连接池中 
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void release(Connection conn,Statement sta,ResultSet rs){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sta != null){
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
