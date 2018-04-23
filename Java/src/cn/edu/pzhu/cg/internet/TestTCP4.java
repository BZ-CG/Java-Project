package cn.edu.pzhu.cg.internet;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.junit.Test;

public class TestTCP4 {
//客户端向服务端发送一段字符文本，服务端将其转化为大写字符，然后将其返回给客户端
	
	@Test
	public void client() throws Exception{
		Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
		OutputStream os = socket.getOutputStream();
		byte[] b = new byte[10];
		System.out.println("请输入一段由字母组成的字符:");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		os.write(str.getBytes());
		socket.shutdownOutput();
		
		InputStream is = socket.getInputStream();
		byte[] b1 = new byte[10];
		int len1;
		while((len1 = is.read(b1)) != -1){
			String str1 = new String(b1, 0, len1);
			System.out.print(str1);
		}
		
		is.close();
		sc.close();
		os.close();
		socket.close();
	}
	@Test
	public void server()throws Exception{
		ServerSocket serverSocket = new ServerSocket(9090);
		Socket socket = serverSocket.accept();
		InputStream is = socket.getInputStream();
		byte[] b = new byte[10];
		int len;
		String str1 = "";
		while((len = is.read(b)) != -1){
			String str = new String(b, 0, len);
			str1 += str;
		}
		String strUpperCase = str1.toUpperCase();
		OutputStream os = socket.getOutputStream();
		os.write(strUpperCase.getBytes());
		
		os.close();
		is.close();
		socket.close();
		serverSocket.close();
	}
}
