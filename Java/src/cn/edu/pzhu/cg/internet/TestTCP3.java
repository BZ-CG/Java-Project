package cn.edu.pzhu.cg.internet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;
//TCP编程示例三：客户端发送一张图片到服务端，服务端接收到图片后给客户端发送"我已接收到你发送的图片"的信息
public class TestTCP3 {

	@Test
	public void client(){
		Socket socket = null;
		OutputStream os = null;
		FileInputStream fis = null;
		InputStream is = null;
		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			os = socket.getOutputStream();
			fis = new FileInputStream(new File("1.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = fis.read(b)) != -1){
				os.write(b, 0, len);
			}
			socket.shutdownOutput();
			is = socket.getInputStream();
			byte[] b1 = new byte[1024];
			int len1;
			while((len1 = is.read(b1)) != -1){
				String msg = new String(b1, 0, len1);
				System.out.print(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	@Test
	public void server(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream is = null;
		FileOutputStream fos = null;
		OutputStream os = null;
		try {
			serverSocket = new ServerSocket(9090);
			socket = serverSocket.accept();
			is = socket.getInputStream();
			fos = new FileOutputStream(new File("2.jpg"));
			byte[] b = new byte[1024];
			int len;
			while((len = is.read(b)) != -1){
				fos.write(b, 0, len);
			}
			os = socket.getOutputStream();
			os.write("我已接收到你发送的图片!".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
