package cn.edu.pzhu.cg.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

/*
 * 转换流:InputStreamReader   OutputStreamWriter
 * 编码:字符串 --> 字节数组
 * 解码:字节数组 --> 字符串
 */
public class TestOtherStream {

	@Test
	public void test2(){
		System.getProperties().list(System.out);
	}
	@Test
	public void test() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			// 编码
			File file = new File("集合类.txt");
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			br = new BufferedReader(isr);

			// 解码
			File file2 = new File("集合类4.txt");
			FileOutputStream fos = new FileOutputStream(file2);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			String str = null;
			while ((str = br.readLine()) != null) {
				// System.out.println(str); //输出内容
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
