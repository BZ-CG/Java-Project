package cn.edu.pzhu.cg.reflection;

public class Father<String> {
	public double height;
	public void breath(){
		System.out.println("呼吸");
	}
	private void privateMethod(){
		System.out.println("这是父类的一个私有的方法");
	}
}
