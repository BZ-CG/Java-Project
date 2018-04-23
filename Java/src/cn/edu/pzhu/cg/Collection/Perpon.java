package cn.edu.pzhu.cg.Collection;

public class Perpon implements Comparable {

	private String name;
	private Integer age;
	public Perpon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Perpon(String name, Integer age) {
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perpon other = (Perpon) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Perpon [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof Perpon){
			Perpon p = (Perpon)o;
			int i = this.name.compareTo(p.name);
			if(i == 0){			//如果相同，按照age排序
				return this.age.compareTo(p.age);
			}else{
				return i;
			}
		}
		return 0;
	}
	
}
