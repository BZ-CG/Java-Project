package cn.edu.pzhu.cg.reflection;

import java.nio.file.WatchEvent.Modifier;

import cn.edu.pzhu.cg.annotation.MyAnnotation;

@Myannotation
public class Person extends Father<String> implements MyInterface,MyInterface2{
	public String name;
	private int age;
	String sex; 
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("我是Person无参的构造器");
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	private Person(String name, int age,String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	private void privateMethod() throws Exception,ClassNotFoundException{
		System.out.println("这是一个私有的方法");
	}
	public void testException() throws Exception,ArrayIndexOutOfBoundsException{
		
	}
	public void show(){
		System.out.println("我是一个人!");
	}
	
	@Myannotation
	@Deprecated
	public void display(String nation){
		System.out.println("我的国籍是:" + nation);
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex="+sex+"]";
	}
	
	public static void info(){
		System.out.println("我是Person中静态的方法!");
	}
	private String testPrivate(String name){
		String msg = name + "你好，我是一个带参数的 private 函数.";
		return msg;
	}
}

