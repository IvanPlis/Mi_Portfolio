package usuario;
import java.util.Vector;

import compraYVenta.Compra;
import compraYVenta.Venta;

import categoria.Categoria;
import plataforma.Producto;
import plataforma.Publicacion;
import plataforma.Usuario;
import subasta.SubastaBronce;
import ventaDirecta.VentaDirBronce;

public class UsuarioDefault extends Usuario {

	
	//LOS COMENTARIOS DE ACA SE APLICAN A TODAS LAS CLASES USUARIO(TIPO).
	
	
	public UsuarioDefault(String nombreUsuario, String email) {
		super(nombreUsuario, email);
		// TODO Auto-generated constructor stub
	}
	
	
	//Compruebo existencia de publicacion. Si no existe, chequeo si se requiere una venta directa o una subasta. Dependiendo
	//del boolean, llamo aL metodo para crear una u otra.
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

	//Creo una venta directa del tipo bronce (correspondiente a usuarioDefault, la agrego al vector de publicaciones 
	//del usuario y al vector de la categoria tomada como parametro.
	@Override
	protected void crearVentaDirecta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion) {
		// TODO Auto-generated method stub
		
		Publicacion publicacion = new VentaDirBronce (this, categoria, producto,
			titulo, precio, stock, descripcion);
		
		publicaciones.add(publicacion);
		categoria.getPublicaciones().add(publicacion);
	}
	
	//IDEM arriba pero creo una subasta.
	@Override
	protected void crearSubasta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion) {
		// TODO Auto-generated method stub

		Publicacion publicacion = new SubastaBronce (this, categoria, producto,
			titulo, precio, stock, descripcion);
			
		publicaciones.add(publicacion);
		categoria.getPublicaciones().add(publicacion);
	}


	//Si la cantidad de ventas es mayor a cero, creo un usuarioNormal enviando como parametros los
	//atributos de esta instancia, para asi realizar el upgrade.
	@Override
	public Usuario upgrade() {
		// TODO Auto-generated method stub
		if (this.cantidadVentas>0){
			Usuario userUpgraded = new UsuarioNormal(this.nombreUsuario, this.email ,
											this.reputacion, this.cantidadVentas, this.facturacion, 
											this.publicaciones, this.productos, this.compras ,this.ventas);
			
			return userUpgraded;
		}
		
		else return null;
	}
	
	
	

}
