package cn.edu.pzhu.cg.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

public class DAO {

	/*
	 * 封装通用的单条记录操作(插入、删除、修改)方法，为了后面的事务操作，所以这里 Connection 作为形参传入。
	 */
	public void updata(Connection conn,String sql,Object...args){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < args.length;i++){
				ps.setObject(i+1, args[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//Connection 是形参传入进来的，应该交由创建他的地方关闭，在这里不能关闭
			JDBCTools.release(null, ps, null);
		}
	}
	
}
