package usuario;
import negocios.Heroe;
import negocios.Nivel;
import negocios.Personaje;
import negocios.Villano;
import processing.core.PApplet;
import processing.core.PFont;

public class Pantalla extends PApplet {
	
	boolean flagMovIzqHeroe = false;
	boolean flagMovDerHeroe = false;
	boolean flagSaltarHeroe = false;
	boolean flagRecargarHeroe = false;
	boolean flagDispararHeroeDer = false;
	boolean flagDispararHeroeIzq = false;
	
	boolean flagMovIzqVillano = false;
	boolean flagMovDerVillano = false;
	boolean flagSaltarVillano = false;
	boolean flagRecargarVillano= false;
	boolean flagDispararVillanoDer = false;
	boolean flagDispararVillanoIzq = false;
	

	PFont datos = createFont ("Arial", 20, true);
	
	Nivel actual;
	private static int numeroNivelActual;
	private static int cantNiveles = 2;
	
	Personaje heroe = new Heroe (20, 420);
	Villano villano = new Villano (450, 420);
	
	
	public void settings() {
		
		size(500, 500);
	  
	}

	
	public void setup(){
		

		background(59, 131, 189);
		PFont datos = createFont ("Arial", 18, true);
		
		Pantalla.numeroNivelActual = 1;
		
		this.actual = new Nivel(Pantalla.numeroNivelActual, this.heroe, this.villano);
		
		this.crearPlataformas1();
		
	}
	
	
	
	//Dibujo el nivel con sus atributos, ademas de los datos del personaje. Verifico si hay teclas activadas: Compruebo
	//el estado de la partida. Si esta termino, creo el siguiente nivel con otras plataformas, y reseteo los atributros de lso
	//personajes
	public void draw(){
		
		background(59, 131, 189);
		this.actual.dibujar(this);
		
		this.mostrarDatosHeroe();
		this.mostrarDatosVillano();
		
		verificarTecla();
		
		if (actual.partidaTerminada() == true){
			
			if (Pantalla.numeroNivelActual < Pantalla.cantNiveles)	{
				
				Pantalla.numeroNivelActual++;
				System.out.println(Pantalla.numeroNivelActual);
				
				this.resetearPersonajes();
				this.actual = new Nivel(Pantalla.numeroNivelActual, this.heroe, this.villano);
				this.crearPlataformas2();				
				
			}
		}
		
	}
	
	public void resetearPersonajes(){
		this.heroe.setLimBalas(5);
		this.heroe.setVida(100);
		this.heroe.setX(20);
		this.heroe.setY(420);
		
		this.villano.setLimBalas(5);
		this.villano.setVida(100);
		this.villano.setX(450);
		this.villano.setY(420);
	}
	
	
	public void crearPlataformas1(){
		
		actual.crearPlataforma(100, 130, 40);
		actual.crearPlataforma(145, 170, 40);
		actual.crearPlataforma(235, 230, 40);
		actual.crearPlataforma(100, 290, 40);
		actual.crearPlataforma(325, 320, 40);
		actual.crearPlataforma(120, 340, 40);
		actual.crearPlataforma(415, 350, 40);
		actual.crearPlataforma(325, 100, 40);
		actual.crearPlataforma(260, 300, 40);
		actual.crearPlataforma(400, 80, 40);
		actual.crearPlataforma(150, 410, 40);
		actual.crearPlataforma(50, 310, 40);
		actual.crearPlataforma(250, 410, 40);
		actual.crearPlataforma(350, 410, 40);
		actual.crearPlataforma(350, 50, 40);
		
	}
	
	public void crearPlataformas2(){
		
		actual.crearPlataforma(100, 130, 40);
		actual.crearPlataforma(125, 170, 40);
		actual.crearPlataforma(265, 230, 40);
		actual.crearPlataforma(100, 350, 40);
		actual.crearPlataforma(355, 320, 40);
		actual.crearPlataforma(310, 100, 40);
		actual.crearPlataforma(405, 350, 40);
	}
	
	
	//Muestra los datos del heroe en pantalla, con la caracteristica de cambiar el valor del timer solo cuando
	//este es un numero redondo.
	public void mostrarDatosHeroe(){
		textFont(datos,16);
		fill(215, 215, 215);
		text("Vida: " + actual.getHeroe().getVida(), 20, 470);
		text("Balas: " + actual.getHeroe().getLimBalas(), 20, 485);
		
		if (actual.getHeroe().getTimerRecarga() <= 600 &&
				actual.getHeroe().getTimerRecarga() > 500)
			text("Recarga en: " + 5 , 100, 485);
		
		if (actual.getHeroe().getTimerRecarga() <= 500 &&
				actual.getHeroe().getTimerRecarga() > 400)
			text("Recarga en: " + 4 , 100, 485);
		
		if (actual.getHeroe().getTimerRecarga() <= 400 &&
				actual.getHeroe().getTimerRecarga() > 300)
			text("Recarga en: " + 3 , 100, 485);
		
		if (actual.getHeroe().getTimerRecarga() <= 300 &&
				actual.getHeroe().getTimerRecarga() > 200)
			text("Recarga en: " + 2 , 100, 485);
		
		if (actual.getHeroe().getTimerRecarga() <= 200 &&
				actual.getHeroe().getTimerRecarga() > 100)
			text("Recarga en: " + 1 , 100, 485);
		
		if (actual.getHeroe().getTimerRecarga() <= 100)
			text("Recarga en: " + "YA" , 100, 485);	
		
	}
	
