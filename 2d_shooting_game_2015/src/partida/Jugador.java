package partida;

import java.util.Vector;

public class Jugador {
	
	//Un Jugador puede tener varios personajes.
	
	private String nombre;
	private int idJugador;
	private Vector<Personaje> personajes;
	
	
	public Jugador(String nombre, int idJugador, Personaje personaje) {
		super();
		this.nombre = nombre;
		this.idJugador = idJugador;
		this.personajes = new Vector<Personaje>();
	
		if(personaje != null){
			this.personajes.add(personaje);
		}
	}
	
	//Para mostrar los jugadores en pantalla. Queda mas lindo asi.
	public void toStringJugador() {
		System.out.println ("Nombre Jugador: " + this.nombre);
		System.out.println("ID Jugador :" +  this.idJugador);
		System.out.println ("Personajes de " + this.nombre + ": ");
		
		//Busco todos los personajes de un jugador
		for (int i=0; i<personajes.size(); i++) {
			System.out.println ("	" + "Nombre: " + personajes.get(i).getNombre() + 
					"--> ID Personaje = " + personajes.get(i).getIdPersonaje());
		}
		System.out.println("-----------------------------------");
	}
	
	//En proceso
	public void agregarPersonaje (Personaje personaje, int idJugador) {
		
		
	}

	
	//Se ingresa un id de un pj a eliminar. Si existe, se lo elimina, sino, error. Falta hacer la parte de jugadores.
	public void eliminarPersonaje (int idPersonaje) {
		Personaje personaje = obtenerPersonaje(idPersonaje);
		
		if (personaje != null) {
			personajes.removeElement(personaje);	
			System.out.println ("El personaje " + personaje.getNombre() + " se ha eliminado correctamente");
		}
		
		else System.out.println ("El personaje ingresado no existe");
		System.out.println("-----------------------------------");
		System.out.println();
	
	}
	
	//Recorro el vector de personajes y retorno el que se busca, si existe...
	private Personaje obtenerPersonaje(int idPersonaje){
		for(Personaje personajeABuscar : personajes){
			if(personajeABuscar.getIdPersonaje() == idPersonaje){
				return personajeABuscar;
			}
		}
		return null;
	}
	
	
	//Getters y Setters
	public int getIdJugador() {
		return idJugador;
	}


	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Vector<Personaje> getPersonajes() {
		return personajes;
	}


	public void setPersonajes(Vector<Personaje> personajes) {
		this.personajes = personajes;
	}
	
	
	//Recorro vector de personajes e imprimo en pantalla cada uno, llamando al metodo toString.
	public void mostrarPersonaje(){
		for (int i=0; i< personajes.size(); i++){
			personajes.get(i).toStringPersonaje();
		}
	}
	
	
}
