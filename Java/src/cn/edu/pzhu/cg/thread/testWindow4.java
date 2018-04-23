package cn.edu.pzhu.cg.thread;
/*
 * 同步方法:将操作共享数据的方法声明为synchronized。即此方法为同步方法，能够保证当其中一个线程执行此方法时
 * 		其他线程在外等待直至此线程执行完该方法
 */
public class testWindow4 {
	public static void main(String[] args) {
		Window4 w = new Window4();
		
		Thread w1 = new Thread(w,"窗口一");
		Thread w2= new Thread(w,"窗口二");
		Thread w3 = new Thread(w,"窗口三");
		
		w1.start();
		w2.start();
		w3.start();
		
	}
}
class Window4 implements Runnable{
	int TICKET = 100;
	public void run(){
		while(true){
			show();
		}
	}
	public synchronized void show(){
		if(TICKET > 0){
			try {
				Thread.currentThread().sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "售票，票号为:" + TICKET--);
		}
	}
}