	//IDEM arriba
	public void mostrarDatosVillano(){
		
		fill(200, 37, 18);
		text("Vida: " + actual.getVillano().getVida(), 410, 470);
		text("Balas: " + actual.getVillano().getLimBalas(), 410, 485);
		
		if (actual.getVillano().getTimerRecarga() <= 600 &&
				actual.getVillano().getTimerRecarga() > 500)
			text("Recarga en: " + 5 , 280, 485);
		
		if (actual.getVillano().getTimerRecarga() <= 500 &&
				actual.getVillano().getTimerRecarga() > 400)
			text("Recarga en: " + 4 , 280, 485);
		
		if (actual.getVillano().getTimerRecarga() <= 400 &&
				actual.getVillano().getTimerRecarga() > 300)
			text("Recarga en: " + 3 , 280, 485);
		
		if (actual.getVillano().getTimerRecarga() <= 300 &&
				actual.getVillano().getTimerRecarga() > 200)
			text("Recarga en: " + 2 , 280, 485);
		
		if (actual.getVillano().getTimerRecarga() <= 200 &&
				actual.getVillano().getTimerRecarga() > 100)
			text("Recarga en: " + 1 , 280, 485);
		
		if (actual.getVillano().getTimerRecarga() <= 100)
			text("Recarga en: " + "YA" , 280, 485);	
		
	}
		
		

	//Si algun flag esta activado, llamo al metodo de su respectivo movimiento.
	public void verificarTecla (){
		
		if (flagMovIzqHeroe) actual.moverIzq(heroe);
		if (flagMovDerHeroe) actual.moverDer(heroe);	
		if (flagSaltarHeroe) actual.saltar(heroe);
		if (flagDispararHeroeDer) actual.disparar(heroe, true);
		if (flagDispararHeroeIzq) actual.disparar(heroe, false);
		
		if (flagMovIzqVillano) actual.moverIzq(villano);
		if (flagMovDerVillano) actual.moverDer(villano);	
		if (flagSaltarVillano) actual.saltar(villano);
		if (flagDispararVillanoDer) actual.disparar(villano, true);
		if (flagDispararVillanoIzq) actual.disparar(villano, false);
	}
	
	//Cada vez que mantenga apretada una tecla, compruebo si esta le corresponde a algun movimiento.
	public void keyPressed() {
		
		this.activarMovimientosHeroe();
		this.activarMovimientosVillano();
		this.recargar();
		
		
	}
	
	//IDEM arriba pero si desactivo cuando la suelto
	public void keyReleased (){
		
		desactivarMovimientosHeroe();
		desactivarMovimientosVillano();
		
	}
	
	//Mientras una tecla se mantenga apretada, el flag de usu movimiento correspondiente se activa, para que en el metodo
	//verificarTecla se llame al metodo de movimiento correspondiente a la tecla.
	public void activarMovimientosHeroe(){
		
		if (key == 'w')	flagSaltarHeroe = true;	
		if (key == 'a')	flagMovIzqHeroe = true;
		if (key == 'd')	flagMovDerHeroe = true;
		if (key == '.')	flagDispararHeroeDer = true;
		if (key == ',')	flagDispararHeroeIzq = true;
	}
	
	public void activarMovimientosVillano(){
		
		if (keyCode == UP)	flagSaltarVillano = true;	
		if (keyCode == LEFT)	flagMovIzqVillano = true;
		if (keyCode == RIGHT)	flagMovDerVillano = true;
		if (key == '6')	flagDispararVillanoDer = true;
		if (key == '4')	flagDispararVillanoIzq = true;
	}
	
	
	//Si suelto, el flag es false y no se produce movimiento hasta que vuelva a apretar.
	public void desactivarMovimientosHeroe(){
		
		if (key == 'w') flagSaltarHeroe = false;	
		if (key == 'a')	flagMovIzqHeroe = false;
		if (key == 'd')	flagMovDerHeroe = false;
		if (key == '.')	flagDispararHeroeDer = false;
		if (key == ',')	flagDispararHeroeIzq = false;
	}

	public void desactivarMovimientosVillano(){
		
		if (keyCode == UP)	flagSaltarVillano = false;
		if (keyCode == LEFT)	flagMovIzqVillano = false;
		if (keyCode == RIGHT)	flagMovDerVillano = false;
		if (key == '6')	flagDispararVillanoDer = false;
		if (key == '4')	flagDispararVillanoIzq = false;
	}
	
	
	public void recargar(){
		if (key == 'r') actual.getHeroe().recargar();
		if(keyCode == DOWN) actual.getVillano().recargar();
		
	}

	
	public static void main(String args[]){
	    PApplet.main(new String[] { "--present", "Pantalla" });
	}
}