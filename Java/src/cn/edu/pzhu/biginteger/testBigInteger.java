package cn.edu.pzhu.biginteger;

import java.math.BigInteger;
import org.junit.Test;
/*
 * 大整数操作类:BigInteger
 * 		操作无限大的整形数据
 */
public class testBigInteger {

	@Test
	public void test1(){
		BigInteger bi1 = new BigInteger("234809234801"); 	//大数1
		BigInteger bi2 = new BigInteger("8939834789");		//大数2
		
		System.out.println("加法结果为:" + bi1.add(bi2));		//加法
		System.out.println("减法结果为:" + bi1.subtract(bi2));	//减法
		System.out.println("乘法结果为:" + bi1.multiply(bi2));	//乘法
		System.out.println("除法结果为:" + bi1.divide(bi2));	//除法，没有余数

		BigInteger result[] = bi1.divideAndRemainder(bi2);	//除法保留余数，数组第一个元素是商，第二个是余数
		System.out.println("商:" + result[0] + "  余数:" + result[1]);
		
		
	}
}
