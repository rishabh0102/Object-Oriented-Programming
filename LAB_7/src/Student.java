
public class Student {
	private String name; private String gender; private int age;

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public Student(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	public String toString(){
		return name+" "+gender+" "+age; }

}
