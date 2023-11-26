package personajeVillano;
import partida.Item;
import partida.Personaje;

public abstract class Villano extends Personaje {
	
	public Villano(String nombre, int idPersonaje, Item item) {
		super(nombre, idPersonaje, item);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	abstract public void atacar(Personaje personaje);
	abstract public void atacar2(Personaje personaje);

}
