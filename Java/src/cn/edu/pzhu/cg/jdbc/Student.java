package cn.edu.pzhu.cg.jdbc;

public class Student {

	public String name;
	public int age;
	public String tel;
	public String major;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, int age, String tel, String major) {
		super();
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.major = major;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public String toString() {
		return "Student : name=" + name + ", age=" + age + ", tel=" + tel + ", major=" + major + "";
	}
	
}
