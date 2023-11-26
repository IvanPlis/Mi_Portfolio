package partida;

import java.util.Vector;

abstract public class Personaje {
	
	//Un personaje puede tener varios items.
	protected String nombre;
	protected int idPersonaje;
	protected int vida;
	protected int ataque;
	protected int def;
	protected Vector<Item> items;
	
	
	public Personaje(String nombre, int idPersonaje, Item item) {
		super();
		this.nombre = nombre;
		this.idPersonaje = idPersonaje;
		this.vida = 100;
		
		//Valores default
		this.ataque = 10;
		this.def = 10;
		
		this.items = new Vector<Item>();
		
		if(items != null){
			this.items.add(item);
		}
	}

	//Metodos comunes a todos los personajes	
	abstract public void atacar (Personaje personaje);
	abstract public void atacar2 (Personaje personaje);
	
	//Recurpero la vida
	public void descansar () {
		this.vida=100;
	}
	
	//Queda mas lindo.
	public void toStringPersonaje() {
		System.out.println("Nombre PJ: " + this.nombre);
		System.out.println ("ID PJ: " + this.idPersonaje);
		System.out.println (" Vida PJ: " + this.vida);
		System.out.println (" ATT PJ: " + this.ataque);
		System.out.println (" DEF PJ: " + this.def);
		
		System.out.println("-----   		    -----");
	}
	
	//Getters y setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

}
