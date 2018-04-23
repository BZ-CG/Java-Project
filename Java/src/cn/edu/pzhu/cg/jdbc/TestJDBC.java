package cn.edu.pzhu.cg.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class TestJDBC {


	@Test
	public void testSelect() throws Exception{
		Connection conn = getConnection();
		Statement sta =  null;
		
		String sql = "select id, name, age from customers";
		sta = conn.createStatement();
		
		ResultSet res = sta.executeQuery(sql);
		while(res.next()){
			System.out.print(res.getInt(1) + "  ");//索引和字段名都可以
			System.out.print(res.getString(2) + "  ");
			System.out.println(res.getString("age"));
		}
		res.close();
		sta.close();
		conn.close();
	}
	@Test
	public void testUpdate() throws Exception{
		Connection conn = getConnection();
		Statement sta =  null;
		
		String sql = "update customers set name = 'ZS' where id = '1'";
		sta = conn.createStatement();
		
		int len = sta.executeUpdate(sql);
		System.out.println("更新行数：" + len);
		sta.close();
		conn.close();
	}
	@Test
	public void testInsert() throws Exception{
		Connection conn = getConnection();
		Statement sta =  null;
		
		String sql = "insert into customers(name,age) values('张三','21')";
		sta = conn.createStatement();
		
		int len = sta.executeUpdate(sql);
		System.out.println("更新行数：" + len);
		sta.close();
		conn.close();
	}
	@Test
	public void test1() throws Exception{
		Driver driver = new Driver();
		String url = "jdbc:mysql://localhost:3306/test";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "123456");
		Connection conn = driver.connect(url, info);
		System.out.println(conn);
		conn.close();
	}
	@Test
	public void test2() throws Exception{
		System.out.println(getConnection());
	}
	public Connection getConnection() throws Exception{
		String driver;
		String url;
		String user;
		String password;
		
		InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		/*Driver driver2 = new Driver();
		Connection conn =driver2.connect(url, properties);
		return conn;*/
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
	
	public void release(Connection conn,PreparedStatement ps,ResultSet rs){
		if(conn != null){
			try {
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
