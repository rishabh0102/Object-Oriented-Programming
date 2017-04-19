
public class Address {
	private String line1;
	private String line2;
	private String line3;
	private char[] city;
	private char[] state;
	private String pin;
	
	
	Address(String value){
		String[] s = value.split("$");
		line1 = s[0];
		line2 = s[1];
		line3 = s[2];
		s[3].getChars(0, s[3].length()-1, city, 0);
		s[4].getChars(0, s[4].length(), state, 0);
		pin = s[5];

	}


	public String getLine1() {
		return line1;
	}


	public String getLine2() {
		return line2;
	}


	public String getLine3() {
		return line3;
	}


	public String getCity() {
		return city.toString();
	}


	public String getState() {
		return state.toString();
	}


	public String getPin() {
		return pin;
	}

	

}
