class ClassTest{
    public static void main(String args[]) {
        Circle c1 = new Circle(2.3);
        c1.area();
        // accessing static method with class name
//        Circle.getCircumference(2.3);
        Circle c2 = new Circle(3.45);
        c2.area();
        // accessing static method with references is discouraged
        c2.getCircumference (3.45);
        /* 1. Make the area function as static and observe the
               output
           2. Remove the formal argument from getCircumference()
               method and observe the output
           3. Rename static to final and observe the error(s) and
               correct them */
    } // end of main
}