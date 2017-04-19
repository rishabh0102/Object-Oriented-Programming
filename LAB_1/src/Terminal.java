class Terminal{
	public static int Fibonnaci(int n){
		if(n == 1 || n ==2){
			return 1;
			
		}
		else
			return (Fibonnaci(n-1) + Fibonnaci(n-2));		
	}
public static void main(String[] args) {
	double sumL2R = 0.0;
	double sumR2L = 0.0;
	for(int i = 1;i<=5000;i++){
		sumL2R+=1/(double)(i);
	}
	for(int i = 5000;i>=1;i--){
		sumR2L+=1/(double)(i);
	}

	System.out.println(sumR2L-sumL2R);
	
	
}
}
