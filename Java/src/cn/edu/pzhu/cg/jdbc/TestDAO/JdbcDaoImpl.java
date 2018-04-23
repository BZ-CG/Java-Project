package cn.edu.pzhu.cg.jdbc.TestDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.pzhu.cg.jdbc.ReflectionUtils;
import cn.edu.pzhu.cg.jdbc.Student;

/**  
* DAO 的具体实现类，主要实现 接口中的各个方法,而不需要管理数据库连接和关闭等操作。
*/
public class JdbcDaoImpl<T> implements DAO<T> {

	private QueryRunner queryRunner = null;
	private Class<T> type;
	
	public JdbcDaoImpl() {
		queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
		//System.out.println(type);
	}
	@Override
	public void update(Connection conn, String sql, Object... args) throws SQLException {
		queryRunner.update(conn, sql, args);
	}

	@Override
	public  T getObject(Connection conn, String sql, Object... args) throws SQLException {
		T entity = queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
		return entity;
	}

	@Override
	public List<T> getObjectList(Connection conn, String sql, Object... args) throws SQLException {
		return queryRunner.query(conn, sql, new BeanListHandler<>(type), args);
	}

	@Override
	public <E> E getValue(Connection connection, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
  