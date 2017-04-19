
public class Movie {
	interface Bookable{
		public void printTicket();
		public void giveTicket(String Movie);
		
	}
	public void BookTheTicket(){
		class EnglishMovie implements Bookable{
			String name;
			public void printTicket(){
				giveTicket("BlindDate");
			}
			public void giveTicket(String movie){
				name = movie;
				System.out.println("Booked for"+name);
			}
			
		}
		Bookable Hollywood = new EnglishMovie();
		Bookable HindiMovie = new Bookable(){
			public void printTicket(){
				giveTicket("Don");
			}
			public void giveTicket(String movie){
				String name = movie;
				System.out.println("Booked for"+name);

				
			}
		};
		Hollywood.printTicket();
		HindiMovie.printTicket();
	}
	public static void main(String [] args){
		Movie m = new Movie();
		m.BookTheTicket();
	}

	
}
