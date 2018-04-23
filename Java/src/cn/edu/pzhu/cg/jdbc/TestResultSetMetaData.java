package cn.edu.pzhu.cg.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.edu.pzhu.cg.jdbc.Student;

public class TestResultSetMetaData {

	@Test
	public void test2(){
		String sql = "select name name ,age age from customers where name = ?";
		Customers customers = getObject(Customers.class, sql, "ZS");
		System.out.println(customers);
		
		String sql2 = "select name name ,age age,major major,tel tel from student where age = ? and tel = ?";
		Student student = getObject(Student.class, sql2, "18","15082383272");
		System.out.println(student);
		
	}
	 /*
	  * ResultSetMetaData：可以获取结果集中所有的列，以及所有列的值
	  * 	1.getColumnCount():获取列数目。
	  * 	2.getColumnLabel(int index):获取列名。
	  * 	
	  */
	@Test
	public void testResultSetMetaData(){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = "select name name1,age age1 from customers where name = ?";
			conn = JDBCTools.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "ZS");
			rs = ps.executeQuery();
			
			//1.获取ResultSetMetaData对象
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//2.在结果集中将所有的结果按键值对的形式存放在 Map 中
			Map<String, Object> values = new HashMap<>();
			
			while(rs.next()){
				//3.获取每一列的数据
				for(int i = 0;i < rsmd.getColumnCount();i++){
					String columnLabel = rsmd.getColumnLabel(i+1);//获取列名(如果有别名就是该字段的别名)
					Object columnObject = rs.getObject(columnLabel);//获取该列的数据
//					String columnName = rsmd.getColumnName(i+1);
//					System.out.println(columnLabel + " - " + columnName);
					values.put(columnLabel, columnObject);
				}
			}
			//4.通过反射实例化相应的对象
			Class clazz = Customers.class;
			Object object = clazz.newInstance();
			
			//5.通过反射为相应对象赋值，属性即为 Map 的键，属性值为 Map 的值
			for (Map.Entry<String, Object> entry:values.entrySet()) {
				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();
				
				Field field = clazz.getDeclaredField(fieldName);
				field.set(object, fieldValue);
				
				//System.out.println(fieldName + ":" + fieldValue);
			}
			System.out.println(object);
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println("发生异常");
		}finally{
			JDBCTools.release(conn, ps, rs);
		}
		
	}
	/*
	 * 封装的一个查询单条结果的方法，以泛型的形式返回适合所有情况，因此通用性很强。
	 */
	public <T> T getObject(Class<T> clazz,String sql,Object... args){
		T entity = null; //保存结果的对象，因为不知道具体的类，所以用泛型
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JDBCTools.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < args.length;i++){
				ps.setObject(i+1,args[i]);
			}
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			Map<String, Object> values = new HashMap<>();
			
			if(rs.next()){
				for(int i = 0;i < rsmd.getColumnCount();i++){
					String columnLabel = rsmd.getColumnLabel(i+1);//获取列名
					Object columnObject = rs.getObject(columnLabel);//获取该列的数据
					values.put(columnLabel, columnObject);
				}
			}

			entity = clazz.newInstance();
			
			for (Map.Entry<String, Object> entry:values.entrySet()) {
				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();
				
				Field field = clazz.getDeclaredField(fieldName);
				field.set(entity, fieldValue);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(conn, ps, rs);
		}
		
		return entity;
	}
	/*
	 * 1.Object...args:动态参数
	 * 	传入的是一个参数数组，在调用此方法时，传入参数个数不确定，可以不传，可以传一个，可以传多个...
	 * 2.PreparedStatement.setObject(int index,Object object):
	 * 	为 PreparedStatement 要执行的 sql 设置参数，index 表示第几个位置的参数，object 表示该位置的参数
	 * 3.
	 */
	@Test
	public void test1(){
		String sql = "select name,age from customers where name = ? and age = ?";
//		String sql = "select name,age from customers ";
//		Customers customers = getCustomers(sql); //不传参数也是可以的
		Customers customers = getCustomers(sql, "ZS","21");
		System.out.println(customers);
	}
	public Customers getCustomers(String sql,Object...args){
		Customers customers = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JDBCTools.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < args.length;i++){
				ps.setObject(i+1,args[i]);
			}
			rs = ps.executeQuery();
			if(rs.next()){
				customers = new Customers();
				customers.setName(rs.getString(1));
				customers.setAge(rs.getInt(2));
			}
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println("发生异常");
		}finally{
			JDBCTools.release(conn, ps, rs);
		}
		
		return customers;
	}
}
