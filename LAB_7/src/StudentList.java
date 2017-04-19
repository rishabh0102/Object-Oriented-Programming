import java.util.*;
public class StudentList {
public static void main(String args[]) {
          // create an array list
ArrayList studentList = new ArrayList();
// add elements to the array list 
studentList.add(new Student("Ramesh","Male",18)); studentList.add(new Student("Reeta","Female",19)); studentList.add(new Student("Seema","Female",18)); studentList.add(new Student("Suresh","Male",20));
System.out.println("Original contents ofstudentList:");
Iterator itr = studentList.iterator();
//          Iterator itr = studentList.iterator();
while(itr.hasNext()) {
Student element = (Student)itr.next();
System.out.print(element +"\n"); }

ListIterator ltr = studentList.listIterator();
while(ltr.hasNext()){
	Student element = (Student)ltr.next();
	System.out.println(element);
}
while(ltr.hasPrevious()){
	Student element = (Student)ltr.previous();
	System.out.println(element);
}

	}
}