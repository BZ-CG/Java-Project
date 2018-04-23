package cn.edu.pzhu.cg.jdbc.TestDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.edu.pzhu.cg.jdbc.Customers;

/**  
* 创建时间：2018年1月18日 上午1:27:45  
* 项目名称：Java  
* @author 张超  
* @version 1.0   
* @since JDK 1.6.0_21  
* 文件名称：CustomersDAO.java  
* 类说明：  
*/
public class CustomersDAO implements DAO<Customers> {

	@Override
	public void update(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Customers getObject(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> getObjectList(Connection conn, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> E getValue(Connection connection, String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
  