package cn.edu.pzhu.cg.enumeration;

import cn.edu.pzhu.cg.Collection.Student;

/*
 * 枚举类的使用
 * 	1.自定义枚举类
 * 	2.用enum关键字定义枚举类：
 * 		 (1).常用的方法：values()[获取所有的枚举类对象],valuesOf(String name)[获取指定name的枚举类],ordinal()[获取枚举类的序号]
 * 		 (2).在枚举类中实现接口，可以让不同的枚举类对象调用被重写的方法，在每个对象里面分别重写抽象方法，达到执行的效果不同。
 */
public class TestEnumeration2 {

	public static void main(String[] args) {
		Student2 student = Student2.s1;
		System.out.println(student);
		student.show();
		
		Student2[] stu = Student2.values();
		for (Student2 student2 : stu) {
			System.out.println(student2);
		}
		System.out.println();
		
		String string = "s2";
		Student2 student2 = Student2.valueOf(string);
		System.out.println(student2);
		student2.show();
		
	
	}
} 
interface Info{
	public void show();
}
/*
 * enum关键字定义枚举类
 */
enum Student2 implements Info{
	//枚举类中定义的对象必须放在类的开头
	s1("小明",21){
		public void show(){						//在枚举类里面重写抽象方法
			System.out.println("你好，我是小明。");
		}
	},
	s2("小红",20){
		public void show(){
			System.out.println("Hello，我是小红。");
		}
	};
	
	private final String name;
	private final Integer age;

	private Student2(String name,Integer age){
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	
	public void show(){
		System.out.println("这是一个学生!");
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
	
}