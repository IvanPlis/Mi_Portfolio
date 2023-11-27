package negocios;
import java.util.Vector;

import processing.core.PApplet;


public class Nivel implements Dibujable{
	
	private Vector <Plataforma> plataformas;
	private Personaje heroe;
	private Personaje villano;
	private Abismo abismo;
	private static int numeroNivel=0;
	
	public Nivel(int numeroNivel, Personaje heroe, Personaje villano) {
		
		this.plataformas = new Vector <Plataforma> ();
		this.abismo = new Abismo();
		Nivel.numeroNivel++;
		this.heroe=heroe;
		this.villano=villano;
		
	}
	
	//Dibujo todos los atributos que tiene un nivel. Tambien llamo a los metodos que comprueban colisiones.
	public void dibujar (PApplet pantalla){
		this.dibujarPersonajes(pantalla);
		this.dibujarPlataformas(pantalla);
		
		this.dibujarAbismo(pantalla);
		
		for (Plataforma plataforma: this.plataformas){
			heroe.volverAlPisoObstaculo(plataforma);
			villano.volverAlPisoObstaculo(plataforma);
		}
		
		this.comprobarAtaqueBala(this.heroe, this.villano);
		this.comprobarAtaqueBala(this.villano, this.heroe);
		this.chocoDerBalaPlataforma(heroe);
		this.chocoDerBalaPlataforma(villano);
		this.partidaTerminada();
	}
	
	public void dibujarPlataformas(PApplet pantalla){
		for (Plataforma plataforma: this.plataformas){
			plataforma.dibujar(pantalla);
		}
	}
	
	public void dibujarPersonajes (PApplet pantalla){
		this.heroe.dibujar(pantalla);
		this.villano.dibujar(pantalla);
	}
	
	public void dibujarAbismo (PApplet pantalla){
		abismo.dibujar(pantalla);
	}
	
	public void saltar (Personaje personaje){
		personaje.saltar();
	}
	
	public void disparar (Personaje personaje, boolean direccion){
		personaje.disparar(direccion);
	}
	
	public void recargar (Personaje personaje){	
		personaje.recargar();
	}
	
	//Creo una plataforma nueva y la agrego al vector de plataformas del nivel.
	public void crearPlataforma (int x, int y, int lado){
		Plataforma plataforma = new Plataforma (x, y, lado);
		this.agregarPlataforma(plataforma);
	}
	
	public void agregarPlataforma (Plataforma plataforma){
		plataformas.add(plataforma);
	}
	
	
	//Le descuenta un punto de "integridad" a la plataforma. Si la misma llega a cero, la elimino del vector.
	public void comprobarEstadoPlataforma (Plataforma plataforma){
		plataforma.setEstado(plataforma.getEstado()-1);
			
		if(plataforma.getEstado() == 0){
			this.eliminarPlataforma(plataforma);
		}
	}
	
	public void comprobarSalto(Personaje personaje){
			personaje.comprobarSalto();
	}
	
	
	public void eliminarPlataforma(Plataforma plataforma){
		this.plataformas.removeElement(plataforma);
	}
	
	//Muevo un personaje a la izquiera. El acumulador i es para comprobar que antes de que un perosnaje se mueva,
	//no haya ninguna colision con ninguna plataforma.
	public void moverIzq(Personaje personaje){
		int i = 0;
		
		for (Plataforma obstaculo: this.plataformas){
			if( personaje.chocoIzq(obstaculo) == false){
				i++;
			}
		}
		
		if (i==plataformas.size()) personaje.moverIzq();
	}
	
	
	public void moverDer(Personaje personaje){
		
		int i = 0;
		
		for (Plataforma obstaculo: this.plataformas){
			if( personaje.chocoDer(obstaculo) == false){
				i++;
			}
		}
		
		if (i==plataformas.size()) personaje.moverDer();
	}
	
	
	public void comprobarAtaqueBala(Personaje atacante, Personaje atacado){
		chocoDerBala(atacante, atacado);
		chocoIzqBala(atacante, atacado);
	}
	
	
	//Comprueba la colosion de las balas de un personaje contra otro.. De producirse, se elimina la bala del vector de 
	//balas del personaje atacante, y se le resta vida al pj que recibio el impacto.
	public boolean chocoDerBala(Personaje atacante, Personaje atacado){
		
		for (Bala bala: atacante.getBalas()){
			
			if (atacado.getX() <= bala.getX() &&
				atacado.getX() + atacado.getLado()  >= bala.getX() + bala.getLado() &&
				atacado.getY() <= bala.getY() &&
				atacado.getY() + atacado.getLado() >= bala.getY() + bala.getLado()){
				
				
				atacado.setVida(atacado.getVida()-bala.getDanio());
				atacante.eliminarBala(bala);
			
				return true;		
			}
		}
		
		return false;
	}
	
	public boolean chocoIzqBala(Personaje atacante, Personaje atacado){
	
		for (Bala bala: atacante.getBalas()){
			
			if (atacado.getX() >= bala.getX() &&
				atacado.getX() <= bala.getX() + bala.getLado() &&
				atacado.getY() >= bala.getY() &&
				atacado.getY() <= bala.getY() + bala.getLado()){
				
				
				atacado.setVida(atacado.getVida()-bala.getDanio());
				atacante.eliminarBala(bala);
				return true;	
			}
		}
		
		return false;
	}
	
	//IDEM arriba, pero comprueba con las plataformas y resta puntos de "integridad" de haber colision.
	public boolean chocoDerBalaPlataforma (Personaje personaje){
		
		for (Bala bala: personaje.getBalas()){
			for (Plataforma plataforma: this.plataformas){
				
				if (plataforma.getX() <= bala.getX() &&
					plataforma.getX() + plataforma.getLado()  >= bala.getX() + bala.getLado() &&
					plataforma.getY() <= bala.getY() &&
					plataforma.getY() + plataforma.getLado() >= bala.getY() + bala.getLado()){	
					
					System.out.println(bala.getX());	
					
					personaje.eliminarBala(bala);
					this.comprobarEstadoPlataforma(plataforma);
					
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	//Si algun pj pierde los 100pts de vida, temrina la partida.
	public boolean partidaTerminada(){
		if (this.heroe.getVida()<=0 || this.villano.getVida()<=0){
			return true;
		}
		
		return false;
	}
	
	
	public Vector<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(Vector<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}

	public Abismo getAbismo() {
		return abismo;
	}

	public void setAbismo(Abismo abismo) {
		this.abismo = abismo;
	}

	public static int getNumeroNivel() {
		return numeroNivel;
	}

	public static void setNumeroNivel(int numeroNivel) {
		Nivel.numeroNivel = numeroNivel;
	}
	

	
	public Personaje getHeroe() {
		return heroe;
	}

	
	public void setHeroe(Personaje heroe) {
		this.heroe = heroe;
	}
	

	public Personaje getVillano() {
		return villano;
	}
	
	
	public void setVillano(Personaje villano) {
		this.villano = villano;
	}
	
}
