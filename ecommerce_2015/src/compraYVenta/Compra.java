package compraYVenta;
import plataforma.Publicacion;
import plataforma.Usuario;

public class Compra {
	
	private Publicacion publicacion;
	private int cantidad;
	
	public Compra(Publicacion publicacion, int cantidad) {
		super();
		this.publicacion = publicacion;
		this.cantidad = cantidad;
	}
	
	
	public void mostrarDatos(){
		System.out.println("Publicacion: " + this.publicacion);
		System.out.println("Cantidad: " + this.cantidad);
	}


	public Publicacion getPublicacion() {
		return publicacion;
	}


	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	


}
