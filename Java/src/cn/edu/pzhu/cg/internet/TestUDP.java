package cn.edu.pzhu.cg.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;

import org.junit.Test;
//UDP编程的实现
public class TestUDP {

	//发送端
	@Test
	public void send(){
		DatagramSocket dataSock = null;
		try {
			dataSock = new DatagramSocket();
			byte[] b = "你好，我是要发送的信息!".getBytes();
			//创建一个数据报，每个数据报不能超过64k，都记录着数据信息，发送端的IP,端口号，以及要发送到的接收端的IP和端口号。
			DatagramPacket dataPack = new DatagramPacket(b, 0, b.length, InetAddress.getByName("127.0.0.1"),9090);
			dataSock.send(dataPack);
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dataSock != null)
				dataSock.close();
		}
	}
	//接收端
	@Test
	public void receive(){
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(9090);
			byte[] b = new byte[1024];
			DatagramPacket packet = new DatagramPacket(b, 0, b.length);
			datagramSocket.receive(packet);
			
			String msg = new String(packet.getData(), 0, packet.getLength());
			System.out.println(msg);
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(datagramSocket != null){
				datagramSocket.close();
			}
		}
	}
}
