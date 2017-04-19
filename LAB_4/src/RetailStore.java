public class RetailStore {
private int[] itemId = {1001,1002,1003,1004,1005}; private double[] price = {13.50, 18.00, 19.50, 25.50};
private double computePrice(int value) {
// method to compute the price of the item.
         // it returns the price
for (int i = 0; i<price.length; ++i) { // note the use of price.length.
         // it gives the length of the array
if (itemId[i] == value) { return price[i];

}
}  // method which takes in the index and
// returns the price of the item
return -1;}
public static void main(String[] args) {
//main method. Execution begins here 
	RetailStore retailOne = new RetailStore(); System.out.println("price of item id 1002 is " + retailOne.computePrice(1003)); System.out.println("price of item id 1004 is "
                                        +retailOne.computePrice(1004));
System.out.println("price of item id 1009 is "
+retailOne.computePrice(1007));
     /* un-comment the above line and see the output.
            * Why there is no compilation error?*/
} }