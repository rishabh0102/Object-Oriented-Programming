
public class TestTax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaxOnSalary tax1 = new TaxOnSalary();
		TaxOnSalary tax2 = new TaxOnSalary(false);
		
		tax1.inputSalary();
		System.out.println(tax1.calculateTax());
		System.out.println(tax2.calculateTax());

	}

}
