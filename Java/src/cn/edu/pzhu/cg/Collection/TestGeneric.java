package cn.edu.pzhu.cg.Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class TestGeneric {

	/*
	 * 通配符的使用
	 */
	@Test
	public void test1(){
		List<String> list = new ArrayList<>();
		list.add("AA");
		list.add("bb");
		List<?>  list2 = list;
		//可以读取声明为通配符的集合类的对象
		Iterator<?> iterator = list2.iterator();
		for (Object obj : list2) {
			System.out.println(obj);
		}
		//不能向声明为通配符的集合类中写入对象,唯一例外是null
		//list2.add("CC");
		list2.add(null);
		
	}
}
