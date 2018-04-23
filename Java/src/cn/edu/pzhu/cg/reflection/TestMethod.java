package cn.edu.pzhu.cg.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class TestMethod {

	//获取运行时类的方法
	@Test
	public void test1(){
		Class clazz = Person.class;
		//1.getMethods():获取当前运行时类和其父类的所有public方法
		Method[] methods = clazz.getMethods();
		for(Method m:methods){
			System.out.println(m);
		}
		System.out.println("----");
		//2.getDeclaredMethods():获取当前运行时类申明的方法	
		Method[] methods2 = clazz.getDeclaredMethods();
		for(Method m:methods2){
			System.out.println(m);
		}
	}
	//获取注解   权限修饰符   方法类型   方法名称  参数列表   异常
	@Test
	public void test2(){
		Class clazz = Person.class;
		Method[] methods = clazz.getMethods();
		for(Method m:methods){
			//1.获取注解
			Annotation[] ann = m.getAnnotations();
			for(Annotation an:ann){
				System.out.println(an);
			}
			
			//2.获取权限修饰符
			System.out.print(Modifier.toString(m.getModifiers()) + "   ");
			
			//3.获取方法类型
			Class type = m.getReturnType();
			System.out.print(type + "   ");
			
			//4.获取方法名称
			System.out.print(m.getName() + "   ");
			
			//5.获取参数列表
			Class[] par = m.getParameterTypes();
			System.out.print("(");
			for(Class p:par){
				System.out.print(p + " ");
			}
			System.out.print(")");
			
			//6.获取异常
			Class[] exClasses = m.getExceptionTypes();
			if(exClasses.length != 0){
				System.out.print("throw");
			}
			for(Class e:exClasses){
				System.out.print(e);
			}
			
			System.out.println();
			System.out.println();
		}
		
		
		
		
	}
	
	
	//调用运行时类中指定的方法
	@Test
	public void test3() throws Exception{
		Class clazz = Person.class;
		//getMethod(String methodName,Class ... params):调用声明为public 的方法
		Method m1 = clazz.getMethod("show");
		Person p = (Person) clazz.newInstance();
		Object object = m1.invoke(p);//第一个参数:调用运行时类对象；第二个参数:方法 m1 的形参
		System.out.println(object);
		System.out.println("--------");
		
		Method m2 = clazz.getMethod("toString");
		Object object2 = m2.invoke(p);
		System.out.println(object2);
		System.out.println("--------");
		
		//对于静态方法可以这样调用
		Method m3 = clazz.getMethod("info");
		m3.invoke(Person.class);
		System.out.println("--------");
		
		Method m4 = clazz.getDeclaredMethod("testPrivate",String.class);
		m4.setAccessible(true);//这里testPrivate是私有的方法需要先设置可访问
		Object object3 = m4.invoke(p, "张超");
		System.out.println(object3);					
	}
	
	//调用指定的构造器
	@Test
	public void test4() throws Exception{
		Class clazz = Person.class;
		Constructor con = clazz.getDeclaredConstructor(String.class,int.class);
		con.setAccessible(true);
		Person p = (Person)con.newInstance("张超",21);
		System.out.println(p);
		
	}
	
}
