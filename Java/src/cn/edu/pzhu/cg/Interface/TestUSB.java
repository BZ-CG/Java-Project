package cn.edu.pzhu.cg.Interface;
//接口应用实现
public class TestUSB {

	public static void main(String[] args) {
		Computer computer = new Computer();
		computer.doWork(new Printer());
		
		Flash flash = new Flash();
		computer.doWork(flash);
		
		//实现接口的匿名类的对象
		USB phone = new USB(){

			@Override
			public void start() {
				System.out.println("手机开始工作!");
			}

			@Override
			public void stop() {
				System.out.println("手机停止连接!");
			}
			
		};
		computer.doWork(phone);
		
	}
}
interface USB{
	void start();
	void stop();
}
class Computer{
	public void doWork(USB usb){
		usb.start();
		System.out.println("---此设备开始工作---!");
		usb.stop();
	}
}
class Flash implements USB{

	@Override
	public void start() {
		System.out.println("U盘开始工作!");
	}

	@Override
	public void stop() {
		System.out.println("U盘停止工作!");
	}
	
}
class Printer implements USB{

	@Override
	public void start() {
		System.out.println("打印机开始工作!");
	}

	@Override
	public void stop() {
		System.out.println("打印机停止工作!");
	}
	
}