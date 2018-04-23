package cn.edu.pzhu.cg.Exception;

public class ReturnExceptionDemo {

	static void methodA(){
		try {
			System.out.println("进入方法A");
			throw new RuntimeException("制造异常");//抛出异常，程序中断前先执行 fanally 里面代码
		} finally {
			System.out.println("用A方法的finally");
		}
	}
	static int methodB(){
		try {
			System.out.println("进入方法B");		//同理，retuan 1 前，执行 finally 代码
			return 1;
		} finally {
			System.out.println("用B方法的finally");
			//return 2;
		}
	}
	public static void main(String[] args) {
		try {
			methodA();
		} catch (Exception e) {		//处理 methodA() 方法抛出的异常
			System.out.println(e.getMessage());
		}
		int i = methodB();
		System.out.println(i);
	}
}
