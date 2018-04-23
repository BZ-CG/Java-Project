package cn.edu.pzhu.cg.Collection;
/*
 * 对Collection和Map操作的工具类：
 * 		1.reverse(list):反转集合list集合中的元素.
 * 		2.shuffle(list):对集合中的元素进行随机排序.
 * 		3.sort(list):根据元素的自然顺序对指定的list集合按元素升序排列.
 * 		4.sort(list,Comparator):根据指定的Comparator产生的顺序对指定的list集合进行排序.
 * 		5.swap(list,int,int):将指定集合 list 元素从 i 到 j 交换.
 * .
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class testCollections {

	@Test
	public void test(){
		List list = new ArrayList();
		list.add(123);
		list.add(456);
		list.add(111);
		list.add(045);
		System.out.println(list);
		
		Collections.reverse(list);
		System.out.println(list);
		Collections.shuffle(list);
		Collections.sort(list);
		System.out.println(list);
		
		Collections.swap(list, 0, 3);
		System.out.println(list);
	}
}
