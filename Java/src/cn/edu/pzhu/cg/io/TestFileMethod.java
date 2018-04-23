package cn.edu.pzhu.cg.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

/*1.File:
* 	mkdir():创建一个目录，并且父目录必须存在。
* 	mkdirs():创建一个目录，如果父目录不存在就一并创建。
*2. FileInputStream,FileOutputStream 字节流
*3. FileReader() FileWriter() 处理文本文件的字符流,可以实现文本文件的复制，对于非文本文件（视频文件，图片文件等）只能使用字节流!
*/
public class TestFileMethod {

	// 从一个文件写入数据到另一个文件
	@Test
	public void test6() {
		File file1 = new File("hello.txt");
		File file2 = new File("hello2.txt");

		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(file1);
			out = new FileOutputStream(file2);
			byte[] bs = new byte[20];
			int len;
			while ((len = in.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	//FileReader() FileWriter() 处理文本文件的字符流
	@Test
	public void test5() throws IOException {
		//File file = new File("D:" + File.separator + "test" + File.separator + "test2.txt");
		File file = new File("test.txt");
		File file2 = new File("testCopy.txt");
	
			Reader read = null;
			Writer writer = null;
			try {
				read = new FileReader(file);
				writer = new FileWriter(file2);
				
				char[] data = new char[1024];
				int len;
				// System.out.println("读取的文件类容是:"+new String(data,0,len));
				while ((len = read.read(data)) != -1) {
					writer.write(data, 0, len);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				read.close();
				writer.close();
			}
			
		

	}

	@Test
	public void test4() throws IOException {
		File file = new File("D:" + File.separator + "test" + File.separator + "test.txt");
		if (!file.getParentFile().exists()) { // 如果父目录不存在
			file.getParentFile().mkdirs(); // 创建父目录
		}
		InputStream in = new FileInputStream(file);
		byte[] b = new byte[1024];
		int len = in.read(b);
		in.close();
		System.out.println("读取的数据是:" + new String(b, 0, len));
	}

	// FileOutputStream(file,true) 数据的追加
	@Test
	public void test3() throws IOException {
		File file = new File("D:" + File.separator + "test" + File.separator + "test.txt");
		if (!file.getParentFile().exists()) { // 如果父目录不存在
			file.getParentFile().mkdirs(); // 创建父目录
		}
		OutputStream out = new FileOutputStream(file, true);// 数据的追加，将追加到源文件的末尾
		String data = "\r\nHello Word."; // '\r\n' 换行
		out.write(data.getBytes());
		out.close();
	}

	// FileOutputStream(File file) 用于新建数据
	@Test
	public void test2() throws IOException {
		File file = new File("D:" + File.separator + "test" + File.separator + "test.txt");
		if (!file.getParentFile().exists()) { // 如果父目录不存在
			file.getParentFile().mkdirs(); // 创建父目录
		}
		OutputStream out = new FileOutputStream(file);
		String data = "this is txt.";
		byte[] b = data.getBytes();
		for (byte c : b) {
			System.out.println((char) c);
		}
		out.write(data.getBytes());
		out.close();
	}

	// 文件类的基本操作
	@Test
	public void test1() throws IOException {
		File file = new File("D:\\workspace\\Java\\test.txt");
		if (file.exists()) {
			System.out.println("文件名称:" + file.getName());
			System.out.println(file.getName() + (file.isDirectory() ? "是一个目录。" : "不是一个目录"));
			System.out.println(file.getName() + (file.isFile() ? "是一个文件。" : "不是一个文件"));
			System.out.println(file.getName() + (file.isHidden() ? "是一个隐藏文件。" : "不是一个隐藏文件"));
			System.out.println(
					"最后一次修改日期:" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date(file.lastModified())));
			System.out.println("文件大小:" + new BigDecimal(file.length() / (double) 1024 / 1024)
					.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP).doubleValue() + "M");
			System.out.println("字节:" + file.length());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getCanonicalPath());
			System.out.println(file.getPath());
		}

	}
}
