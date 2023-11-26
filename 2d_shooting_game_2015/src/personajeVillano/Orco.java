package personajeVillano;

import partida.Item;
import partida.Personaje;

public class Orco extends Villano {
	
	public Orco(String nombre, int idPersonaje, Item item) {
		// TODO Auto-generated constructor stub
		super(nombre, idPersonaje, item);
		
		this.ataque=5;
		this.def=15;
		
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
