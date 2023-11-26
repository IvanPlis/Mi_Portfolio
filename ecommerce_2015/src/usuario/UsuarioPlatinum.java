package usuario;

import java.util.Vector;

import compraYVenta.Compra;
import compraYVenta.Venta;

import categoria.Categoria;
import plataforma.Producto;
import plataforma.Publicacion;
import plataforma.Usuario;
import subasta.SubastaBronce;
import subasta.SubastaPlatino;
import ventaDirecta.VentaDirBronce;
import ventaDirecta.VentaDirPlatino;

public class UsuarioPlatinum extends Usuario{

	public UsuarioPlatinum(String nombreUsuario, String email) {
		super(nombreUsuario, email);
		// TODO Auto-generated constructor stub
	}

	public UsuarioPlatinum(String nombreUsuario, String email, double reputacion,
			int cantidadVentas, double facturacion,
			Vector<Publicacion> publicaciones, Vector<Producto> productos,
			Vector<Compra> compras, Vector<Venta> ventas) {
		super(nombreUsuario, email);
		// TODO Auto-generated constructor stub
		this.reputacion = reputacion;
		this.cantidadVentas = cantidadVentas;
		this.facturacion = facturacion;
		this.publicaciones = publicaciones;
		this.productos = productos;
		this.compras = compras;
		this.ventas = ventas;
	}
	
	@Override
	public void crearPublicacion(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion,
			boolean ventaDirecta) {
		// TODO Auto-generated method stub
		
		Publicacion publicacionABuscar = buscarPublicacion(titulo);
	
			if (publicacionABuscar == null) {
				
				if (ventaDirecta == true) {	
					crearVentaDirecta(categoria, producto,
						titulo, precio, stock, descripcion);
				}
				
				else crearSubasta(categoria, producto,
					titulo, precio, stock, descripcion);	
		
		}
		
		else mostrarErrorPublicacionYaExisteUsuario(titulo);
		
	}

	
	@Override
	protected void crearVentaDirecta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion) {
		// TODO Auto-generated method stub
		
		Publicacion publicacion = new VentaDirPlatino (this, categoria, producto,
			titulo, precio, stock, descripcion);
		
		publicaciones.add(publicacion);
		categoria.getPublicaciones().add(publicacion);
	}
	
	@Override
	protected void crearSubasta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion) {
		// TODO Auto-generated method stub

		Publicacion publicacion = new SubastaPlatino (this, categoria, producto,
			titulo, precio, stock, descripcion);
			
		publicaciones.add(publicacion);
		categoria.getPublicaciones().add(publicacion);
	}

	
	//No se puede upgradear mas.
	@Override
	public Usuario upgrade() {
		// TODO Auto-generated method stub
		return null;
	}
	


	
}
