package cn.edu.pzhu.cg.thread;
/*
 * 继承Thrad 实现同步代码块
 */
public class testWindow2 {

	public static void main(String[] args) {
		Window3 w1 = new Window3();
		Window3 w2 = new Window3();
		Window3 w3 = new Window3();
		
		w1.setName("窗口一");
		w2.setName("窗口二");
		w3.setName("窗口三");
		w1.start();
		w2.start();
		w3.start();
	}
}
class Window3 extends Thread{
	static int TICKET = 100;
	static Object obj = new Object();
	public void run(){
		while(true){
	//		synchronized(this){ //在本问题中，this代表w1,w2,w3
			synchronized(obj){	//obj是静态的，相当于是同一个，等于同一个锁
				if(TICKET > 0){
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "售票，票号为:" + TICKET--);
				}else {
					break;
				}
			}
		}
	}
}