package partida;
import java.util.Vector;

//EL TP esta incompleto. Tiene casi todo lo referente a personajes y jugadores (menos los metodos
//de agregar multiples PJs a un jugador y eliminar personaje), y la base de los tipos de personajes e items.
//Faltan los metodos de  personajes e items (que son todos mas o menos iguales) y lo otro mencionado arriba.

//Lo faltante no fue hecho por falta de tiempo, espero que entienda.

public class Partida {
	
	//La partida puede tener muchos jugadores.
	
	private String nombrePartida;
	private Vector<Jugador> jugadores;
	
	public Partida(String nombrePartida) {
		super();
		this.setNombrePartida(nombrePartida);
		this.jugadores = new Vector<Jugador>();
	}
	

	//Ingreso los datos de un nuevo jugador. Compruebo que no exista, y lo creo. Sino, error.
	public Jugador nuevoJugador (String nombre, int idJugador, Personaje personaje){
		
		Jugador jugadorABuscar = buscarJugador(idJugador);
		
		if (jugadorABuscar == null) {
			Jugador jugador = new Jugador(nombre, idJugador, personaje);
			jugadores.add(jugador);
			return jugadorABuscar;
		}	
		
		else {
			System.out.println("El Jugador con ID=" + idJugador +" ya existe");
			System.out.println("-----------------------------------");
			return null;
		}	
	}
	
	
	//Recorro el vector de jugadores y encuentro el que buscar el usuario.
	private Jugador buscarJugador(int idJugador){
		for(Jugador jugadorABuscar : jugadores){
			if(jugadorABuscar.getIdJugador() == idJugador){
				return jugadorABuscar;
			}
		}
		return null;
	}
	
	//Se ingresa un jugador, se lo busca, y si existe, se lo elimina.
	public void eliminarJugador (int idJugador) {
		Jugador jugador = buscarJugador(idJugador);
		
		if (jugador!=null) {
			jugadores.removeElement(jugador);	
			System.out.println ("El Jugador " + jugador.getNombre() + " se ha eliminado correctamente");
		}
		
		else System.out.println ("El Jugador ingresado no existe");
		System.out.println("-----------------------------------");
		System.out.println();
	
	}
	
	//Recorro vector de jugadores para encontrar todos los personajes.
	public void listarJugadores(){	
		for (int i=0; i< jugadores.size(); i++){
			jugadores.get(i).toStringJugador();
		}
	}
	
	
	public void listarPersonajes(){
		for (int i=0; i< jugadores.size(); i++){
			jugadores.get(i).mostrarPersonaje();
		}
	}


	public String getNombrePartida() {
		return nombrePartida;
	}


	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}

}
