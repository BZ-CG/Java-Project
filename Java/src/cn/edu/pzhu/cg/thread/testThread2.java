package cn.edu.pzhu.cg.thread;

/*
 * 有一个银行账号，余额为0，现在有两个用户，向你们存钱，每次存1000，一共存三次，存完后，余额3000.
 * 
 * 1.线程同步。
 * 2.数据共享
 */
class Account {
	double balance;// 余额

	public Account() {

	}

	// 存钱
	public synchronized void deposit(double amt) {//这里的锁相当于this
		balance += amt;
		try {
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ":" + balance + "￥");
	}
}

class Customer extends Thread {
	Account account;

	public Customer(Account account) {
		this.account = account;
	}

	public void run() {
		for (int i = 0; i < 3; i++)
			account.deposit(1000);
	}
}

public class testThread2 {
	public static void main(String[] args) {
		Account account = new Account();    //同一个对象，相当于一把锁，保证了同步问题。
		Customer c1 = new Customer(account);
		Customer c2 = new Customer(account);
		
		c1.setName("甲");
		c2.setName("乙");
		
		c1.start();
		c2.start();
		
	}
}
