package cn.edu.pzhu.cg.thread;

/*
 * 生产者和消费者问题：
 * 		生产者将生产的商品交给店员，店员负责给消费者提供商品；店员的货架上最多只能存放20件商品，当放满后，应该通知生产者暂停一下
 * 	 当货架上的商品为0时，应该告知消费者稍等一下。
 * 
 * 分析：
 * 	1.多线程问题（生产者和消费者）
 *  2.涉及到共享数据，应当考虑安全
 *  3.共享数据-产品的数量
 *  4.涉及到线程的通信
 */

class Clerk{		//店员
	int  produce;
	
	public synchronized void addProduce(){
		if(produce >= 20){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			produce++;
			System.out.println(Thread.currentThread().getName()+" :生产了第"+produce+"件商品");
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notifyAll();
		}
		
		
	}
	public synchronized void consumeProduce(){
		if(produce <= 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" :消费第"+produce+"件商品");
			produce--;
			notify();
		}
	}
	
}
class Producer implements Runnable{  //生产者
	Clerk clerk;

	public Producer(Clerk clerk) {
		 this.clerk = clerk;
	 }
	@Override
	public void run() {
		System.out.println("生产者开始生产商品:");
		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			clerk.addProduce();
		}
	}
	
	
}
class Consume implements Runnable{
	Clerk clerk;
	public Consume(Clerk clerk){
		this.clerk = clerk;
	}
	@Override
	public void run() {
		System.out.println("消费者开始消费商品:");
		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			clerk.consumeProduce();
		}
	}
	
	
}
public class testProduceConsume {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Producer p1 = new Producer(clerk);
		Producer p2 = new Producer(clerk);
		Producer p3 = new Producer(clerk);
		Producer p4 = new Producer(clerk);
		
		Consume c1  = new Consume(clerk);
		Consume c2  = new Consume(clerk);
		Consume c3  = new Consume(clerk);
		
		
		Thread t1 = new Thread(p1, "生产者1");
		Thread t2 = new Thread(p2, "生产者2");
		Thread t3 = new Thread(p3, "生产者3");
		Thread t4 = new Thread(p4, "生产者4");

		Thread T1 = new Thread(c1, "消费者1");
		Thread T2 = new Thread(c2, "消费者2");
		Thread T3 = new Thread(c3, "消费者3");
		
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		T1.start();
		T2.start();
		T3.start();
		
		
	}

}
