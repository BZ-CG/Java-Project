package cn.edu.pzhu.cg.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cn.edu.pzhu.cg.jdbc.TestJDBC;
import cn.edu.pzhu.cg.thread.testThread2;

import org.junit.Test;

public class TestStatementAndPreparedStatement {

	public void test(Object... args){
		
	}
	@Test
	public void testPreparedStatement2(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			String sql = "select id,name,age from customers";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("ID:" + rs.getString(1));
				System.out.println("Name:" + rs.getString("name"));
				System.out.println("Age:" + rs.getString(3));
				System.out.println();
			}
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e.printStackTrace(););
		}finally{
				try {
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(ps != null)
						ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
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
	
	/*
	 * PreparedStatement优点：
	 * 	 1).对象已经预编译过，执行速度比 Statement 快。
	 * 	 2).代码简洁。
	 * 	 3).PreparedStatement中用 '?' 占位参数，所以可以 有效的防止 SQL 注入
	 */
	@Test
	public void testPreparedStatement(){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = getConnection();
			String sql = "insert into customers(name,age) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "ZS");
			ps.setInt(2, 21);
			
			int num = ps.executeUpdate();
			System.out.println("更新行数：" + num);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				try {
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/*
	 * SQL 注入式攻击样例
	 */
	@Test
	public void testSQLInjection(){
		String name = "a' OR age = ";
		String age = "OR '1' = '1";
		
		String sql = "select * from customers where name = '"+name+"'"
				+ "and age = '"+age+"'";
		
		System.out.println(sql);
		
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			if(rs.next()){
				System.out.println("恭喜你，登陆成功！");
			}else{
				System.out.println("登陆失败，请检查用户名及密码！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			release(conn, sta, rs);
		}
	}
	@Test
	public void testStatement() throws Exception{
		Connection conn = getConnection();
		Statement sta =  null;
		
		String sql = "update customers set name = 'ZS' where id = '1'";
		sta = conn.createStatement();
		
		int len = sta.executeUpdate(sql);
		System.out.println("更新行数：" + len);
		sta.close();
		conn.close();
	}
	public void release(Connection conn,Statement sta,ResultSet rs){
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
