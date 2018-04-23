package cn.edu.pzhu.cg;

/* 内部类:
 * 1.在类的内部再定义类.外面定义的类：外部类。里面定义的类：内部类。
 * 2.内部类的分类：成员内部类（声明在类的内部且方法的外边）、局部内部类（声明在内的方法里面）
 * 3.掌握:
 * 	   ①如何创建成员内部类的对象。
 * 	   ②如何区分调用外部类和内部类 的变量(特别是变量名重名时)。
 * 	   ③局部内部类的使用。
 * */
public class TestInnerClass {

	public static void main(String[] args) {
		// 创建静态内部类的对象可以直接通过外部类调用内部类的构造器
		Person.Dog dog = new Person.Dog();

		// 创建非静态类的对象，必须先创建外部类的对象，在通过外部类的对象创建内部类
		// Person.Bird bird = new Person.Bird(); 非静态不能直接调用构造器
		Person p = new Person();
		Person.Bird bird = p.new Bird();

		// 外部类.内部类 内部类对象 = new 外部类().new 内部类()
		Person.Bird bird2 = new Person().new Bird();
		bird.print();
		bird2.setName("斑鸠");
		System.out.println("--------------------");

		// 方式一：创建实现接口的对象
		Phone p1 = new Phone();
		TestInnerClass test = new TestInnerClass();
		test.print(p1);

		Computer c = new Computer();
		test.print(c);
		// 方式二：创建匿名类，实现接口方法
		Project p2 = new Project() {

			@Override
			public void getName() {
				System.out.println("雷柏键盘");
			}

			@Override
			public void getPrice() {
				System.out.println("￥189");
			}

		};
		test.print(p2);

		// 方式三：创建匿名类
		test.print(new Project() {

			@Override
			public void getName() {
				System.out.println("雷蛇鼠标");
			}

			@Override
			public void getPrice() {
				System.out.println("￥29");

			}

		});

	}

	public void print(Project p) {
		p.getName();
		p.getPrice();
	}
}

class Person {
	String name = "张三";
	int age;

	// 成员内部类(非静态)
	class Bird {
		String name = "麻雀";
		int id;

		public void print() {
			show();
		}

		public void setName(String name) {
			System.out.println(name); // 形参的name
			System.out.println(this.name); // 其当前类的name “麻雀”
			System.out.println(Person.this.name);// 父类的name "张三"

		}
	}

	// 静态内部类
	static class Dog {

	}

	public void Iofo() {
		// 成员方法内部类
		class Cat {

		}
	}

	public void show() {
		System.out.println("我是show()方法!");
	}

	// 局部内部类的使用。
	public void method1() { // 使用比较少
		class C {

		}
	}

	// 使用方式一：
	// 常用一个方法返回一个内部类和接口，而这个内部类接口在这个方法中实现
	public Comparable getComparable() {
		class MyComparable implements Comparable {

			@Override
			public int compareTo(Object o) {
				return 0;
			}

		}
		return new MyComparable();
	}

	// 使用方式二：
	// 返回一个内部类
	public Comparable getComparable2() {
		return new Comparable() {

			@Override
			public int compareTo(Object o) {
				return 0;
			}

		};
	}

}

interface Project {
	public void getName();

	public void getPrice();
}

class Computer implements Project {

	@Override
	public void getName() {
		System.out.println("华硕电脑-X450J !");
	}

	@Override
	public void getPrice() {
		System.out.println("￥6000");
	}

}

class Phone implements Project {

	@Override
	public void getName() {
		System.out.println("魅族 MX5 !");
	}

	@Override
	public void getPrice() {
		System.out.println("￥1500");
	}

}
