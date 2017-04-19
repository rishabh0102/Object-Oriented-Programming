import java.io.IOException;
import java.util.*;
public class Test {
	public static Student readStudent() throws IOException{
		Scanner sc = new Scanner(System.in);
		String f = sc.next();
		String m = sc.next();
		String l = sc.next();
		int format = sc.nextInt();
		int age = sc.nextInt();
//		sc.nextLine();

		String d;
		if (format ==1){
			d = ",";
		}
		else
			d = ";";
		System.out.println(f+d+m+d+l);
		Name name1 = new Name(f+d+m+d+l);
		Student sd = new Student(name1,age);
//		sc.close();//sc.close creating problem
		
	return (sd);

		
		
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		StudentList.addStudent(Test.readStudent());
////		StudentList.addStudent(Test.readStudent());
//		StudentList.addStudent(Test.readStudent());

		for(int x=0;x<2;x++)	
		{
		StudentList.addStudent(Test.readStudent());	
		}
	System.out.println(StudentList.getStudentWithAge(21));
	System.out.println(StudentList.getStudentWithLastName("Gupta"));
	System.out.println(StudentList.getStudentsInRange(0, 30));
	




	}

}
