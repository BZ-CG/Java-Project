package cn.edu.pzhu.cg.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

public class TestDBCP {

	/*
	 * 使用 DBCP 数据库连接池:
	 * 1.导包(2个)，依赖于 Commons Pool
	 * 2.创建数据库连接池
	 * 3.设置常用属性
	 * 4.获取连接
	 */
	@Test
	public void testDBCP() throws Exception{
		BasicDataSource dataSource = null;
		//创建 DBCP 示例
		dataSource = new BasicDataSource();
		
		//为数据源设置必须的属性
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		//为数据源设置可选的属性
		
		//(1).指定数据库连接池中初始化连接数的个数
		dataSource.setInitialSize(10);
		
		//(2).设置连接池中最大连接数：同一时刻可以同时向数据库申请的连接数
		dataSource.setMaxIdle(50);
		
		//(3).设置连接池的最小连接数：在数据库连接池中保存的最少的空闲的连接数量
		dataSource.setMinIdle(5);
		
		//(4).等待数据库连接池分配连接的最长时间，单位为毫秒，超出规定时间将抛出异常
		dataSource.setMaxWaitMillis(1000 * 5);
		
		//从数据源中获取连接
		Connection conn = dataSource.getConnection();
		System.out.println(conn.getClass());
	}
	/*
	 * 1.将 DataSource 数据源所有属性写在 properties 配置文件中
	 * 2.调用 BasicDataSourceFactory 的 createDataSource(properties) 方法实例化 DataSource 对象
	 * 3.从 DataSource 中获取 Conection 连接
	 */
	@Test
	public void testDataSource() throws Exception{
		
		InputStream in = JDBCTools.class.getClassLoader().getResourceAsStream("dbcp.properties");
		Properties properties = new Properties();
		
		properties.load(in);
		
		DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
		
		System.out.println(dataSource.getConnection());
		
		
		BasicDataSource basicDataSource = (BasicDataSource) dataSource;
		System.out.println(basicDataSource.getMaxWaitMillis());
		System.out.println(basicDataSource.getMaxIdle());
	
	}
}
