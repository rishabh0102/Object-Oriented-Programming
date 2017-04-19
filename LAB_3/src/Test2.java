class Test2 {
    public static void main(String args[]) {
         Integer I = 10; // what happens for this statement

Integer J = 20; // what happens for this statement
// Observe the output for following two
// statements carefully
System.out.println(I.intValue());
System.out.println(I);
// Observe the output for following two
// statements carefully
System.out.println(J.intValue());
System.out.println(J);
Integer K1 = new Integer(I.intValue()+J.intValue());
Integer K2 = I + J + K1;
System.out.println(K2);}
}