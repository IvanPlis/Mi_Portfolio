package negocios;
import java.util.Iterator;
import java.util.Vector;
import processing.core.PApplet;

abstract public class Personaje extends Cuadrado implements Movible, Disparante{

	
	protected Vector<Bala> balas;
	protected int limBalas;
	protected int velSalto;
	protected int velGravedad;
	protected boolean saltando;
	private int vida;
	private int timerRecarga;

	
	public Personaje(int x, int y, int colorR, int colorG,
			int colorB) {
		super(x, y, 30, colorR, colorG, colorB);
		this.balas = new Vector<Bala>();
		this.limBalas = 5;
		this.velSalto = 0;
		this.velGravedad = 0;
		this.saltando = false;
		this.vida = 100;
		this.timerRecarga=600;
	}
	
	
	//Dibuja al personaje y comprueba sus movimientos. Ademas, resta segundos del timer de recarga.
	public void dibujar(PApplet pantalla){	
		super.dibujar(pantalla);
		
		this.comprobarSalto();
		this.gravedad();
		this.volverAlPiso();
		this.comprobarDisparo(pantalla);
		
		if (this.timerRecarga>0){
			this.timerRecarga--;
		}
		
	}
	
	public void saltar(){
		if (this.saltando==false){
			this.velSalto = -25;
			this.saltando = true;
		}
	}	

	//Se crea una nueva bala si el personaje tiene espacio en el "cargador" para crear una. Se le especifica
	//una direccion.
	public void disparar(boolean direccion){
		if (this.limBalas > 0)	{
			balas.add(new Bala(this, direccion));
			this.limBalas--;
		}
	}
	
	//Si hay espacio en el cargador y pasaron los 5 segundos de tiempo de espera por recarga, se llena
	//se reserva el espacio en el cargador para las bales faltantes y se resetea el timer.
	public void recargar(){
		if (this.limBalas<5 && this.timerRecarga <= 100){
			
			int balasFaltantes = 5-limBalas;
			this.limBalas= this.limBalas+balasFaltantes;
			this.timerRecarga=600;
			
		}
	
	}
	
	//Se dibuja la bala de un personaje en pantalla.
	public void comprobarDisparo(PApplet pantalla){
		Iterator<Bala> iterador = this.balas.iterator();
		
		while (iterador.hasNext())	{
			
			Bala actual = iterador.next();
			actual.dibujar(pantalla);
			
			if (actual.getX()>500 ){
				iterador.remove();
			}
		}
	}
	
	//Si se llama desde pantalla al metodo saltar, aca se Aumenta la velocidad de salto hasta que llegue a 25
	//o salga de la pantalla.
	public void comprobarSalto(){
		if (saltando == true){
			this.y += this.velSalto;
			this.velSalto++;
			
			if (this.velSalto == 25 || this.getX() <=0){
				saltando = false;
			}
	
		}	
		
	}
	
	//Mientras no haya llegado al piso, el pj tiene que caer.
	public void gravedad(){
		
		if (this.y+this.lado < 450){
			
			this.velGravedad++;
			this.y+=this.velGravedad;
			
		}
	}
	
	//Si se produce solo una colision sobre una plataforma, desactivo la graveda y ubico al personaje sobre la misma.
	public void volverAlPisoObstaculo(Plataforma obstaculo){
		
		if (chocoIzq(obstaculo) == false && chocoDer(obstaculo)==false){	
			if (chocoSalto(obstaculo)==true){
				velGravedad = 0;
				this.y=obstaculo.getY()-this.lado;
			}
		}
	}

	//Si choco con el piso me quedo ahi
	public void volverAlPiso (){
		if (this.y+this.lado >= 450){
			this.velGravedad=0;
			this.y=450-this.lado;
		}
	}

	public void moverIzq() {
		if(this.x>0 ){
			this.x=this.x-4;
		}	
	}
	
	public void moverDer(){	
		if(this.x+this.lado < 500){
			this.x=this.x+4;
		}	
	}
	
	//Compruebo choque de un pj con alguna plataforma por izq, derecha y por arriba.
	public boolean chocoIzq(Plataforma obstaculo){
		
		if (this.x >= obstaculo.getX() &&
			this.x <= obstaculo.getX() + obstaculo.getLado() &&
			this.y >= obstaculo.getY() &&
			this.y <= obstaculo.getY() + obstaculo.getLado()){
			return true;		
		}
		
		return false;
	}
	
	public boolean chocoDer(Plataforma obstaculo){
	
		if (this.x+this.lado >= obstaculo.getX() &&
			this.x+this.lado <= obstaculo.getX() + obstaculo.getLado() &&
			this.y >= obstaculo.getY() &&
			this.y <= obstaculo.getY() + obstaculo.getLado()){
			
			return true;	
		}		
		
		return false;
	}
	
	
	public boolean chocoSalto(Plataforma obstaculo){
		
		if (this.x >= obstaculo.getX() &&
			this.x <= obstaculo.getX() + obstaculo.getLado() &&
			this.y+this.lado >= obstaculo.getY() &&
			this.y+this.lado <= obstaculo.getY() + obstaculo.getLado())	{
			
				
			return true;
		}
		
	//vertice derecha abajo
		if (this.x+this.lado >= obstaculo.getX() &&
			this.x+this.lado <= obstaculo.getX() + obstaculo.getLado() &&
			this.y+this.lado >= obstaculo.getY() &&
			this.y+this.lado <= obstaculo.getY() + obstaculo.getLado()){
			
			return true;
			
		}
		
		return false;
		
	}
	
	public void eliminarBala (Bala bala){
		this.balas.removeElement(bala);
	}
	
	
	public Vector<Bala> getBalas() {
		return balas;
	}

	public void setBalas(Vector<Bala> balas) {
		this.balas = balas;
	}

	public int getVelSalto() {
		return velSalto;
	}

	public void setVelSalto(int velSalto) {
		this.velSalto = velSalto;
	}

	public int getVelGravedad() {
		return velGravedad;
	}

	public void setVelGravedad(int velGravedad) {
		this.velGravedad = velGravedad;
	}

	public boolean isSaltando() {
		return saltando;
	}

	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	} 
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	
	public int getTimerRecarga() {
		return timerRecarga;
	}

	public void setTimerRecarga(int timerRecarga) {
		this.timerRecarga = timerRecarga;
	}

	public int getLimBalas() {
		return limBalas;
	}

	public void setLimBalas(int limBalas) {
		this.limBalas = limBalas;
	}
}
