package cn.edu.pzhu.cg.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3P0 {

	/*
	 * c3p0数据库连接池：
	 * 	1.导包：1).c3p0-0.9.5.2.jar , 2).mchange-commons-java-0.2.11.jar
	 * 	2.创建 c3p0-config.xml 文件
	 * 	3.创建 ComboPooledDataSource 实例：
	 * 		DataSource dataSource  = new ComboPooledDataSource("helloC3p0");
	 * 	4.从DataSource 中获取数据库连接
	 */
	@Test
	public void testC3P0XML() throws Exception{
		DataSource dataSource  = new ComboPooledDataSource("helloC3p0");
		
		System.out.println(dataSource.getConnection());
		
		ComboPooledDataSource cpds = (ComboPooledDataSource) dataSource;
		System.out.println(cpds.getMaxPoolSize());
	}
	@Test
	public void testC3P0() throws Exception{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setUser("root");
		cpds.setPassword("123456");
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		
		System.out.println(cpds.getConnection());
	}
	@Test
	public void testJdbcTools() throws Exception{
		Connection conn = JDBCTools.getConnection();
		System.out.println(conn);
	}
}
