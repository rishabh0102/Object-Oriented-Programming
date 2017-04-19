
public class Mystatic {
	private int a;
	static int b;
	Mystatic(){
		b++;
	}
	public void showdata(){
		System.out.println(a + " "+b);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mystatic s1 = new Mystatic();
		Mystatic s2 = new Mystatic();
		s1.showdata();
		Mystatic.b++;
		s2.showdata();

		

	}

}
