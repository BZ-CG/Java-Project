package cn.edu.pzhu.cg.Collection;
/*
 * Map 接口：
 * 		1.HashMap
 * 		2.Hashtable：古老的实现类，线程安全，不建议使用，但是常用他的子类Properties来处理属性文件，键和值都是String类型的.
 * 		3.TreeMap:按照添加进Map中元素的key的指定属性排序.要求,key的属性是同一个类.
 * 				  针对key排序有：自然排序和定制排序(和TreeSet一样).
 * 		4.LinkedHashMap:使用链表维护添加进Map中顺序，故遍历Map时是按照添加进去的顺序遍历的.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public class TestMap {
	
	//使用Properties处理属性文件
	@Test
	public void test2() throws FileNotFoundException, IOException{
		Properties pro = new Properties();
		pro.load(new FileInputStream(new File("db.properties")));
		String user = pro.getProperty("user");
		String password = pro.getProperty("password");
		System.out.println(user+"  "+password);
	}
	/*
	 * HashMap:
	 * 		1.key 是用set存放的不可重复，value是用collection存放的，可重复，一个key-value对，是一个Entry,所有Entry是用set存放的
	 * 		2.向HashMap 中添加元素会调用key所在类的equals()方法，，判断两个元素是否相同，后添加的元素会替换掉先前的元素
	 */
	@Test
	public void test1(){
		Map map = new HashMap();
		map.put(1, "AA");
		map.put("BB", 9);
		map.put(1, "AA");
		map.put("BB", 7);
		map.put("dd", 1);
		map.put("CC", 5);
		map.put("JJ", 1);
		map.put(new Perpon("CG",21), 21);
		//遍历key
		Set set = map.keySet();
		System.out.println("key:");
		for (Object object : set) {
			System.out.println(object);
		}
		//遍历value
		Collection col = map.values();
		System.out.println("value:");
		for (Object object : col) {
			System.out.println(object);
		}
		//遍历key-value对;
		//方式一：
		Set set1 = map.keySet();
		System.out.println("key--value对：");
		for (Object object : set1) {
			System.out.println(object+"-->"+map.get(object));
		}
		//方式二：
		Set set2 = map.entrySet();
		for (Object obj : set2) {
			Map.Entry obj1 = (Map.Entry)obj;
			System.out.println(	);
		}
	}
	
}
