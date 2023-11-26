package personajeVillano;

import partida.Item;
import partida.Personaje;

public class Mago extends Villano {
	
	public Mago (String nombre, int idPersonaje, Item item) {
		// TODO Auto-generated constructor stub
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
