package usuario;

import java.util.Vector;

import compraYVenta.Compra;
import compraYVenta.Venta;
import categoria.Categoria;
import plataforma.Producto;
import plataforma.Publicacion;
import plataforma.Usuario;
import subasta.SubastaBronce;
import subasta.SubastaOro;
import ventaDirecta.VentaDirBronce;
import ventaDirecta.VentaDirOro;

public class UsuarioGold extends Usuario {

	public UsuarioGold(String nombreUsuario, String email) {
		super(nombreUsuario, email);
		// TODO Auto-generated constructor stub
	}
	
	public UsuarioGold(String nombreUsuario, String email, double reputacion,
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
		
		Publicacion publicacion = new VentaDirOro (this, categoria, producto,
			titulo, precio, stock, descripcion);
		
		publicaciones.add(publicacion);
		categoria.getPublicaciones().add(publicacion);
	}
	
	@Override
	protected void crearSubasta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion) {
		// TODO Auto-generated method stub

		Publicacion publicacion = new SubastaOro (this, categoria, producto,
			titulo, precio, stock, descripcion);
			
		publicaciones.add(publicacion);
		categoria.getPublicaciones().add(publicacion);
	}
	
	@Override
	public Usuario upgrade() {
		// TODO Auto-generated method stub
		if (this.cantidadVentas>=10 || this.facturacion >= 1000000){
			Usuario userUpgraded = new UsuarioPlatinum(this.nombreUsuario, this.email ,
											this.reputacion, this.cantidadVentas, this.facturacion, 
											this.publicaciones, this.productos, this.compras ,this.ventas);
			
			return userUpgraded;
		}
		
		else return null;
	}

	
}
