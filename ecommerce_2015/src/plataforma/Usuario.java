package plataforma;

import java.util.Vector;

import compraYVenta.Compra;
import compraYVenta.Puja;
import compraYVenta.Venta;
import preguntaYRespuesta.Pregunta;
import publicacion.Subasta;
import publicacion.VentaDirecta;
import categoria.Categoria;


abstract public class Usuario {
	
	protected String nombreUsuario;
	protected String email;
	protected double reputacion;
	protected int cantidadVentas;
	protected double facturacion;
	protected Vector <Publicacion> publicaciones;
	protected Vector <Producto> productos;
	protected Vector <Compra> compras;
	protected Vector <Venta> ventas; 
	protected Vector <Puja> pujas;

	public Usuario(String nombreUsuario, String email) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.reputacion = 0;
		this.cantidadVentas = 0;
		this.facturacion = 0;
		this.publicaciones = new Vector <Publicacion>();
		this.productos = new Vector <Producto>();
		this.compras = new Vector <Compra>();
		this.ventas = new Vector <Venta>();
	}


	public void mostrarDatos() {
		System.out.println ("Nombre Usuario: " + this.nombreUsuario);
		System.out.println ("Email " + this.nombreUsuario + ": " + this.email);
		System.out.println ();
	}
	
	public void actualizarDatos (String nombreUsuario, String email){
		this.nombreUsuario=nombreUsuario;
		this.email=email;		
	}
	
	private void sumarVenta(){
		this.cantidadVentas++;
	}
	
	private void facturar(Publicacion publicacionAux){
		double facturaDeVenta = publicacionAux.getPrecio();
		this.facturacion= this.facturacion + facturaDeVenta;
	}
	
	abstract public Usuario upgrade();
	
	public String getUsername() {
		return nombreUsuario;
	}
	
	public void modificarUsername(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void modificarEmail(String email) {
		this.email = email;
	}

	//Comprueba existencia del producto en el usuario. Si np existe, lo creo y lo agrego al vector.
	void crearProducto (String nombreProducto, String descripcionProd, boolean estado) {
		Producto productoABuscar = buscarProducto (nombreProducto);
		
		if (productoABuscar == null) {	
			Producto producto = new Producto (nombreProducto, descripcionProd, estado, this);
			productos.add(producto);
		}
	}
	
	//Busca un producto recorriendo el vector, tomando como parametro el nombre del producto.
	public Producto buscarProducto (String nombreProducto) {
		for(Producto productoABuscar: productos){
			if(productoABuscar.getNombreProducto().equals(nombreProducto)){
				return productoABuscar;
			}
		}	
		return null;
	}
	
	//Recorro vector de productos para encontrar todos los productos.
	public void listarProductos() {		
		for (Producto producto : productos){
			producto.mostrarDatos();
		}
	}
	
	//Busca un producto, chequea su existencia y lo elimina.
	public void eliminarProducto (String nombreProducto) {
		
		Producto producto = buscarProducto(nombreProducto);
		
		if (producto != null) {
			productos.removeElement(producto);	
			System.out.println ("El producto " + producto.getNombreProducto() + " se ha eliminado correctamente");
			return;
		}
		
		
	}

	abstract public void crearPublicacion (Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion,
			boolean ventaDirecta);
	abstract protected void crearVentaDirecta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion);
	abstract protected void crearSubasta(Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion);
	
	//Recore el vector de publicaciones del usuario, comparando el titulo tomado como parametro con las ya existentes.
	//Si encuentra una coincidencia, retorna la publicacion existence. Sino, null.
	protected Publicacion buscarPublicacion(String titulo) {	
		for(Publicacion publicacionABuscar: publicaciones){
			if(publicacionABuscar.getTitulo().equals(titulo)){
				return publicacionABuscar;
			}
		}
		return null;
	}
	
	//Recorre el vector de publicaciones, comparando en cada instancia si alguna tiene como atributo un producto que coincida
	//con el como parametro. Si lo encuentra, lo retorna.
	public Publicacion buscarProductoEnPublicacion(String  nombreProducto){
		for (Publicacion publicacion: publicaciones){
			Producto productoABuscar = publicacion.getProducto();
			
			if (nombreProducto.equals(productoABuscar.getNombreProducto())){
				return publicacion;
			}
		}
		
		return null;
	}
	
	public void listarPublicaciones (){
		
		for (Publicacion publicacion: publicaciones) {
			publicacion.mostrarDatos();
		}
		
	}
	
	//Me fijo si el usuario quiere listar ventas directas o subastas segun el boolean. Recorro el vector de publicaciones
	//de un usuario. El insatance of lo uso para saber a que clase pertenece un objeto en este caso. 
	//Retorna true or false dependiendo si pertenece a la clase especificada o no.
	//Si pertenece, imprimo la publicacion em pantalla.
	public void listarPublicaciones (boolean ventaDirecta){
		
		if ( ventaDirecta == true ){
			for (Publicacion publicacion: publicaciones) {
				if (publicacion instanceof VentaDirecta == true) {
					publicacion.mostrarDatos();
				}
			}
		}
		
		else {
			for (Publicacion publicacion: publicaciones) {
				if (publicacion instanceof Subasta == true) {
					publicacion.mostrarDatos();
				}
			}
		}	
	}
	
	
	public void cancelarPublicacion (Publicacion publicacion) {
		publicacion.setEstado(false);
		System.out.println("La publicacion ->" + publicacion.getTitulo() + "<- ha sido cancelada");
		
	}
	
	//Chequea que exista la publicacion y que este activa. Despues, crea una pregunta y la agrega al vector de preguntas
	//de la publicacione enviada como parametro.
	public void realizarPregunta (Publicacion publicacion, String pregunta) {	
		
		if (publicacion != null) {
			
			if (publicacion.isEstado()==true){
				Pregunta nuevaPregunta = new Pregunta (publicacion, this, pregunta);
				publicacion.preguntas.add(nuevaPregunta);			
			}
			
			else mostrarErrorPublicacionInactiva();	
		}
		
		else mostrarErrorPublicacionNoExiste();
	}
	
	
	void contestarPregunta (Pregunta pregunta, String respuesta) {
		pregunta.setRespuesta(respuesta);
	}
	
	//Crea objeto compras y lo agrega al vector de compras del user.
	public void comprar (Publicacion publicacionAux, int cantidad){
		Compra compra = new Compra (publicacionAux, cantidad);
		compras.add(compra);	
	}
	
	//Idem compras pero con ventas.
	public void vender (Publicacion publicacionAux, Usuario comprador, int cantidad){	
		Venta venta = new Venta (publicacionAux, comprador, cantidad);
		ventas.add(venta);	
		sumarVenta();
		facturar(publicacionAux);
	}
	
	//Idem pero con pujas realizadas por el usuario.
	public void pujar (Publicacion publicacionAux){
		Puja puja = new Puja (publicacionAux);
		pujas.add(puja);	
	}
	
	//Recorre el vector de ventas de un usuario, y si su atributo usuarioComprador coincide con el usuario upgradeado tomado
	//como parametro, lo reemplaza por este ultimo.
	public void actualizarReferenciasDeUsuarioEnVentas(Usuario usuarioUpgraded){
		for (Venta venta: ventas){
			if (venta.getComprador().equals(usuarioUpgraded)){
				venta.setComprador(usuarioUpgraded);
			}
		}		
	}
	
	//Actualizo referencias a usuario en todas las preguntas de todas las publicaciones de esta instancia.
	public void actualizarReferenciasDeUsuarioEnPreguntas(Usuario usuarioUpgraded){
		for (Publicacion publicacion: publicaciones){
			publicacion.actualizarReferenciasDeUsuarioEnPreguntas(usuarioUpgraded);
		}
	}
	
	public void listarCompras(){
		for(Compra compra: compras){
			compra.mostrarDatos();
		}
	}
	
	public void listarVentas(){
		for (Venta venta: ventas){
			venta.mostrarDatos();
		}
	}
		
	//Errores
	public void mostrarErrorProductoYaExisteEnUsuario() {
		System.out.println ("El producto ingresado ya existe");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorProductoInexistenteEnUsuario() {
		System.out.println ("El producto ingresado no existe");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorPublicacionYaExisteUsuario(String titulo){
		System.out.println("La publicacion-> " + titulo +" <- ya ha sido creada por el usuario");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorPublicacionNoExiste(){
		System.out.println("La publicacion ingresada no existe");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorPublicacionInactiva (){
		System.out.println("La publicacion ha sido cancelada");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	//Getters y setters
	public double getReputacion() {
		return reputacion;
	}
	public void setReputacion(double reputacion) {
		this.reputacion = reputacion;
	}
	
	public int getCantidadVentas() {
		return cantidadVentas;
	}
	public void setCantidadVentas(int cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}
	
	public double getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(double facturacion) {
		this.facturacion = facturacion;
	}

	public Vector<Publicacion> getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(Vector<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	public Vector<Producto> getProductos() {
		return productos;
	}
	public void setProductos(Vector<Producto> productos) {
		this.productos = productos;
	}

}
