import java.util.*;
public class Name {
	private String fname;
	private String mname;
	private String lname;
	
	Name(String value){
		StringTokenizer str = new StringTokenizer(value,",");
		
		if(str.countTokens() ==3){
			this.fname = str.nextToken();
			this.mname = str.nextToken();
			this.lname = str.nextToken();
			System.out.println(", working");


		}
		else{
			
			str = new StringTokenizer(value,";");
			this.lname = str.nextToken();
			this.mname = str.nextToken();
			this.fname = str.nextToken();
			System.out.println("; working");
		}

		
		
	}
	public String getfname(){
		return fname;
	}
	public String getmname(){
		return mname;
	}
	public String getlname(){
		return lname;
	}
	public String getName(){
		return (fname+" "+mname+" "+lname);
	}
	public String toString() {
		return (fname+mname+lname);

	}
	
	

}
