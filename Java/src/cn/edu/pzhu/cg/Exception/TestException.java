package cn.edu.pzhu.cg.Exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/*
 * Java 提供的是异常处理的抓抛模型
 * 1."抓":当我们执行代码时，一旦出现异常，就会在异常的代码处生成一个异常类型的对象，并将此对象抛出(自动抛出/手动抛出)。
 * 		 ①.一旦抛出此异常对象，那么程序就将终止执行。
 * 		 ②.此异常类的对象抛给方法的调用者。
 * 2."抓":抓住上一步抛出的异常对象；如何抓？即为异常的处理方式。
 * 		 方式一：
 * 			try{
 * 				//可能出现异常的代码
 * 			}catch(Exception1 e1){
 * 				//处理方式一
 * 			}catch(Exception2 e2){
 * 				//处理方式二
 * 			}finally{
 * 				//一定执行的代码(不管出不出现异常)
 * 			}
 * 			
 * 			如果 catch 中的异常是 "并列" 关系，顺序没有要求。
 * 			如果 catch 中的异常是 "包含" 关系，需将子类放在上面，父类放在下面，否则报错。
 * 			try-catch 可以相互嵌套。
 * 		方式二:在方法的声明出，显示抛出该异常对象的类型。
 * 			 1.异常的对象可以逐层向上抛，直至main中，在向上抛的过程中可以通过try--catch-finally 处理。
 * 			 2.throw 手动抛出一个异常 
 * 			 3.抛出自定义异常类(继承现有的异常类)
 */
public class TestException {

	@Test
	public void test1(){
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File("hello1.txt"));
			int a = 0;
			while((a = in.read()) != -1){
				System.out.print((char)a);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void m1(){
		System.out.println("Hello 美女!");
	}
}
