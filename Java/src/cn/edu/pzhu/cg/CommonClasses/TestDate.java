package cn.edu.pzhu.cg.CommonClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cn.edu.pzhu.cg.thread.testThread2;

/*
 * 与时间相关的类
 * 1.System 类下的 currentTimeMillis();
 * 2.Date类:java.util.Date 以及其子类 java.sql.Date
 * 3.SimpleDateFormat类
 * 4.Calendar 类
 */
public class TestDate {

	//日历类
	@Test
	public void test4(){
		Calendar c = Calendar.getInstance();
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
		
		c.add(Calendar.DAY_OF_MONTH, -1);
		day = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
		
		c.set(Calendar.DAY_OF_MONTH, 25);
		Date d = c.getTime();
		System.out.println(d);
	}
	/*
	 * "三天打渔，两天晒网"  "1990-01-01" XXXX-XX-XX 打渔？晒网？
	 */
	public int getTime(String date1,String date2) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdf.parse(date1);
		Date d2 = sdf.parse(date2);
		long millTime = d2.getTime() - d1.getTime();
		
		long time = millTime/1000/3600/24;
		//double time = Math.ceil((double)millTime/1000/3600/24);
		//System.out.println("^_^"+millTime/1000/3600/24);
		return  (int) time+1;//time=0表示第一天，所以这里应该加一才对
	}
	@Test
	public void test3() throws ParseException{
		String date1 = "1990-01-01";
		String date2 = "1990-01-0";
		for(int i = 1;i<=9;i++){
			//date2 = date2 + i+"";
			//System.out.println(date2+i);
			int temp = getTime(date1, date2+i);
			if(temp % 5 == 0 || temp % 5 == 4){
				System.out.println(i+":晒网");
			}else{
				System.out.println(i+":打渔");
			}
			
		}
		
	}
	
	/*
	 * 利于国际化:日期-->文本
	 * 	    解析:文本-->日期
	 */
	@Test
	public void test2() throws ParseException{
		//默认格式化
		SimpleDateFormat sdf = new SimpleDateFormat();
		String date = sdf.format(new Date());
		System.out.println(date); //17-11-18 下午11:45
		//指定格式化
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = sdf2.format(new Date());
		System.out.println(date2);//2017-11-18 23:52:28 
		
		sdf2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		date2 = sdf2.format(new Date());
		System.out.println(date2);//星期六, 18 十一月 2017 23:57:07 +0800
		
		//解析
		Date date3 = sdf.parse("17-11-18 下午11:45");
		/*
		 * 传入的参数必须和当前对象的构造器一致 
		 * 错误写法：
		 * 		sdf.parse("EEE, d MMM yyyy HH:mm:ss Z")
		 */
		System.out.println(date3);//Sat Nov 18 23:45:00 CST 2017
		
		date3 = sdf2.parse("星期六, 18 十一月 2017 23:57:07 +0800");
		System.out.println(date3);//Sat Nov 18 23:57:07 CST 2017
		
		
	}
	@Test
	public void test1(){
		Date d1 = new Date();
		System.out.println(d1);//Sat Nov 18 23:36:50 CST 2017
		System.out.println(d1.getTime());//1511019410086
		
		Date d2 = new Date(1511019410086L);
		System.out.println(d2);//Sat Nov 18 23:36:50 CST 2017
		
		//SQL中的日期类，传入一个long类型的值，返回一个日期，如：2017-11-18
		java.sql.Date d3 = new java.sql.Date(1511019374333L);
		System.out.println(d3);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
