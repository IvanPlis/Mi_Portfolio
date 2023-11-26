package compraYVenta;

import plataforma.Publicacion;
import plataforma.Usuario;

public class Puja {
	
	private Publicacion publicacion;
	private Usuario usuario;
	private double montoOfertado;
	
	//Objeto puja del comprador (no requiere almacenar el usuario que puja)
	public Puja(Publicacion publicacion) {
		super();
		this.publicacion = publicacion;
		this.montoOfertado = publicacion.getPrecio()+(publicacion.getPrecio()/5);
	}
	
	//Objeto puja de la publicacion. Requiere almacenar al usuario que pujo.
	public Puja(Publicacion publicacion, Usuario pujador) {
		super();
		this.publicacion = publicacion;
		this.usuario = pujador;
		this.montoOfertado = publicacion.getPrecio()+(publicacion.getPrecio()/5);
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getMontoOfertado() {
		return montoOfertado;
	}

	public void setMontoOfertado(double montoOfertado) {
		this.montoOfertado = montoOfertado;
	}
	
	
	
}
