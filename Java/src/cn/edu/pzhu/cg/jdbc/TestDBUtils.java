package cn.edu.pzhu.cg.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

public class TestDBUtils {

	/*
	 * 测试 DBUtils 工具类
	 * 
	 */
	/*
	 * ScalarHandler:将结果集转化为一个数值(可以使任意的基本数据类型和字符串、Date 等)返回。
	 * 默认是返回第一列数据
	 */
	@Test
	public void testScalarHandler(){
		Connection conn = null;
		QueryRunner queryRunner = new QueryRunner();
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name from student where id >= ?";
			
			Object result = queryRunner.query(conn, sql, new ScalarHandler(),1);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);;
		}
		
	}
	
	/*
	 * MapListHandler:将结果集转化为一个 Map 的 List.
	 * Map 对应一条查询记录:键:SQL 查询的列名(不是别名), 值:列的值。
	 * 
	 */
	@Test
	public void testMapListHandler(){
		Connection conn = null;
		QueryRunner queryRunner = new QueryRunner();
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,tel,major from student where id >= ?";
			
			List<Map<String, Object>> list = (List<Map<String, Object>>) queryRunner.query(conn, sql, new MapListHandler(),1);
			for (Map<String, Object> map : list) {
				System.out.println(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);;
		}
		
	}
	
	/*
	 * MapHandler:返回 SQL 对应的第一条记录对应的 Map 对象。
	 * 键:SQL 查询的列名(不是别名), 值:列的值。
	 */
	@Test
	public void testMapHandler(){
		Connection conn = null;
		QueryRunner queryRunner = new QueryRunner();
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,tel,major from student where id >= ?";
			
			Map<String, Object> map = queryRunner.query(conn, sql, new MapHandler(),1);

			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);;
		}
		
	}
	/*
	 * BeanListHandler:将结果集封装成参数  class 对应的对象集合list。(list 不为 null ，但可能为空集合(size == 0))
	 */
	@Test
	public void test	(){
		Connection conn = null;
		QueryRunner queryRunner = new QueryRunner();
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,tel,major from student where id >= ?";
			
			List<Student> list =  (List<Student>) queryRunner.query(conn, sql, new BeanListHandler(Student.class),4);
			for (Student student : list) {
				System.out.println(student);
			}
			//System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);;
		}
		
	}
	
	
	/*
	 * BeanHandler:将结果集的第一条记录转为创建 BeanHandler 的对象传入的 class 参数对应的对象。
	 */
	@Test
	public void testBeanHandler(){
		Connection conn = null;
		QueryRunner queryRunner = new QueryRunner();
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,tel,major from student where id = ?";
			
			Student stu = (Student) queryRunner.query(conn, sql, new BeanHandler(Student.class),4);
			System.out.println(stu);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);;
		}
	}
	
	class MyResultSetHandler implements ResultSetHandler{

		List<Student> list = new ArrayList<>();
		@Override
		public Object handle(ResultSet rs) throws SQLException {
			while(rs.next()){
				String name = rs.getString(1);
				int age = rs.getInt(2);
				String tel = rs.getString(3);
				String major = rs.getString(4);
				
				Student stu = new Student(name, age, tel, major);
				list.add(stu);
			}
			
			return list;
		}
		
	}
	/*
	 * QueryRunner 的 query 方法的返回值取决于其 ResultSetHandler 参数的 handler 方法的返回值
	 */
	@Test
	public void testQuery(){
		QueryRunner queryRunner = new QueryRunner();
		
		String sql = "select name,age,tel,major from student";
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			@SuppressWarnings("unchecked")
			Object obj = queryRunner.query(conn, sql, new MyResultSetHandler());
			System.out.println(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);
		}
	}
	
	@Test
	public void testQueryRunner() throws Exception{
		QueryRunner queryRunner = new QueryRunner();
		
		String sql = "update test set name = 'name_200' where id = ?";
		
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			int rowNum = queryRunner.update(conn, sql, 2);
			System.out.println("影响的行数：" + rowNum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);
		}	
	}
}
