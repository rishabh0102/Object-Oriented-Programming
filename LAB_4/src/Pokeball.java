
public class Pokeball {
	private Pokemon pokemon;

	public Pokeball(Pokemon pokemon) {
		super();
		this.pokemon = pokemon;
	}
	
	public Pokemon getPokemon(){
		return this.pokemon;
	}
	public void setPokemon(Pokemon pokemon){
		this.pokemon = pokemon;
	}
	public String toString(){
		return(pokemon.getId()+" "+pokemon.getName()+" "+pokemon.getType());
	}

}
