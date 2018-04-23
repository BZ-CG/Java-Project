package cn.edu.pzhu.cg.enumeration;
/*
 * 枚举类的使用
 */
public class TestEnumeration {

	public static void main(String[] args) {
		Student student = Student.s1;
		student.show();
		System.out.println(student);
	}
}
class Student{
	//1.提供类的属性，声明为private，final
	private final String name;
	private final Integer age;
	//2.为上述属性初始化
	public Student(String name,Integer age){
		this.name = name;
		this.age = age;
	}
	//3.通过公共方法来调用属性
	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
	//创建枚举类的对象：将类的对象声明为 public static final
	public static final Student s1 = new Student("小明",21);
	public static final Student s2 = new Student("小红",20);
	
	public void show(){
		System.out.println("这是一个学生!");
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
	
}