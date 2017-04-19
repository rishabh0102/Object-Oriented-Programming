
public class StudentList {
	public static Student[] list = new Student[2];
	public static int count =0;
	
	public static void addStudent(Student std){
		if(count>=10)return;
		
		list[count] = std;
		count++;
	}
	public static Student[] getStudentWithAge(int age){
		Student[] new_age = new Student[2];
		int count_age = 0;
		for(Student i : list){
			if (i.getAge() ==age){
				new_age[count_age] = i;
				count_age++;
			}
//			if(new_age==null)
//				return null;	
		}
		return new_age;
	}
	public static Student[] getStudentWithLastName(String lname){
		Student[] new_last = new Student[2];
		int count_last = 0;
		for(Student i : list){
			if (i.getName().getlname() ==lname){
				new_last[count_last] = i;
				count_last++;
			}
//			if(new_age==null)
//				return null;	
		}
		return new_last;
	}
	public static Student[] getStudentsInRange(int minAge, int maxAge) {
		Student[] range = new Student[2];
		int c_r = 0;
		for(Student i : list){
			if (i.getAge()>=minAge && i.getAge()<=maxAge){
				range[c_r] = i;
				c_r++;
			}

		 }
		return range;
	}
	

}
