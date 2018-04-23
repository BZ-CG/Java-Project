package cn.edu.pzhu.cg;

public class StringMethod {

	public static void main(String[] args) {
		String str = "helloword";
		String str2 = "123456";
		String str3 = "HELLOWORD";
		char date[] = str.toCharArray();
		for(int i = 0;i < date.length;i++)
			date[i] -= 32;
		//System.out.println("转换后的字符串:"+new String(date));
		/*System.out.println(isNumber(str));
		System.out.println(isNumber(str2));
		System.out.println(str3.compareTo(str));
		System.out.println(str2.compareTo(str));*/
		/*
		System.out.println(str.contains("ll"));
		System.out.println(str.indexOf("ll"));
		System.out.println(str.endsWith("rd"));
		System.out.println(str.startsWith("hs"));*/
		
		System.out.println(str.toUpperCase());
		System.out.println(str2.toLowerCase());
	}
	public static boolean isNumber(String temp) {
		char date[] = temp.toCharArray();
		for(int i = 0;i < date.length;i++){
			if(date[i] < '0' || date[i] > '9')
				return false;
			
		}
		return true;
	}
	
}
