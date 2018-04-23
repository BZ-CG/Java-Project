package cn.edu.pzhu.cg.Exception;

public class Test {

	public static void main(String[] args) {
		Student student = new Student();
		
			try {
				student.regist(-12);
				System.out.println(student);
			} catch (MyException e) {
				System.err.println("错误:"+e.getMessage());
			}
		
		
	}
}
class Student{
	private int id;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Student(int id) {
		this.id = id;
	}

	
	


	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}


	public void regist(int id) throws MyException{
		if (id > 0) {
			this.id = id;
		} else {
			throw new MyException("传入的 ID 不合法");
		}
	}
	
}
