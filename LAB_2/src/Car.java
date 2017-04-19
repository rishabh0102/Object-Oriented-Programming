
public class Car {
	
	private int year;
	private String make;
	private double speed;
	public Car(int year, String make, double speed) {
		super();
		this.year = year;
		this.make = make;
		this.speed = speed;
	}
	public int getYear(){
		return this.year;
	}
	public String getMake() {
		return make;
	}
	public double getSpeed() {
		return speed;
	}
	public void Accelerate(){
		speed+=1;
	}
	public void Accelerate(int increment){
		speed+=increment;
	}
	public void Break(int b){
		speed-=Math.sqrt(b);
	}
	public static void main(String[] args){
		Car t = new Car(2000,"toyota",200);
		System.out.println(t.getSpeed());
		System.out.println(t.getMake());
		System.out.println(t.getYear());
		t.Accelerate();
		System.out.println(t.getSpeed());
		t.Accelerate(10);
		System.out.println(t.getSpeed());
		t.Break(50);
		System.out.println(t.getSpeed());
		
//		Car c = new Car();



		
		
	}

}
