package cn.edu.pzhu.cg.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.junit.Test;

public class MetaDataTest {

	/*
	 * DatabaseMetaData:是描述数据库的元数据对象
	 * 可以由Connection获得
	 */
	@Test
	public void testDatabaseMetaData() {
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = JDBCTools.getConnection();
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			//可以得到数据库本身的一些信息
			
			//1.得到数据库的版本号
			int version = databaseMetaData.getDatabaseMajorVersion();
			System.out.println("版本号为：" + version);
			
			//2.得到连接数据库的用户名
			String user = databaseMetaData.getUserName();
			System.out.println("用户名：" + user);
			
			//3.得到连接数据库的URL
			String url = databaseMetaData.getURL();
			System.out.println("URL：" + url);
			
			//4.得到所有的数据库
			rs = databaseMetaData.getCatalogs();
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(conn, null, rs);
		}
	}

}
