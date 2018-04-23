package cn.edu.pzhu.cg;

public class B extends A {

	public B(){
		System.out.println("这是B的构造方法");
	}
//	public void pri(){
//		System.out.println("B-pri()");
//	}
	public static void main(String[] args) {
		B b =  new B();
		b.pri();
		System.out.println("-------");
		
		A a = new B();   //向上转型，自动。
		a.pri();
		System.out.println("-------");
		
		//向下转型前，应当先向上转型，获得父类对象，在向下转型变成子类对象实例
		A a2 = new B();
		B b2 = (B) a2;
		b2.pri();
		
	} 
/*	public void pri(){//父类中final定义的方法，子类不能覆写
		
	}*/
}
