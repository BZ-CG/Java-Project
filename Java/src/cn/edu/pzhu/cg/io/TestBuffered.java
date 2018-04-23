package cn.edu.pzhu.cg.io;
/*
 * 缓冲流操作:
 * 		BufferedInputStream
 * 		BufferedOutputStream	(flush())
 * 		BufferedReader
 * 		BufferedWriter			(flush())
 * 	可以提升文件操作的效率
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class TestBuffered {

	@Test
	public void test2(){
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			File file = new File("集合类2.txt");
			File file2 = new File("集合类3.txt");
			FileReader fr = new FileReader(file);
			FileWriter fw = new FileWriter(file2);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			String str = null;
			while((str = br.readLine()) != null){
				//System.out.println(str);	//输出一行内容
				bw.write(str);
				bw.newLine();//	换行
				bw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	@Test
	public void testFileCopy(){
		long start = System.currentTimeMillis();
		String src = "C:\\Users\\ASUS\\Desktop\\1.mp4";
		String dest = "C:\\Users\\ASUS\\Desktop\\2.mp4";
		
		copyFile(src, dest);
		long end = System.currentTimeMillis();
		System.out.println("花费的时间是:"+(double)(end - start)/1000);
	}
	//实现文件的复制
	public void copyFile(String src,String dest){
		File file1 = new File(src);
		File file2 = new File(dest);
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			FileInputStream fis = new FileInputStream(file1);
			FileOutputStream fos = new FileOutputStream(file2);
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			byte[] b = new byte[1024];
			int len;
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
				bos.flush();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	
	@Test
	public void testBufferedInputStreamOutStream(){
		File file1 = new File("hello.txt");
		File file2 = new File("hello2.txt");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			FileInputStream fis = new FileInputStream(file1);
			FileOutputStream fos = new FileOutputStream(file2);
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			byte[] b = new byte[1024];
			int len;
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
}
