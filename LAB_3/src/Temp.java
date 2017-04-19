class A{
	public static void show(int a,int b){
		System.out.println("Class A "+(a+b));
	}
}
class B extends A{
	public static void show(int a,int b){
		System.out.println("Class B "+(a*b));
	}
	
}
	
public class Temp {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a = new A();
		a.show(1,2);
		a = new B();
		a.show(1, 2);
		B b = new B();
		b.show(1, 4);

	}

}
