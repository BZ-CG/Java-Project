package cn.edu.pzhu.cg.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import cn.edu.pzhu.cg.thread.testThread2;

public class testBeanUtils {

	@Test
	public void test4(){
		String sql = "select name,age,tel,major  from student where id= ?";
		Student student = getObject(Student.class, sql,"1");
		System.out.println(student);
	}
	@Test
	public void test3(){
		String sql = "select name,age,tel,major  from student";
		List<Student> students = getObjectList(Student.class, sql);

		for(Student stu:students){
			System.out.println(stu);
		}
	}
	@Test
	public void test2() throws Exception{
		Student student = new Student();
		BeanUtils.setProperty(student, "name", "张三");
		BeanUtils.setProperty(student, "age", "18");
		
		Object val = BeanUtils.getProperty(student, "age");
		System.out.println(val);
	}
	@Test
	public void test1() throws Exception{
		Student student = new Student();
		BeanUtils.setProperty(student, "name", "张三");
		BeanUtils.setProperty(student, "age", "18");
		System.out.println(student);
		
	}
	/*
	 * 返回某个对象集合的方法
	 */
	public <T> List<T> getObjectList(Class<T> clazz,String sql,Object... args){
		List<T> list = new ArrayList<>();
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
			
			List<Map<String, Object>> values = new ArrayList<>();
			 
			Map<String, Object> map = null;
			while(rs.next()){
				map = new HashMap<>();
				for(int i = 0;i < rsmd.getColumnCount();i++){
					String columnLabel = rsmd.getColumnLabel(i+1);//获取列名
					Object columnObject = rs.getObject(columnLabel);//获取该列的数据
					map.put(columnLabel, columnObject);
				}
				values.add(map);
			}
			
			T bean = null;
			
			if(values.size() > 0){
				//一个 map 就是一个对象
				for(Map<String, Object> m:values){
					bean = clazz.newInstance();
					//获取该对象的所有属性值
					for(Map.Entry<String, Object> entry:m.entrySet()){
						String propertiName = entry.getKey();
						Object propertiValue = entry.getValue();
						//设置该对象的所有属性值
						BeanUtils.setProperty(bean, propertiName, propertiValue);
					}
					list.add(bean);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(conn, ps, rs);
		}
		
		return list;
	}
	
	/*
	 * 有了上面获取某个对象集合的方法，可以将前面获取单个对象的方法简化。
	 */
	public <T> T getObject(Class<T> clazz,String sql,Object... args){
		List<T> resultList = getObjectList(clazz, sql, args);
		if(resultList.size() > 0){
			return resultList.get(0);
		}
		return null;
	}
}
