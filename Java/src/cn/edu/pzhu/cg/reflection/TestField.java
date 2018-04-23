package cn.edu.pzhu.cg.reflection;

import java.awt.Window.Type;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class TestField {

	//获取运行时类的属性
	@Test
	public void test1(){
		Class clazz = Person.class;
		//1.getFields():获取运行时类和棋父类中的public属性
		Field[] field1 = clazz.getFields();
		for(int i = 0;i < field1.length;i++)
			System.out.println(field1[i]);
		
		//2.getDeclaredFields():只能获取运行时类本身的所有的属性
		Field[] field2 = clazz.getDeclaredFields();
		for(Field f:field2){
			System.out.println(f.getName());
		}
	}
	//权限修饰符  变量类型 变量名
	@Test
	public void test2(){
		Class clazz = Person.class;
		Field[] field2 = clazz.getDeclaredFields();
		for(Field f:field2){
			//1.权限修饰符
			int m = f.getModifiers();
			String modifiers = Modifier.toString(m);
			System.out.print(modifiers + "  ");
			
			//2.属性的类型
			Class type = f.getType();
			System.out.print(type + "  ");
			//3.属性名
			System.out.println(f.getName());
		}
	}
	
	/*
	 * 获取运行时类的属性
	 * 		1.getField(String fieldName):获取 public 属性，可直接赋值。
	 * 		2.getDeclaredField(String fieldName)
	 * 			1).可以获取所有的属性
	 * 			2).当属性权限修饰符为 private 时，需要先设置可访问 setAccessible(true)，才能赋值。
	 * 			3).当属性权限修饰符为缺省时，可直接赋值。
	 */
	@Test
	public void test3() throws Exception{
		Class clazz = Person.class;
		//1.获取指定属性
		Field name = clazz.getField("name");
		
		//2.创建运行时类
		Person p = (Person) clazz.newInstance();
		System.out.println(p);
		//p.setAge(12);
		//System.out.println(p);
		
		//3.给运行时类的指定属性赋值
		name.set(p, "Tom");
		
		
		Field age = clazz.getDeclaredField("age");//可以获取所有声明的属性	
		age.setAccessible(true);//当属性权限是 private 时，需要设置可访问
		age.set(p, 18);
		System.out.println(p);
		
		//缺省修饰符属性
		Field sex = clazz.getDeclaredField("sex");
		sex.set(p, "女");
		System.out.println(p);
	}
}
