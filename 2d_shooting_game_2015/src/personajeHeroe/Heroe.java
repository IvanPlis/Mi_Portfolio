package personajeHeroe;
import partida.*;

abstract public class Heroe extends Personaje {


	public Heroe(String nombre, int idPersonaje, Item item) {
		super(nombre, idPersonaje, item);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	abstract public void atacar(Personaje personaje);
	abstract public void atacar2(Personaje personaje);


}

