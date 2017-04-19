import java.util.*;
public class RetailStores {
	private Vector<Item>items = new Vector<Item>();
	public void addItem(Item item){
		items.add(item);
	}
	private double computePrice(int value){
		for(Item i : items){
			if(i.getItemId()==value)return i.getPrice();
			
		}
		return -1;
	}

public static void main(String[] args) { //main method. Execution begins here 
	RetailStores retailOne = new RetailStores(); 
	retailOne.addItem(new Item(1001,13.50));
	retailOne.addItem(new Item(1002,18.00));
	retailOne.addItem(new Item(1003,19.50));
	retailOne.addItem(new Item(1004,25.50)); retailOne.addItem(new Item(1005,25.50));
System.out.println("price of item id 1002 is "+retailOne.computePrice(1003)); 
System.out.println("price of item id 1004 is "+retailOne.computePrice(1004));

}
}