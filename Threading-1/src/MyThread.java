
public class MyThread extends Thread {
	
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(8);
		MyThread t = new MyThread();
		System.out.println(t.getPriority());
		System.out.println(Thread.currentThread().getPriority());


		
	}

}
