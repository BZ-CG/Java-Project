package cn.edu.pzhu.cg.jdbc.TestDAO;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import cn.edu.pzhu.cg.jdbc.JDBCTools;
import cn.edu.pzhu.cg.jdbc.Student;
import cn.edu.pzhu.cg.jdbc.testBeanUtils;

/**  
* 测试代理类  
*/
public class StudentDAOProxyTest {

	StudentDAOProxy stuDao = new StudentDAOProxy(); 
	
	@Test
	public void test(){
		StudentDAOProxy stuDao = DAOFactory.getStudentDAOProxyInstance();
		String sql = "select name,age,tel,major from student where id >= ?";
		List<Student> list = stuDao.getStudentList(sql, 1);
		System.out.println(list);
		
	}
	@Test
	public void testUpdate() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "insert into student(name,age,tel,major) values(?,?,?,?)";
			stuDao.update(conn, sql, "Test",20,"123456789","播音主持");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);
		}
	}

	@Test
	public void testGetObject() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,tel,major from student where id = ?";
			Student student =stuDao.getObject(conn, sql, 10);
			System.out.println(student);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);
		}
		
	}

	@Test
	public void testGetObjectList() {
		Connection conn = null;
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,tel,major from student where id >= ?";
			List<Student> list =stuDao.getObjectList(conn, sql, 1);
			
			for (Student student : list) {
				System.out.println(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);
		}
		
	}

	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

}
  