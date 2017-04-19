
public class DisplayMessage implements Runnable {
	private String message;
	public DisplayMessage(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}
	public void run(){
		while(true){
			System.out.println(message);
		}
	}

}
