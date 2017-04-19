import java.util.*;
public class Temp {
	private int a,b;
	

	public Temp(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	


	public int getA() {
		return a;
	}



	public void setA(int a) {
		this.a = a;
	}



	public int getB() {
		return b;
	}



	public void setB(int b) {
		this.b = b;
	}

	public static void update(Temp ab){
		ab = new Temp(10,20);
		ab.setA(1);
		ab.setB(2);
	}


	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ArrayList<String>names  =new ArrayList<String>(10);
		names.add("java");
		names.add("python");
		names.add(1,"Fortran");
		names.add(1,"c");
		names.set(1, "c++");
		names.add(3,"R");
		names.remove(3);
		names.add("c++");
		
		System.out.println(names);







	}

}
