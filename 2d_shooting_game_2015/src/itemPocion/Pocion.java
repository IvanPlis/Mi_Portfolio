package itemPocion;
import partida.Item;
import partida.Personaje;

abstract public class Pocion extends Item{

	public Pocion(int identificador) {
		super(identificador);
		// TODO Auto-generated constructor stub
	}
	
	abstract public void recojerItem();
	abstract public void arrojarItem();
	
	//Metodo comun a todas las pociones
	abstract public void usarPocion(Personaje personaje);
	

}
