
public class Customer {
	private String name;
	private String idNo;
	private double balance;
	private Item item;
	public Customer(String name, String idNo, double balance) {
		super();
		this.name = name;
		this.idNo = idNo;
		this.balance = balance;
	}
	public Customer(String name, String idNo) {
		super();
		this.name = name;
		this.idNo = idNo;
		this.balance = 5000;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public Item getItem() {
		return item;
	}
	public void print(){
		System.out.println("itemName "+this.item.getItemName() );
		System.out.println("Id NO "+this.item.getItemIdNo() );
		System.out.println("Quantity "+this.item.getItemQuantity() );
		System.out.println("ItemPrice "+this.item.getItemPrice() );

	}
	public void buyItem(Item item){
		this.item = item;

		double required = item.getItemPrice()*item.getItemQuantity();
		if(this.balance>=required && item.getItemQuantity()>=1){
			
			System.out.println("Item purchased"+this.item.getItemName() );
			
			
			
			this.setBalance(this.balance-required);
			System.out.println("Current Balance "+this.getBalance() );
			print();
			
		}
		else if (this.balance<required){
			System.out.println("insufficient balance");
		}
		else if(this.item.getItemQuantity()<1){
			System.out.println("Not valid order ");

		}



		
	}
}
	
	


