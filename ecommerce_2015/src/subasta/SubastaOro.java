package subasta;

import categoria.Categoria;
import plataforma.Producto;
import plataforma.Usuario;
import publicacion.Subasta;

public class SubastaOro extends Subasta {

	public SubastaOro(Usuario usuario, Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion
			) {
		super(usuario, categoria, producto, titulo, precio, stock, descripcion);
		// TODO Auto-generated constructor stub
	}

}
