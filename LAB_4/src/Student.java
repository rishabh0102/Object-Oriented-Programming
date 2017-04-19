
public class Student {
	private Name name;
	private int age;

	public Name getName() {
	return name;
}
public void setName(Name name) {
	this.name = name;
}
	public int getAge() {
		return age;
	}
	public Student(Name name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String toString(){
		return this.name.getfname() +" "+name.getmname()+" "+name.getlname();
	}
}
