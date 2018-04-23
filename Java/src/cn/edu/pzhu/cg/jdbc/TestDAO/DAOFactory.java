package cn.edu.pzhu.cg.jdbc.TestDAO;   

/** 
* @Description DAO 工厂类，获取 DAO 实例  
* @version 1.0   
* @since JDK 1.6.0_21  
* 文件名称：DAOFactory.java  
* 类说明：  
*/
public class DAOFactory {

	public static StudentDAOProxy getStudentDAOProxyInstance(){
		return new StudentDAOProxy();
	}
	
	public static CustomersDAO getCustomersDAOInstance(){
		return new CustomersDAO();
	}
}
  