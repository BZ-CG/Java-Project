package cn.edu.pzhu.biginteger;

import java.math.BigDecimal;
import org.junit.Test;
/*
 * BigDecimal:大小数操作类
 * divide(BigDecimal bd1,int scale,int roundingMode)
 * 		bd1:被除数
 * 		scale:保留小数的位数
 * 		roundingMode:进位模式
 * 				ROUND_HALF_UP:向上进位
 */
public class testBigDecimal {

	@Test
	public void test1(){
		BigDecimal bi1 = new BigDecimal("168.996754");
		BigDecimal bi2 = new BigDecimal("1");
		
		//System.out.println(bi1.divide(bi2, 5,BigDecimal.ROUND_HALF_UP));
		System.out.println(bi1.divide(bi2, 0,BigDecimal.ROUND_HALF_UP));
		
		BigDecimal bi3 = new BigDecimal("16");
		BigDecimal bi4 = new BigDecimal("5");
		
		System.out.println(bi3.divide(bi4, 5,BigDecimal.ROUND_HALF_UP));
		
	}
}
