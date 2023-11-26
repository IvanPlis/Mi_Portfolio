package itemArma;
import partida.Item;

abstract public class Arma extends Item {
	
	public Arma(int identificador) {
		super(identificador);
		// TODO Auto-generated constructor stub
	}

	abstract public void recojerItem();
	abstract public void arrojarItem();
	
	//Metodo comun a todas las armas.
	abstract public void equiparArma();
	

}
