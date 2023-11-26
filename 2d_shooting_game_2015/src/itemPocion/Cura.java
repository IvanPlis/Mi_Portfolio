package itemPocion;
import partida.Personaje;

public class Cura extends Pocion {

	public Cura(int identificador) {
		super(identificador);
		// TODO Auto-generated constructor stub
	}

	public void usarPocion(Personaje personaje) {
		personaje.setVida(100);	
	}

	@Override
	public void recojerItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void arrojarItem() {
		// TODO Auto-generated method stub
		
	}


}
