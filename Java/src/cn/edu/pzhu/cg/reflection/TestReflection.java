package cn.edu.pzhu.cg.reflection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.Test;
import org.junit.experimental.theories.Theories;

public class TestReflection {

	/*
	 * java.lang.Class:是反射的源头
	 * 创建一个类，通过编译(javac.exe)，生成对应的.class文件。之后我们使用java.exe加载(JVM类加载器)
	 * 次.class 文件加载到内存以后，就是一个运行时类，存放在缓冲区。那么这个运行时类本身就是一个Class实例
	 * 1.诶一个运行时类只加载一次。
	 * 2.有了这个Class实例才可以进行如下操作:
	 * 		1) 创建对应的运行时类的对象
	 * 		2) 获取对应的运行时类的完整结构(属性、方法、构造器、内部类、父类、所在的包、异常、注解。。。。)
	 * 		3) 调用对应的运行时类的指定结构(属性、方法、构造器)
	 * 		4) 反射的应用 动态代理
	 */
	
	@Test
	public void test5() throws IOException{
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream is = loader.getResourceAsStream("cn\\edu\\pzhu\\cg\\reflection\\jdbc.properties");
		Properties properties = new Properties();
		properties.load(is);
		
		String name = properties.getProperty("user");
		String password = properties.getProperty("password");
		System.out.println("name:" + name + "  password:" + password);
		
		
	}
	//关于类的加载器:ClassLoader
	public static void main(String[] args) {
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();
		System.out.println(loader1);
		
		
	}
	
	//如何获取Class的实例
	@Test
	public void test4() throws Exception{
		//1.调用运行时类本身的.class属性
		Class clazz = Person.class;
		System.out.println(clazz.getName());
		
		//2.通过运行时类的对象获取
		Person p = new Person();
		Class clazz2 = p.getClass();
		System.out.println(clazz2.getName());
				
		//3.通过Class的静态方法获取
		String className = "cn.edu.pzhu.cg.reflection.Person";
		Class clazz3 = Class.forName(className);
		System.out.println(clazz3.getName());
		
	
	}
	
	@Test
	public void test3(){
		Person p = new Person();
		Class clazz = p.getClass();//通过运行时类的对象，调用其getClass()方法，返回其运行时类。
		System.out.println(clazz);
	}
	@Test
	public void test2() throws Exception, Exception{
		//创建c的运行时类Person类的对象
		Class<Person> c = Person.class;
		Person p = c.newInstance();//调用无参构造器
		
		System.out.println(p);
		//通过反射弧调用运行时类的属性
		//public 属性
		Field f1 = c.getField("name");
		f1.set(p, "张学友");
		System.out.println(p);
		
		//private 属性
		Field f2 = c.getDeclaredField("age");
		f2.setAccessible(true);
		f2.set(p, 50);
		System.out.println(p);
		
		//通过反射弧调用指定的方法
		Method m1 = c.getMethod("show");
		m1.invoke(p);
		
		Method m2 = c.getMethod("display",String.class);
		m2.invoke(p,"香港");
		
	}
	
	//创建一个类的对象，并调用其中的方法、属性
	@Test
	public void test1(){
		Person person = new Person();
		person.setAge(21);
		person.setName("张超");
		person.show();
		person.display("中国");
		System.out.println(person);
		
	}
}
