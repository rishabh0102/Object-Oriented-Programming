import java.util.Scanner;
public class Example2 {

	public static void main(String[] args) {
		int x;
		double y;
		String a,b;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter integer");
		x  = sc.nextInt();
		y = sc.nextDouble();
		a = sc.next();
		sc.nextLine();
		b = sc.nextLine();
		
		sc.close();
		
		System.out.println("x "+x);
		System.out.println("y "+y);
		System.out.println("a "+a);
		System.out.println("b "+b);

		
	}

}
