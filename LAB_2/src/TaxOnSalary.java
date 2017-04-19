import java.util.Scanner;
public class TaxOnSalary {
	private double salary;
	private boolean isPanSubmitted;
	public TaxOnSalary(boolean isPanSubmitted) {
		super();
		this.isPanSubmitted = isPanSubmitted;
		this.salary = 1000;
	}
	public TaxOnSalary() {
		super();
		this.salary = 0.0;
		this.isPanSubmitted = false;
	}
	public double getSalary() {
		return salary;
	}
	public boolean getisPanSubmitted() {
		return isPanSubmitted;
	}
	
	public double calculateTax(){
		if(salary<=18000 && isPanSubmitted ==true) return 0.0;
		
		else if(salary<=18000 && isPanSubmitted == false) return .05*this.getSalary();
		else if( (salary>=18000) && (salary<=500000)) return .1*salary;
		else if( (salary>500000) && (salary<=1000000)) return .2*salary;
		else if( salary>1000000) return .3*salary;
		else return -1;

		
	}
	public void inputSalary(){
		Scanner in  = new Scanner(System.in);
		this.salary = in.nextDouble();
		in.close();
	}
	

}
