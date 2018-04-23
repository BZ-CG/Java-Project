package cn.edu.pzhu.cg.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.junit.Test;

public class TestBatch {

	/*
	 * 批处理，将要执行的 SQL 语句分批次处理，大大减少了时间
	 * 使用批处理要数据库连接字符串要加上  "rewriteBatchedStatements=true"
	 * 即：
	 * 		jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8
	 */
	@Test
	public void testBatch(){
		Connection conn = null;
		PreparedStatement ps = null;
		int N = 100000;
		try {
			conn = JDBCTools.getConnection();
			String sql = "insert into test(name,date) values (?,?)";
			ps = conn.prepareStatement(sql);
			Date date = new Date(new java.util.Date().getTime());
			
			JDBCTools.beginTransaction(conn);
			long begin = System.currentTimeMillis();
			
			for(int i = 0;i < N;i++){
				ps.setString(1, "name_"+(i+1)+"");
				ps.setString(2, date.toString());
				
				//"积攒" SQL
				ps.addBatch();
				
				//当 "积攒" 到一定程度时(这里是300条语句)，统一执行积攒的 SQL 语句，并清空已积攒的 SQL 语句
				if((i + 1) % 300 == 0){
					//执行
					ps.executeBatch();
					//清除
					ps.clearBatch();
				}
			}
			if( N % 300 != 0){
				ps.executeBatch();
				ps.clearBatch();
			}
			
			long end = System.currentTimeMillis();
			System.out.println("Time:" + (end-begin));//831
			
			JDBCTools.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(conn);
		} finally{
			JDBCTools.release(conn, ps, null);
		}
	}
	
	/*
	 * 在 MySql 中不能很好的看出 PreparedStatement 和 Statement 的速度上的差异
	 * 但在 Oracle 中就能明显看出两者在速度上差别。
	 */
	@Test
	public void testBatchWithPreparedStatement(){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "insert into test(name,date) values(?,?)";
			ps = conn.prepareStatement(sql);
			Date date = new Date(new java.util.Date().getTime());
			
			JDBCTools.beginTransaction(conn);
			long begin = System.currentTimeMillis();
			
			for(int i = 0;i < 100000;i++){
				ps.setString(1, "name_"+i+1+"");
				ps.setString(2, date.toString());
				
				ps.executeUpdate();
			}
			
			long end = System.currentTimeMillis();
			System.out.println("Time:" + (end-begin));//6248
			
			JDBCTools.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(conn);
		} finally{
			JDBCTools.release(conn, ps, null);
		}
	}
	
	
	@Test
	public void testBatchWithStatement(){
		Connection conn = null;
		Statement sta = null;
		try {
			conn = JDBCTools.getConnection();
			sta = conn.createStatement();
			
			JDBCTools.beginTransaction(conn);
			long begin = System.currentTimeMillis();
			
			for(int i = 0;i < 100000;i++){
				String sql = "insert into test(name,date) values('name_"+(i+1)+"','2018-1-15')";
				sta.executeUpdate(sql);
			}
			
			long end = System.currentTimeMillis();
			System.out.println("Time:" + (end-begin));//6438
			
			JDBCTools.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(conn);
		} finally{
			JDBCTools.release(conn, sta, null);
		}
	}
}
