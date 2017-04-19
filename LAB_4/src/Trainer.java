import java.util.*;
public class Trainer {
	public static Vector<Pokeball> collection = new Vector<Pokeball>();
	
	public static void capturePokemon(Pokemon pokemon){
		Pokeball p = new Pokeball(pokemon);
		collection.add(p);
		
	}
	public static Pokemon[] getPokemonWithType(String Type){
		Pokemon [] pok = new Pokemon[collection.size()];
		int count = 0;
		for(Pokeball i : collection){
			if(i.getPokemon().getType()==Type){
				pok[count] = i.getPokemon();
				count++;
			}
		}
		return pok;
	}
}
