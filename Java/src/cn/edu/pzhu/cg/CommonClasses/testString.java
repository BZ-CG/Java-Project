package cn.edu.pzhu.cg.CommonClasses;

import org.junit.Test;
/*
 * String:代表不可变的字符序列；底层使用char[]存放
 * String是final的
 */
public class testString {

	/*
	 * float:float是单精度类型,精度是8位有效数字，取值范围是10的-38次方到10的38次方，float占用4个字节的存储空间
	 * double:double是双精度类型，精度是17位有效数字，取值范围是10的-308次方到10的308次方，double占用8个字节的存储空间
	 */
	@Test
	public void test5(){
		String str1 = "520";
		int i = Integer.parseInt(str1);
		System.out.println(i);
		double temp =  520.52052603d;
		float  temp2 = 520.5205667f;//520.52057
		String doubleString = String.valueOf(temp);
		String floatString = String.valueOf(temp2);
		
		System.out.println("double:" + doubleString);
		System.out.println("float:" + floatString);
	}
	@Test
	public void test4(){
		/*
		 *  public String[] split(String str) : 按照字符串str对当前字符串进行拆分，整体返回值是String[]
		 */
		String str1 = "a*dd*f-d*ad*156";
		//因为+、*、|、\等符号在正则表达示中有相应的不同意义，所以在使用时要进行转义处理。
		String str2 = "\\*";//或者 str2 = "[*]";
		String[] str = str1.split(str2);
		for(int i = 0;i < str.length;i++){
			System.out.println(str[i]);
		}
		System.out.println("------");
		String[] str3 = str1.split("-");
		for(String strTemp:str3){
			System.out.println(strTemp);
		}
	}
	
	@Test
	public void test3(){
		/*
		 * public String trim() : 去除当前字符串中首尾的空格，若有多个，就去除多个
		 */
		String str1 = "  a  bcc   ";
		String str2 = str1.trim();
		
		System.out.println("---" + str1 + "--");
		System.out.println("---" + str2 + "--");
		/*
		 * public String concat(String str) : 将当前字符串与str连接
		 */
		String str3 = "我是一个小伙子";
		String str4 = "、长得也还可以。";
		System.out.println(str3.concat(str4));
	}
	@Test
	public void test2(){
		/*
		 * public String substring(int start,int end) : 返回从start开始到end结束的左闭右开的字符串
		 * 		
		 */
		String str1 = "我是一个 热爱学习、并且长得也还不错的小伙子。";
		String str2 = str1.substring(0, str1.length());
		String str3 = "0123456789";
		String str4 = str3.substring(0,7);
		System.out.println(str2);
		System.out.println(str4);
	}
	@Test
	public void test1(){
		String str1 = "JavaEE";
		String str2 = "JavaEE";
		String str3 = new String("JavaEE");
		String str4 = "JavaEE" + "Android";
		String str5 = "Android";
		String str6 = str1 + str5;
		str5 = str5 + "Handoop";
		String str7 = str6.intern();
		String str8 = "JavaEEAndroid";
		
		
		System.out.println(str1 == str2);//true
		System.out.println(str1 == str3);//false
		System.out.println(str1.equals(str3));//true

		System.out.println(str4 == str6);//false
		System.out.println(str4 == str7);//true
		System.out.println(str4 == str8);
		
		Person p1 = new Person("AA");
		Person p2 = new Person("AA");
		System.out.println("^_^"+ (p1.name == p2.name));
		
		
		
	}
}
class Person{
	String name;
	public Person(String name) {
		this.name = name;
	}
}
