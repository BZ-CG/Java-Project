package cn.edu.pzhu.cg.Interface;
//接口的工厂设计模式
public class TestFactoryMethod {

	public static void main(String[] args) {
		WorkFactory iFactory = new StudentWorkFactory();
		iFactory.getWork().doWork();
	}
}
class StudentWorkFactory implements WorkFactory{

	@Override
	public Work getWork() {
		return new StudentWork();
	}
	
}
class TeacherWorkFactory implements WorkFactory{

	@Override
	public Work getWork() {
		return new TeacherWork();
	}
	
}

interface WorkFactory{				//工厂接口
	Work getWork();
}
interface Work{ 					//工作方式接口
	void doWork();
}

class StudentWork implements Work{	//工作类

	@Override
	public void doWork() {
		System.out.println("学生工作类!");
	}
	
}
class TeacherWork implements Work{

	@Override
	public void doWork() {
		System.out.println("老师工作类!");
	}
	
}