package negocios;
import processing.core.PApplet;

public class Bala extends Cuadrado implements Dibujable{
	
	private boolean aLaDerecha;
	private int danio;
	
	public Bala(Personaje disparador, boolean aLaDerecha) {
		super((disparador.x+disparador.lado/2),
			  (disparador.y+disparador.lado/2), 5, 250, 210, 1);
		
		this.aLaDerecha = aLaDerecha;
		this.danio=10;
	}
	
	
	public void dibujar(PApplet pantalla){
		super.dibujar(pantalla);
		if (aLaDerecha== true){
			this.x=x+7;
		}
		
		else{
			this.x=x-7;
		}
	}


	public int getDanio() {
		return danio;
	}


	public void setDanio(int danio) {
		this.danio = danio;
	}
	
	
	
	
}
