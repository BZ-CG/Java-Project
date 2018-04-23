package cn.edu.pzhu.cg.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

public class TestOther {

	//6.获取注解
	@Test
	public void test6(){
		Class clazz = Person.class;
		Annotation[] ann = clazz.getAnnotations();
		for(Annotation an:ann){
			System.out.println(an);
		}
	}
	//5.获取所在的包
	@Test
	public void test5(){
		Class clazz = Person.class;
		Package package1 = clazz.getPackage();
		System.out.println(package1.getName());
		
	}
	//4.获取实现的接口
	@Test
	public void test4(){
		Class clazz = Person.class;
		Class[] inter = clazz.getInterfaces();
		for(Class c:inter){
			System.out.println(c);
		}
	}
	//3.获取父类的泛型
	@Test
	public void test3(){
		Class clazz = Person.class;
		Type type = clazz.getGenericSuperclass();
		
		ParameterizedType param = (ParameterizedType)type;
		Type[] types = param.getActualTypeArguments();
		
		for(Type t:types){
			System.out.println(((Class)t).getName());
		}
	}
	//2.获取带泛型的运行时类的父类
	@Test
	public void test2(){
		Class clazz = Person.class;
		Type type = clazz.getGenericSuperclass();
		System.out.println(type);
	}
	//1.获取运行时类的父类
	@Test
	public void test1(){
		Class clazz = Person.class;
		Class superClass = clazz.getSuperclass();
		System.out.println(superClass);
	}
}
