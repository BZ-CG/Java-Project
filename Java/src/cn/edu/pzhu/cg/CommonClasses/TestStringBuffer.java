package cn.edu.pzhu.cg.CommonClasses;

import org.junit.Test;

/*
 * java.lang.StringBuffer:可变字符序列，可以对字符序列内容进行增删
 * 	>线程安全
 * 	>效率相对低
 * java.lang.StringBuilder:可变字符序列
 * 	>线程不安全
 *  >效率相对高
 * 
 * 
 * 
 * 添加:append();
 * 插入:public StringBuffer insert(int index,String str)
 * 反转:public StringBuffer reverse()
 * 删除:StringBuffer delete(int startIndex,int endIndex)   左闭右开
 * 修改:public void setCharAt(int index,char ch)
 * 取代:StringBuffer replace(int startIndex,int endIndex,String str)   左闭右开
 * 剪切:public String substring(int start,int end) 		左闭右开
 */
public class TestStringBuffer {

	@Test
	public  void test(){
		StringBuffer s = new StringBuffer();
		s.append("我是张超").append("，长得也不错.");
		s.insert(1, "Hello");
		System.out.println(s);
		
		StringBuffer s2 = s.reverse();
		System.out.println(s2);
		System.out.println(s);//原来的StringBuffer被改变了
		
		StringBuffer s3 = new StringBuffer("123456");
		s3.delete(3, 5);  //左闭右开
		System.out.println(s3);

		StringBuffer s4 = new StringBuffer("123456");
		s4.setCharAt(3, '7');  //左闭右开
		System.out.println(s4);

	}
	@Test
	public void test2(){
		StringBuffer s = new StringBuffer("AAABBBCCC");
		s.replace(0,3,"1253");  //左闭右开
		System.out.println(s);
		
		StringBuffer s1 = new StringBuffer("AAABBBCCC");
		String s2 = s1.substring(0, 3);		//左闭右开
		System.out.println(s2);
		
		
	}
	
	
	public static void main(String[] args) {
		/*
		 * 对比StringBuffer StringBuild String 三者在添加上的效率
		 * 		StringBuild > StringBuffer > String
		 */
		String text = "";
		long startTime = 0L;
		long endTime = 0L;
		StringBuffer buffer = new StringBuffer("");
		StringBuilder builder = new StringBuilder("");
		
		startTime = System.currentTimeMillis();
		for(int i = 1;i <= 50000;i++)
			buffer.append(i);
		endTime = System.currentTimeMillis();
		System.out.println("StringBuffer 所用的时间是:" + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		for(int i = 1;i <= 50000;i++)
			builder.append(i);
		endTime = System.currentTimeMillis();
		System.out.println("StringBuilder 所用的时间是:" + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		for(int i = 1;i <= 50000;i++)
			text += i + "";
		endTime = System.currentTimeMillis();
		System.out.println("String 所用的时间是:" + (endTime - startTime));
		
	}

}
