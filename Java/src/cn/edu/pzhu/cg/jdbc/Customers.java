package cn.edu.pzhu.cg.jdbc;

public class Customers {
	public String name;
	public int age;
	
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customers(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customers : name=" + name + ", age=" + age + "";
	}
	
}
