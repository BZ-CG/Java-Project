package cn.edu.pzhu.cg.Interface;
//接口与具体类直接存在多态 
public class TestInterface2 {

	public static void main(String[] args) {
		Duck duck = new Duck();
		TestInterface2.test1(duck);
		TestInterface2.test2(duck);
		TestInterface2.test3(duck);
	}
	public static void test1(Fly fly){
		fly.fly();
	}
	public static void test2(Run run){
		run.run();
	}
	public static void test3(Swi swi){
		swi.swi();
	}
}
interface Fly {
	void fly();
}

interface Run {
	void run();
}

interface Swi {
	void swi();
}
class Duck implements Fly,Run,Swi{

	@Override
	public void swi() {
		System.out.println("游泳!");
		
	}

	@Override
	public void run() {
		System.out.println("奔跑!");
	}

	@Override
	public void fly() {
		System.out.println("飞翔!");
	}
	
}
