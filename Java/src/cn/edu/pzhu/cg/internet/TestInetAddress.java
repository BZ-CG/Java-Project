package cn.edu.pzhu.cg.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;
/*
 * InetAddress:位于java.net包下
 * 1.InetAddress用来代表IP地址，一个InetAddress的对象就代表一个IP地址
 * 2.如何创建InetAddress的对象：getByName(String host)
 * 3.getHostName():获取IP地址对应的域名
 *   getHostAddress():获取IP地址
 */
public class TestInetAddress {

	public static void main(String[] args) throws Exception {
		InetAddress inet = InetAddress.getByName("www.pzhu.com");
		System.out.println(inet);
		
		System.out.println(inet.getHostName());
		System.out.println(inet.getHostAddress());
		
		InetAddress inet1 = InetAddress.getLocalHost();
		System.out.println(inet1.getHostName());
		System.out.println(inet1.getHostAddress());
	}

}
