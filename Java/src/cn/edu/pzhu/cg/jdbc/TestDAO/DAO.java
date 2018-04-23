package cn.edu.pzhu.cg.jdbc.TestDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**  
*   
*/
public interface DAO<T> {

	
	
	/**
	 * 
	    * @Title: update
	    * @Description: TODO(用于操作 INSERT、UPDATE、DELETE 语句)
	    * @param @param conn:数据库连接
	    * @param @param sql:要执行的 sql 语句
	    * @param @param args:填充占位符的参数
	    * @throws SQLException
	 */
	void update(Connection conn,String sql,Object... args) throws SQLException;
	
	/**
	 * 
	    * @Title: getObject
	    * @Description: TODO(获取一个对象的方法)
	    * @param @param conn
	    * @param @param sql
	    * @param @param args
	    * @param @return    参数
	    * @throws
	 */
	T getObject(Connection conn,String sql,Object... args) throws SQLException;
	
	/**
	 * 
	    * @Title: getObjectList
	    * @Description: TODO(获取一组对象的集合)
	    * @param @param conn
	    * @param @param sql
	    * @param @param args
	    * @param @return    参数
	    * @throws
	 */
	List<T> getObjectList(Connection conn,String sql,Object... args) throws SQLException;
	
	/**
	 * 
	    * @Title: getForValue
	    * @Description: TODO(返回一个具体的值，比如姓名、工资、年龄等)
	    * @param @param connection
	    * @param @param sql
	    * @param @param args
	 */
	<E> E getValue(Connection connection,String sql, Object ... args) throws SQLException;
}
  