package publicacion;

import categoria.Categoria;
import plataforma.Producto;
import plataforma.Publicacion;
import plataforma.Usuario;

abstract public class VentaDirecta extends Publicacion{

	public VentaDirecta(Usuario usuario, Categoria categoria,
			Producto producto, String titulo, double precio, int stock,
			String descripcion) {
		super(usuario, categoria, producto, titulo, precio, stock, descripcion);
		// TODO Auto-generated constructor stub
		
	}
	
	//Crea una publicacion auxiliar para guardarla en el objeto compra y en el venta. Despues, llama al metodo comprar del
	//user comprador, al vender del vendedor (es el atributo usuario de la instancia de publicacion) y actualiza stock.
	public void realizarOperacion(Usuario comprador, int cantidad){	
		Publicacion publicacionAux = this;
		comprador.comprar(publicacionAux, cantidad);
		usuario.vender(publicacionAux, comprador, cantidad);
		actualizarStock(cantidad);
	}

}
