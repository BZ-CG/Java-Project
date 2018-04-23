package cn.edu.pzhu.cg.thread;
/*
 * 死锁：不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了死锁。（处理线程同步时，容易出现）
 * 
 */
public class testDeadLock {

	public static void main(String[] args) {
		StringBuffer s1 = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		new Thread(){
			public void run(){
				synchronized (s1) {
					try {
						currentThread().sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					s1.append("A");
					System.out.println(s1);
					
					synchronized (s2) {
						s1.append("B");
						System.out.println(s2);
					}
				}
				
			}
		}.start();
	/*第一个线程握住s1，要想执行完毕必须要s2，但是在sleep(10)后，进入第二个线程，第二个线程握住s2，想要执行完毕，必须要s1
	     这个时候线程一需要的在线程二里，线程二需要的在线程一里，都在等待对象释放资源，形成了死锁。	
	*/
		
		new Thread(){
			public void run(){
				synchronized (s2) {
					try {
						currentThread().sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					s1.append("C");
					System.out.println(s1);
					
					synchronized (s1) {
						s1.append("D");
						System.out.println(s2);
					}
				}
				
			}
		}.start();
	}

}
