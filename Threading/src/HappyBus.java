
public class HappyBus{
	

	public static void main(String[] args){
			BusReservation br = new BusReservation();
			PassengerThread pt1 = new PassengerThread(1,br,"SAM");
			PassengerThread pt2 = new PassengerThread(1, br, "Jack");
			PassengerThread pt3 = new PassengerThread(1, br, "Rishabh");
			pt3.start();
			

			pt2.start();

			pt1.start();
			}
}
