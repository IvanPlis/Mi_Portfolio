package personajeHeroe;

import partida.Item;
import partida.Personaje;

public class Arquero extends Heroe {

	public Arquero(String nombre, int idPersonaje, Item item) {
		super(nombre, idPersonaje, item);
		
		this.ataque=15;
		this.def=5;
		
	}

	@Override
	public void atacar(Personaje personaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacar2(Personaje personaje) {
		// TODO Auto-generated method stub
		
	}

}
