import java.util.*;
public class Exercise2 {
	
	public static void main(String[] args){
		int sum=0;
		
		Scanner in = new Scanner(System.in);
		for(int i = 0;i<10;i++){
			sum+=in.nextInt();
			
			
		}
		System.out.println(sum);
		in.close();
	}

}
