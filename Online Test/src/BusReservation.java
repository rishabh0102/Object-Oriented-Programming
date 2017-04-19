
public class BusReservation implements Runnable {
	private int totalSeatsAvailable = 2;
	
	public int getTotalSeatsAvailable() {
		return totalSeatsAvailable;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
//		BusReservation br =  new BusReservation();
		PassengerThread pt = (PassengerThread) Thread.currentThread();
		boolean ticketsBooked = this.bookTickets(pt.getSeatsNeeded(), pt.getName());
		if(ticketsBooked == true){
			System.out.println("Congrats "+ Thread.currentThread().getName()+" Number of seats requested:"+pt.getSeatsNeeded()+" are Booked");
		}
		else{
			System.out.println("Sorry "+ Thread.currentThread().getName()+" Number of seats requested:"+pt.getSeatsNeeded()+" are not Booked");
			
		}
	
}
	public synchronized boolean bookTickets (int seats, String name){
		 System.out.println("Welcome to happy bus "+ Thread.currentThread().getName()
				 + " Number of Tickets Available are: " + this.getTotalSeatsAvailable());
		 if (seats>this.getTotalSeatsAvailable()) { return false; }
		 else { totalSeatsAvailable = totalSeatsAvailable-seats; return true; } }	
	}

		
		
		



