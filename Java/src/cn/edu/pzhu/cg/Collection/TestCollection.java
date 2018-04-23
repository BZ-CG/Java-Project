package cn.edu.pzhu.cg.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class TestCollection {

	//增强 for 循环遍历数组
	@Test
	public void testColltion2(){
		String[] string = new String[]{"AA","BB","CC"};
		for (String str : string) {
			//这里相当于值传递，在这里修改 str 的值并不会改变数组里面的值
			System.out.println(str);
		}
	}
	@Test
	public void testCollection(){
		Collection coll = new ArrayList();
		coll.add("AA");
		//contains 判断集合中是否包含指定object对象，判断依据是根据equals()方法的。
		boolean b1 = coll.contains("AA");
		System.out.println("b1 "+b1);
		Perpon perpon = new Perpon("张超", 21);
		coll.add(perpon);
		boolean b2 = coll.contains(perpon);	//true
		System.out.println("b2 "+b1);
		//System.out.println(coll.contains(new Perpon("张超", 21))); //false 
		//因为两个对象地址不同，不能判定为同一个对象，所以为false
		//重写Perpon 中的equals 方法后：
		System.out.println("# "+coll.contains(new Perpon("张超", 21)));//true
		Iterator it = coll.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("----");
		for (Object object : coll) {
			System.out.println(object);
		}
	}
}
