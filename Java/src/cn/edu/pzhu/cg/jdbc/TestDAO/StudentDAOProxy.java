package cn.edu.pzhu.cg.jdbc.TestDAO;

import java.sql.Connection;
import java.util.List;

import cn.edu.pzhu.cg.jdbc.JDBCTools;
import cn.edu.pzhu.cg.jdbc.Student;

/**  
*	代理类  : 所有的方法实体已交由实现类 JdbcDaoImpl 完成，代理类中完成数据库的连接和关闭以及调用实现类中相应的方法；或者按照需求添加相应的方法
*/
public class StudentDAOProxy extends JdbcDaoImpl<Student>{

	private Connection conn = null;
	public StudentDAOProxy(){
		try {
			this.conn = JDBCTools.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	    * @Description: TODO(调用实现类中对象的方法实体，实现相应的操作)
	    * @param @param sql
	    * @param @param args
	    * @throws
	 */
	public List<Student> getStudentList(String sql,Object... args){
		List<Student> list = null;
		try {
			//调用实现类的对应方法实体
			list = getObjectList(conn, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, null, null);
		}
		return list;
	}
}
  