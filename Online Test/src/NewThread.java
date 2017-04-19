
public class NewThread implements Runnable {
	Thread t;
	public NewThread() {
		// TODO Auto-generated constructor stub
		t = new Thread(this,"Chil Thread");
		System.out.println("Child Thread Starting");
		t.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			for(int i = 0; i<5;i++){
				System.out.println("Child Thread: " + i);
				Thread.sleep(500);
			}
		}
		catch(InterruptedException e){
			System.out.println("chil thread has been interrupted");
			
		}
		System.out.println("Exiting Child Thread");
		
	}

	

}
