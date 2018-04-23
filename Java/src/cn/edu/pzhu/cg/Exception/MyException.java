package cn.edu.pzhu.cg.Exception;

//自定义异常类
//1.继承现有的异常类
//2.提供一个序列号，提供几个重载的构造器
public class MyException extends RuntimeException {

	static final long serialVersionUID = -7034845766939L;

	public MyException() {

	}

	public MyException(String msg) {
		super(msg);
	}

}
