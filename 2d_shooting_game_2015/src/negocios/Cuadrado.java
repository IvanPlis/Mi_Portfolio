package negocios;
import processing.core.PApplet;

public class Cuadrado implements Dibujable {

	int x,y,lado, colorR, colorG, colorB;
	

	public Cuadrado(int x, int y, int lado, int colorR, int colorG, int colorB) {
		super();
		this.x = x;
		this.y = y;
		this.lado = lado;
		this.colorR = colorR;
		this.colorG = colorG;
		this.colorB = colorB;
	}

	public int getColorR() {
		return colorR;
	}

	public void setColorR(int colorR) {
		this.colorR = colorR;
	}

	public int getColorG() {
		return colorG;
	}

	public void setColorG(int colorG) {
		this.colorG = colorG;
	}

	public int getColorB() {
		return colorB;
	}

	public void setColorB(int colorB) {
		this.colorB = colorB;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	@Override
	public void dibujar(PApplet pantalla) {
		pantalla.color(this.colorR, this.colorG, this.colorB);
		pantalla.fill(this.colorR, this.colorG, this.colorB);
		pantalla.rect(this.x,this.y,this.lado,this.lado);
		
	}
	
}
	