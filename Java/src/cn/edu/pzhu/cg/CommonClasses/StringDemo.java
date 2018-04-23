package cn.edu.pzhu.cg.CommonClasses;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/*
 * 1.模拟一个trim()方法，去除字符串两端的空格
 * 2.将一个字符串进行反转，将一个字符串进行指定部分反转
 * 3.获取一个字符串在另一个字符串中出现的次数
 * 4.求两个字符串的最长公共子串
 */
public class StringDemo {

	public static void main(String[] args) {
		String str1 = "abcabj abkabmnbaab";
		String str2 = "ab";
		System.out.println(getTime(str1, str2));
		
		System.out.println("-----");
		String str3 = "My namse is ZhangChao";
		String str4 = "namsehhZhang";
		System.out.println(getMaxSubString(str4, str3));
		
		List<String> list = getMaxSubString2(str4, str3);
		System.out.print(list);
	}
	@Test
	public void test1(){
		System.out.println("1.-----");
		String str = "   b ad   c    ";
		System.out.println("^_^" + myTrim(str) + "^_^");
		//System.out.println(myTrim("    "));
		
		System.out.println("2.-----");
		String str1 = "123456";
		System.out.println(reverseString(str1, 1, 4));
		
		
	}
	
	// 4.求两个字符串的最长公共子串
	//str1="abcdaaaddd" 	str2="abcdlkjh"
	public static List<String> getMaxSubString2(String str1,String str2){
		List<String> list = new ArrayList<>();
		String maxStr = (str1.length() > str2.length())?str1:str2;
		String minStr = (str1.length() < str2.length())?str1:str2;
		int len = minStr.length();
		//System.out.println("len:"+len);
		for(int i = 0;i < len;i++){
			for(int x = 0,y = len - i;y <= len;x++,y++){
				String str = minStr.substring(x, y);
				//System.out.println("--" + str);
				if(maxStr.contains(str)){
					list.add(str);
				}
			}
			if(list.size() != 0){
				//System.out.println("i:"+i);
				return list;
			}
		}
		return list;
	}
	public static int getMaxSubString(String str1,String str2){
		int a[][] = new int[str1.length()][str2.length()];
		int max = 0;
		int right = 0;
		for(int i =0;i < str1.length();i++)
			a[i][0] = 0;
		
		for(int i =0;i < str2.length();i++)
			a[0][1] = 0;
		
		for(int i = 0;i < str1.length();i++)
			for(int j = 0;j < str2.length();j++){
				if(str1.charAt(i) == str2.charAt(j)){
					if(i > 0 && j > 0){
						a[i][j] = a[i-1][j-1] + 1;
					}else{
						a[i][j] = 1;
					}
					if(a[i][j] > max){
						max = a[i][j];
						right = j;
					}
				}
			}
		/*
		 * 保存最大长度时 j 的索引，再根据最大长度和末位置的索引去确定最大子串的开始位置，再进行剪接。
		 * 注意：j 对应的字符串，如果有最长子串有多个可用 List 去保存子串末位置，再按上面说的进行剪接即可。
		 */
		String temp = str2.substring(right-max+1, right+1);
		System.out.println(temp);
		return max;
	}
	
	//3.获取一个字符串在另一个字符串中出现的次数
	public static int getTime(String str1,String str2){
		int cnt = 0;
		int len;
		while((len = str1.indexOf(str2)) != -1){
			cnt++;
			str1 = str1.substring(len + str2.length());
		}
		return cnt;
	}
	
	
	//2.将一个字符串进行反转，将一个字符串进行指定部分反转
	public String reverseString(String str,int start,int end){
		char[] c = str.toCharArray();
		return reverse(c, start, end);
	}
	public String reverse(char[] c,int start,int end){
		for(int i = start,j = end;i < j;i++,j--){
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
		return new String(c);
	}
	
	
	
	
	//1.模拟一个trim()方法，去除字符串两端的空格
	public String myTrim(String str){
		int start = 0;
		int end = str.length();
		String string = null;
		while(start < end-1 && str.charAt(start) == ' '){
			start++;
		}
		while(start < end-1 && str.charAt(end - 1) == ' '){
			end--;
		}
		string = str.substring(start, end);
		
		return string;
	}
	
}
