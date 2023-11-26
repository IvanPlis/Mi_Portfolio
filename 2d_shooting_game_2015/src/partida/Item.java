package partida;

abstract public class Item {
	
	protected int identificador;
	
	public Item(int identificador) {
		super();
		this.setIdentificador(identificador);
	}
	
	//Metodos comunes a todos los items
	abstract public void recojerItem();
	abstract public void arrojarItem();

	
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	
}
