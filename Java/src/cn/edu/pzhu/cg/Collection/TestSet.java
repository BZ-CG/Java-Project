package cn.edu.pzhu.cg.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestSet {
	
	/*
	 * TreeSet:
	 * 		1.向TreeSet添加的元素必须是同一个类的.
	 * 		2.可以按照添加进集合的指定顺序遍历，比如，string，包装类，默认从小到大排序.
	 * 		3.当向TreeSet中添加自定义类时，有两种排序方法：
	 * 			1).自然排序：要求定义的类实现java.lang.ComparableTo(Object obj)接口并重写compareTo()抽象方法.
	 * 			2).定制排序：
	 * 	    4.向TreeSet中添加元素时首先按照compareTo()进行比较，一旦返回0，虽然仅是两个对象的该属性值相同，但是程序会认为这两个对象是相同的
	 * 		  进而，后一个对象不能添加进来.
	 * 
	 */	
	//TreeSet 的定制排序
	@Test
	public void treeSet2(){
		//创建一个实现comparator接口的类对象
		Comparator com = new Comparator(){
			//向TreeSet中添加Student对象，在此compare中指明按照Student哪个属性排序
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Student && o2 instanceof Student){
					Student s1 = (Student)o1;
					Student s2 = (Student)o2;
					int i = s1.getAge().compareTo(s2.getAge());
					//当age相同时，按照name排序
					if(i == 0){
						return s1.getName().compareTo(s2.getName());
					}
					return i ;
				}
				return 	0;
			}
			
		};
		//将此对象作为形参传给 TreeSet 构造器
		TreeSet set = new TreeSet(com);
		//添加元素
		set.add(new Student("AA",15));
		set.add(new Student("DD",52));
		set.add(new Student("GG",15));
		set.add(new Student("MM",22));
		for (Object i : set) {
			System.out.println(i);
		}
		
	}
	//自然排序
	@Test
	public void test3(){
		Set set = new TreeSet();
//		set.add(123);//不可重复
//		set.add(123);
//		set.add(234);
//		//set.add("ZhangChao");
//		set.add(111);
		//当Perpon类中没有实现Comparable接口时，向TreeSet添加对象时会报错.
		set.add(new Perpon("GG",21));
		set.add(new Perpon("MM",20));
		set.add(new Perpon("AA",19));
		set.add(new Perpon("DD",18));
		set.add(new Perpon("ZZ",25));
		set.add(new Perpon("ZZ",21));
		for (Object i : set) {
			System.out.println(i);
		}
	}
	/*
	 * LinkerHashSet:
	 * 		1.使用链表维护了我们添加进集合中的顺序，导致我们遍历集合元素时是按照添加进去的顺序遍历的!
	 * 		2.也是按照hashCode值来决定元素的储存位置的，同时维护了元素的次序.
	 *      3.LinkerHashSet的性能略低于hashSet,但在迭代访问set里面的元素时却有很好的性能.
	 *      4.同样LinkerHashSet里面不允许有重复的元素.
	 * 		
	 */
	@Test
	public void test2(){
		Set set = new LinkedHashSet();
		set.add(123);
		set.add(234);
		set.add("ZhangChao");
		set.add(null);
		//set.add(123);
		Iterator it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	/*
	 * HashSet:
	 * 		1.无序：无序 != 随机，指的是元素在底层储存顺序是无序的.
	 * 		2.不可重复：当像set添加重复元素时，后面的元素不能添加进去.
	 * 		3.要求添加进 set元素所在类,必须重写equals和hashcode方法.
	 * 
	 */
	@Test
	public void test1(){
		Set set = new HashSet();
		set.add(123);
		set.add(234);
		set.add("ZhangChao");
		set.add(null);
		set.add(123);
		Perpon p1 = new Perpon("GG",21);
		//Perpon p2 = new Perpon("MM",20);
		Perpon p2 = new Perpon("GG",21);
		//如果没重写equals和hashcode()这里将是两个不同的对象，因为他们地址不同，所以都能加入到set
		//如果重写的equals和hashcode()方法，将会判定这两个是同一个对象
		System.out.println(p1.equals(p2));
		System.out.println("p1:"+p1.hashCode());
		System.out.println("p2:"+p2.hashCode());
		
		set.add(p1);
		set.add(p2);
		System.out.println(set.size());
		System.out.println(set);
	}
}
