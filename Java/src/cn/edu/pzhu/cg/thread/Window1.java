package cn.edu.pzhu.cg.thread;

public class Window1 {

	public static void main(String[] args) {
		Window2 w =new Window2();
		Thread t1 = new Thread(w,"窗口一");
		Thread t2 = new Thread(w,"窗口二");
		Thread t3 = new Thread(w,"窗口三");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
class Window2 implements Runnable{
	int TICKET = 100;
	public void run(){
		while(true){
			synchronized(this){ //同步代码块
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