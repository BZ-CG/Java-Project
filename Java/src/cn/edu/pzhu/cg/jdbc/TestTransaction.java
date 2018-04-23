package cn.edu.pzhu.cg.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class TestTransaction {

	/*
	 * 事务操作：
	 * 	1.conn.setAutoCommit(false):设置事务为不自动提交
	 * 	2.操作全部成功完成，提交事务:conn.commit()
	 * 	3.发生异常，回滚事务:conn.rollback()
	 */
	@Test
	public void test(){
		Connection conn = null;
		DAO dao = new DAO();
		try {
			conn = JDBCTools.getConnection();
			//开启事务，设置不自动提交
			conn.setAutoCommit(false);//这一步必须要有，很重要
			
			String sql = "update customers set balance = balance - 5000 where id = 1";
			dao.updata(conn, sql);
			
			//制造异常
			int i = 10/0;
			
			sql = "update customers set balance = balance + 5000 where id = 2";
			dao.updata(conn, sql);
			
			//提交事务
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//发生异常，执行回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			JDBCTools.release(conn, null, null);
		}
	}
}
