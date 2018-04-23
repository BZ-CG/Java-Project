package cn.edu.pzhu.cg.annotation;
/*
 * 注解:
 * 		1.JDK 提供的常用的注解:
 * 			@Override:限定重写的父类方法，该注释只能用于方法
 * 			@Deprecated:用于表示某个程序元素(类，方法等)已过时
 * 			@SuppressWarnings:抑制编译器警告
 * 	    2.自定义注解:一般用户注释源代码，用的比较少
 * 		3.元注解:元注解的作用就是负责注解其他注解
 * 			(1).@Target,
		　　　(2).@Retention,
		　　　(3).@Documented,
		　　　(4).@Inherited
 */
public class TestAnnotation {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Student stu = new Student();
		
		@SuppressWarnings("unused")
		int i = 10;
		//System.out.println(i);
		
	}
}
class Student extends Perpon{
	@Override
	public void walk(){
		System.out.println("学生，走路。");
	}
	
	@Deprecated
	public void eat(){
		System.out.println("学生，吃饭。");
	}
}

class Perpon{
	String name;
	Integer age;
	
	public Perpon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Perpon(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public void walk(){
		System.out.println("走路。");
	}
	
	@MyAnnotation(value = "这是父类中的一个方法") //自定义的注解
	public void eat(){
		System.out.println("吃饭。");
	}
	
}
