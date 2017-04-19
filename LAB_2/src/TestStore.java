import java.util.Scanner;
public class TestStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc  = new Scanner (System.in);
		System.out.println("Enter name,idNo,balance");
		String name = sc.next();
		sc.nextLine();

		String idNo = sc.nextLine();
		double balance = sc.nextDouble();
		System.out.println(name+idNo+balance);
		Customer Rishabh = new Customer(name,idNo,balance);
			System.out.println("Enter Item_name,itemidNo,item_quantity,item_price");
			String item_name = sc.next();
//			sc.nextLine();
			String item_idNo = sc.next();
//			System.out.println(item_name + item_idNo);
			int item_quantity = sc.nextInt();
			double item_price = sc.nextDouble();
			sc.close();
			Item apple = new Item(item_name,item_idNo,item_quantity,item_price);
//			System.out.println("Enter Item_name,itemidNo,item_quantity,item_price");
//			String item_name1 = sc.nextLine();
//			String item_idNo1 = sc.nextLine();
//			sc.nextLine();
//
//			int item_quantity1 = sc.nextInt();
//			double item_price1 = sc.nextInt();
//			Item windows = new Item(item_name1,item_idNo1,item_quantity1,item_price1);
			
			Rishabh.buyItem(apple);
		


	}

}
