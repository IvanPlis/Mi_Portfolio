package ventaDirecta;
import categoria.Categoria;
import plataforma.Producto;
import plataforma.Usuario;
import publicacion.VentaDirecta;


 public class VentaDirBronce extends VentaDirecta {

	public VentaDirBronce(Usuario usuario, Categoria categoria,
			Producto producto, String titulo, double precio, int stock,
			String descripcion) {
		super(usuario, categoria, producto, titulo, precio, stock, descripcion);
		// TODO Auto-generated constructor stub
	}


}
