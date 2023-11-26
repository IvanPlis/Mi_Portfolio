package publicacion;

import java.util.Vector;

import compraYVenta.Puja;
import categoria.Categoria;
import plataforma.Producto;
import plataforma.Publicacion;
import plataforma.Usuario;

abstract public class Subasta extends Publicacion {

	protected Usuario ultimoUserQueOferto;
	protected Vector<Puja> pujas;
	
	public Subasta(Usuario usuario, Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion) {
		super(usuario, categoria, producto, titulo, precio, stock, descripcion);
		this.pujas = new Vector <Puja>();
	}

	public void realizarOperacion(Usuario comprador, int cantidad){
		Publicacion publicacionAux = this;
		comprador.pujar(publicacionAux);
		recibirPuja(publicacionAux, comprador);
		setPrecio(getPrecio()+(getPrecio()/5));
		setUltimoUserQueOferto(comprador);
	}
	
	//Crea una puja y la agrega al vector de pujas realizadas de la subasta.
	public void recibirPuja(Publicacion publicacionAux, Usuario comprador){
		Puja puja = new Puja (publicacionAux, comprador);
		pujas.add(puja);
	}

	public Usuario getUltimoUserQueOferto() {
		return ultimoUserQueOferto;
	}

	public void setUltimoUserQueOferto(Usuario ultimosUserQueOferto) {
		this.ultimoUserQueOferto = ultimosUserQueOferto;
	}
	
}
