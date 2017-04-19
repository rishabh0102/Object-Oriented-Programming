
public class TestAccount {
	public static void main(String [] args){
		Account act = new Account(123456789,30000,"Rishabh");
		Account act1 = new Account(123456789,30000,"Rishabh");

		Bank b1 = new Bank();
		b1.addAccount(act);
		b1.addAccount(act1);
		b1.addAccount(null);
		System.out.println(b1.getAccts());
	}

}
