import java.util.*;
public class ArrayListDemo {
	
	public static void main(String[] args){
		ArrayList arr = new ArrayList();
		System.out.println("Size:"+arr.size());
		arr.add("B");
		arr.add("I");
		arr.add("B");
		arr.add("I");
		arr.remove("B");
		
		System.out.println(arr.subList(0,2));
		
	}

}
