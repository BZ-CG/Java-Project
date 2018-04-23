package cn.edu.pzhu.cg.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

//网络编程实际上就是Scoket编程
//TCP编程例一:客户端给服务端发送信息，服务端将收到的信息打印到控制台上
public class TestTCP1 {

	//客户端
	@Test
	public void client(){
		Socket socket = null;
		OutputStream os = null;
		try {
			//创建Socket对象，通过构造器指明IP地址和端口号
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			//获取输出流
			os = socket.getOutputStream();
			//通过输出流传送数据
			os.write("我是客户端，我在请求连接!".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//服务端
	@Test
	public void server(){
		ServerSocket serverSoc = null;
		Socket socket = null;
		InputStream is = null;
		try {
			//创建serverSoc对象，通过构造器指明端口号
			serverSoc = new ServerSocket(9090);
			//调用accept方法，获取Socket对象
			socket = serverSoc.accept();
			//通过Socket对象获取输入流，从输入流中获取从客户端发来的信息
			is = socket.getInputStream();
			byte[] b = new byte[20];
			int len;
			while((len = is.read(b)) != -1){
				String msg = new String(b,0,len);
				System.out.print(msg);
			}
			System.out.println();
			System.out.println("我是服务端，我收到了来自 " + socket.getInetAddress().getHostAddress() + " 的连接。");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(serverSoc != null){
				try {
					serverSoc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
