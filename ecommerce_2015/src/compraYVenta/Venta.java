package compraYVenta;

import plataforma.Publicacion;
import plataforma.Usuario;

public class Venta {

	private Publicacion publicacion;
	private Usuario comprador;
	private int cantidad;
	
	public Venta (Publicacion publicacion, Usuario comprador,
			int cantidad) {
		super();
		this.publicacion = publicacion;
		this.cantidad = cantidad;
		this.comprador = comprador;
	}
	
	
	public void mostrarDatos(){
		System.out.println("Publicacion: " + this.publicacion);
		System.out.println("Comprador: " + this.comprador);
		System.out.println("Cantidad: " + this.cantidad);
	}


	public Publicacion getPublicacion() {
		return publicacion;
	}


	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}


	public Usuario getComprador() {
		return comprador;
	}


	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
