package cn.edu.pzhu.cg.thread;

import org.junit.Test;
/*
 * 多线程卖票机制
 * 线程安全问题存在的原因？
 * 		由于一个线程在操作共享数据过程中，未执行完毕的情况下，另外的线程参与进来，导致共享数据存在安全问题。
 * 如何解决线程的安全问题？
 * 		必须让一个线程操作完共享数据后，其他线程才有机会参与操作共享数据。
 * Java如何实现线程的安全：线程的同步机制
 * 		方式一：同步代码块
 * 			synchronized（同步监视器){
 * 			//需要被同步的代码块(即操作共享数据的代码)
 * 			}
 * 			1.共享数据：多个线程共同操作的同一个数据（变量）
 * 			2.同步监视器：由一个类的对象来充当，哪个线程获取此监视器，谁就执行大括号里面被同步的代码。俗称：锁。
 * 		方式二：同步方法:将操作共享数据的方法声明为synchronized。即此方法为同步方法，能够保证当其中一个线程执行此方法时
 * 				其他线程在外等待直至此线程执行完该方法
 * 			1.同步方法的锁:this.(应当实现Runbable 接口，此时的this代表的是同一个对象)
 * 			2.如果是用选择继承 Thread 的话，此时不应该使用同步方法；因为同步方法的锁就是当前对象，这样对于每个继承
 * 			     Thread 的对象都是一个独立的锁，不能达到同步的目的。
 * 线程同步的弊端:由于同一个时间只能有一个线程访问共享数据，效率变低了。 					
 * 
 */

public class testThread {

	public static void main(String[] args) {
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		
		w1.setName("窗口一");
		w2.setName("窗口二");
		w3.setName("窗口三");
		
		w1.start();
		w2.start();
		w3.start();
	}
	@Test
	public void test2(){
		Window w1 = new Window();
		Window w2 = new Window();
		
		w1.setName("窗口一");
		w2.setName("窗口二");

		w1.start();
		w2.start();
	}
	@Test
	public void test1(){
		MyThread mt1 = new MyThread();
		//mt1.start(); // Runable 接口中并没有start()方法
		//mt1.run(); 	//并不是多线程机制
		//要想启动一个多线程必须调用start()方法
		Thread t1 = new Thread(mt1, "线程1");
		t1.start();
		
		//在创建一个线程
		Thread t2 = new Thread(mt1);
		t2.start();
	}

}
class Window extends Thread{
	static int TICKET = 100;//静态变量，表示公用100张票
	public void run(){
		while(true){
			synchronized(this){
				if(TICKET > 0){
					try {
						Thread.currentThread().sleep(5);
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
class MyThread implements Runnable{
	public void run(){
		for(int i = 0; i < 50;i++){
			if(i % 2 == 0)
				System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		
	}
}
