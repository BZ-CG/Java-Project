package cn.edu.pzhu.cg.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.sound.sampled.LineListener;

import org.junit.Test;

public class TestBLOB {

	/*
	 * 读取数据库里面的 Blob 类型文件：
	 * 	1.获取该文件的 Blob 对象 Blob blob = rs.getBlob(3);
	 * 	2.从该 Blob 对象中获取次文件的输入流对象
	 * 	3.通过输入流和输出流，输出该文件
	 */
	@Test
	public void readBlob(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCTools.getConnection();
			String sql = "select name,age,picture from student where id = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, 8);
			
			rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString(1);
				String age = rs.getString(2);
				//获取此文件的 Blob 对象
				Blob blob = rs.getBlob(3);
				//获取此文件的输入流对象
				InputStream is = blob.getBinaryStream();
				OutputStream os =  new FileOutputStream(new File("Jack.jpg"));
				
				byte[] b = new byte[1024];
				int len;
				
				while((len = is.read(b)) != -1){
					os.write(b, 0, len);
				}
				
				os.close();
				is.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, ps, rs);
		}
	}
	
	/*
	 * 插入 BLOB 类型的数据必须使用 PreparedStatement,因为 BLOB 类型的数据无法使用字符串拼接
	 * 
	 * 调用 setBlob(int index,InputStream) 方法
	 */
	@Test
	public void testBLOB(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCTools.getConnection();
			String sql = "insert into student(name,age,tel,major,picture) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "Jack");
			ps.setInt(2, 20);
			ps.setString(3, "13056505519");
			ps.setString(4, "土木工程");
			
			//文件输入流
			InputStream is = new FileInputStream(new File("1.jpg"));
			ps.setBlob(5, is);
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(conn, ps, rs);
		}
	}
}
