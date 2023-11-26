package principal;

import itemArma.*;
import itemPocion.*;
import partida.*;
import personajeHeroe.*;
import personajeVillano.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Partida partida = new Partida ("hola");
		
		//Todavia null, porque no esta hecha la parte de items
		Personaje personaje = new Guerrero ("pj1", 1, null);
		Personaje personaje2 = new Escudero ("pj2", 2, null);
		Personaje personaje3 = new Orco("pj3", 3, null);
		Personaje personaje4 = new Mago ("pj4", 4, null);
		
		
		Jugador jugador1 = partida.nuevoJugador("Pepe", 1, personaje);
		Jugador jugador2 = partida.nuevoJugador("Jose", 2, personaje2);
		Jugador jugador3 = partida.nuevoJugador("Papa frita", 3, personaje3);
		Jugador jugador4 = partida.nuevoJugador("Osvaldo", 4, personaje4);
		
		
		partida.listarJugadores();	
		partida.eliminarJugador(2);
		partida.listarJugadores();
		
		partida.listarPersonajes();
		
		
		/*	
		int a = pj1.getVida();
		System.out.println (a);
		pj1.setVida(a-10);
		int b = pj1.getVida();
		System.out.println (b);
		*/

		
	}

}

