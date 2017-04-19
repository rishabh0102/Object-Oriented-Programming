import java.util.*;
public class Bank {
	private ArrayList<Account> accts;
	int maxAxtive;
	Bank(){
		accts = new ArrayList<Account>();
	}
	public boolean addAccount (Account newone) {
		/* Write the code for adding new account, return false if account can’t be created */
		if (newone==null) return false;
//		accts = new ArrayList<Account>();
		accts.add(newone);
		return true;
		}
//		public boolean removeAccount (long acctnum) {
//		/* Write the code for removing the account, return false if
//		account does not exist */
//		}
//		public double deposit(long acctnum, double amount) {
//		       /* Write the code for depositing specified amount to the
//		account,
//		       return -1 if account does not exist */
//		}
//		public double withdraw(long acctnum, double amount) {
//		       /* Write the code for withdrawing specified amount from
//		the account,
//		return -1 if insufficient balance or account does not
//		exist */
//		}
		 //override toString() method to display details of all the
		//accounts in bank
	public ArrayList<Account> getAccts() {
		return accts;
	}
	public void setAccts(ArrayList<Account> accts) {
		this.accts = accts;
	}

	


}

