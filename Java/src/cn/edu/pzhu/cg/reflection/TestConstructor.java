package cn.edu.pzhu.cg.reflection;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class TestConstructor {

	@Test
	public void test1() throws Exception{
		String className = "cn.edu.pzhu.cg.reflection.Person";
		Class clazz = Class.forName(className);
		//1.创建对应的运行时类的对象，使用newInstance()，实际上是调用了运行时类的无参构造器
		/*2.要想能成功创建，必须满足一下两个条件：
				①.对应的运行时类必须要有无参构造器
				②.构造器的权限修饰符要足够
		*/
		Object object = clazz.newInstance();
		Person person = (Person)object;
		
		System.out.println(person);
		
	}
	@Test
	public void test2(){
		Class clazz = Person.class;
		Constructor[] con = clazz.getConstructors();
		for(Constructor c:con){
			System.out.println(c);
		}
		System.out.println("---------");
		
		Constructor[] con2 = clazz.getDeclaredConstructors();
		for(Constructor c:con2){
			System.out.println(c);
		}
	}
}
