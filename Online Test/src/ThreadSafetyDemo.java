
public class ThreadSafetyDemo {
	public static void main(String[] args) {
		BusReservation br = new BusReservation();
		PassengerThread pt1 = new PassengerThread(1,br,"Ramesh");
		PassengerThread pt2 = new PassengerThread(2, br, "Surresh");
		PassengerThread pt3 = new PassengerThread(1, br, "Satish"); 
		pt1.start();
		pt2.start();
		pt3.start();
		
	}

}
