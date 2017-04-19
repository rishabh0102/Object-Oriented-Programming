
public class DisplayMessage implements Runnable{
	private String message;
	public DisplayMessage(String message){
		this.message = message;
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 5;
		while(i>0){
			System.out.println(message);
			i--;
		}
		
	}

}
