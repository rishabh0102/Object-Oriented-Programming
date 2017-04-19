
public class Producer implements Runnable{
	Q q;
	public Producer(Q q) {
		this.q = q;
		// TODO Auto-generated constructor stub
		new Thread(this, "Producer").start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i =0;
		while(true){
			q.put(i++);
		}
		
	}
	

}
