package negocios;
import processing.core.PApplet;


public class Plataforma extends Cuadrado implements Dibujable{

	public int estado;
	
	public Plataforma(int x, int y, int lado) {
		super(x, y, lado, 130, 108, 53);
		this.estado=3;
	}
	

	public void dibujar(PApplet pantalla){	
		super.dibujar(pantalla);
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
