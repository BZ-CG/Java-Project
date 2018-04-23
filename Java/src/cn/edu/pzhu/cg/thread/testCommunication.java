package cn.edu.pzhu.cg.thread;
/*
 * 线程通信:如下的三个关键字使用的话，都得在同步代码块或同步方法中。
 * 	wait():线程的等待，一旦一个线程执行到wait()，就释放当前的锁。
 * 	notify()/notifyAll():唤醒wait的一个或所有的线程 
 * 	
 * 使用两个打印1-100，线程1、2，交替打印。
 */
class PrintNum implements Runnable{
	int num = 1;
	
	public void run(){
		while(true){
			synchronized (this) {
				notify();//唤醒线程
				
				if (num <= 100) {
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + num++);
				} else {
					break;
				}
				
				
				try {
					wait();		//等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
public class testCommunication {

	public static void main(String[] args) {
		PrintNum p1 = new PrintNum();
		Thread t1 = new Thread(p1, "打印机甲");
		Thread t2 = new Thread(p1, "打印机乙");
		
		t1.start();
		t2.start();
	}

}
