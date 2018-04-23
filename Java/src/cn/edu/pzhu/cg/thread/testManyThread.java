package cn.edu.pzhu.cg.thread;

import org.junit.Test;
/*
 * Thread 常用的方法:
 * 	1.start() :启动线程并执行相应的run()方法
 *  2.run():子线程要执行的代码放在run()里面
 *  3.currentThread(): 静态的，点取当前的线程
 *  4.getName():获取线程的名字
 *  5.setName():设置线程的名字
 *  6.yield():调用此方法的线程释放当前CPU的执行权
 *  7.jojn():在A线程中调用B线程的此方法，表示A线程停止执行直至B执行完毕，A线程在接着执行join()之后的代码
 *  8.isAlive(): 判断当前线程是否活着
 *  9.sleep(long l):显示的让当前线程睡眠l毫秒
 *  10.线程通信:wait(),notify(),notifyAll()
 *   
 * 设置线程的优先级:
 *  getPriority():返回线程的优先级
 *  setPriority(int newPrioritty):改变线程的优先级 
 *  
 * 一个线程只能执行一次start()
 * Runnable 接口要比Thread 类更好的实现数据共享，而不是唯一。
 */
public class testManyThread {

	@Test
	public void test6(){
		MyThread5 mt5 = new MyThread5();
		mt5.setName("mt5");
		mt5.start();
		mt5.setPriority(Thread.MAX_PRIORITY);
		
		
		Thread.currentThread().setName("test6");
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		for(int i = 0;i < 10;i++){
			System.out.println(Thread.currentThread().getName()+":"+i+
					"优先级:" + Thread.currentThread().getPriority());
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test5(){
		//这三个线程对象占用同一个Runnable接口对象的引，实现了数据共享的操作用。
		
		MyThread4 mt1 = new MyThread4(); //创建线程对象
//		Thread t1 = new Thread(mt1);
//		t1.start();
		
		new Thread(mt1).start();			//启动线程
		new Thread(mt1).start();			//启动线程	
		new Thread(mt1).start();			//启动线程
		
	}
	
	@Test
	public void test3(){
		//事实上一共买了15张票，每个线程都单独卖了15张票
		MyThread3 mt1 = new MyThread3(); 
		MyThread3 mt2 = new MyThread3(); 
		MyThread3 mt3 = new MyThread3(); 
		
		//一个线程只能执行一次start()
		mt1.start();
		//mt1.start();
		
		mt2.start();
		mt3.start();
		
	}
	
	//利用Runable接口实现多线程，数据共享更加方便
	@Test
	public void test2(){
		MyThread2 mt1 = new MyThread2("线程A"); 
		MyThread2 mt2 = new MyThread2("线程B"); 
		MyThread2 mt3 = new MyThread2("线程C"); 
		new Thread(mt1).start();
		new Thread(mt2).start();
		new Thread(mt3).start();
		
	}
	//使用Thread类实现多线程，缺点：单继承
	@Test
	public void test1(){
		MyThread mt1 = new MyThread("线程A"); 
		MyThread mt2 = new MyThread("线程B"); 
		MyThread mt3 = new MyThread("线程C"); 
		mt1.start();
		/*try {
			mt2.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		mt2.start();
		mt3.start();
		System.out.println(mt1.isAlive());
		
	}
	class MyThread extends Thread{
		private String title;
		public MyThread(String title){
			this.title = title;
		}
		@Override
		public void run(){
			for(int i = 0;i < 5;i++){
				System.out.println(this.title + "运行,i = "+i);
			}
		}
	}
	//利用Runable接口实现多线程，可以避免单继承局限，数据共享更加方便
	class MyThread2 implements Runnable{
		private String title;
		public MyThread2(String title){
			this.title = title;
		}
		@Override
		public void run(){
			for(int i = 0;i < 5;i++){
				System.out.println(this.title + "运行,i = "+i);
			}
		}
	}
	//卖票
	class MyThread3 extends Thread{
		private int ticket = 5;  //一共5张票
		@Override
		public void run(){
			for(int i = 0;i < 50;i++){
				if(ticket > 0){
					System.out.println("卖票,ticket = " + this.ticket--);
				}
			}
		}
	}
	//Runnable实现多线程卖票
	class MyThread4 implements Runnable{
		private int ticket = 5;  //一共5张票
		@Override
		public void run(){
			for(int i = 0;i < 50;i++){
				if(ticket > 0){
					System.out.println("卖票,ticket = " + this.ticket--);
				}
			}
		}
	}
	class MyThread5 extends Thread{
		@Override
		public void run(){
			for(int i = 0;i < 5;i++){
				System.out.println(Thread.currentThread().getName()+":" + i 
						+ "优先级:" + Thread.currentThread().getPriority());
			}
		}
	}
	
}
