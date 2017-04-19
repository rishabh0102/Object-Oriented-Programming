import java.util.*;
import java.lang.*;
public class Pokemon {
	private String name;
	private int id;
	private String type;
	
	public String getName(){
		return name;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	Pokemon(String pokemon){
		StringTokenizer str = new StringTokenizer(pokemon,"|");
		if(str.countTokens()==3){
			name = str.nextToken();
			id = Integer.parseInt(str.nextToken());
			type = str.nextToken();
			
		}
		else{
			str = new StringTokenizer(pokemon,";");
			id = Integer.parseInt(str.nextToken());
			name = str.nextToken();
			type = str.nextToken();
		}
		
	}
	public void getPokemon(){
		System.out.println("#"+id);
		System.out.println(name);
		System.out.println(type);


		
	}

}
