package cn.edu.pzhu.cg.Interface;
/*
 * 接口
 * 1.接口可以看成是一个特殊的抽象方类，是常量与抽象方法的一个集合，不能有变量和一般的方法。
 * 2.接口没有构造器。
 * 3.接口定义的就是一种功能，此功能可以被类所实现（implenents）。
 * 4.实现接口的类，必须重写其中所有的抽象方法，方可实例化。
 * 5.类可以实现多个接口。----Java中继承是单继承的。
 * 6.接口与接口之间也是继承关系，而且可以实现多个继承。interface EE extends DD,AA 	
 */
public class TestInterface {
}

interface AA {
	// 常量，都用public static final修饰，可以省略修饰，但事实上是一个常量，不能被修改。
	public static final int I = 100;
	public static final boolean FLAG = true;

	int temp = 0;
	// 不能 定义变量
	// int i;

	// 抽象方法，都用 public abstract 修饰，可以省略
	public abstract void method1();

	void method2();
}

// 抽象类可以不实现接口里面的方法
abstract class BB implements AA {

}

class CC implements AA {

	@Override
	public void method1() {
		// TODO Auto-generated method stub

	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub

	}

}

interface DD {
	public void method3();
}

interface EE extends DD, AA {
	void method4();
}