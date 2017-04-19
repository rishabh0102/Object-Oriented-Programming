import java.io.*;
public class Example1 {

	public static void main(String[] args) throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int sum =0;
		for(int i =0;i<10;i++){
			int numbers = Integer.parseInt(br.readLine());
			sum+=numbers;
		}
		
		System.out.println(sum);
		
		
		
	}

}
