package plataforma;
import java.util.Vector;

import preguntaYRespuesta.Pregunta;
import publicacion.Subasta;
import publicacion.VentaDirecta;
import categoria.Categoria;
import usuario.UsuarioDefault;

//FALTAN MEDIOS DE PAGO Y ENVIO.

public class Plataforma {
	
	private String nombrePlataforma;
	private Vector <Usuario> usuarios;
	private Vector <Categoria> categorias;
	
	public Plataforma(String nombrePlataforma) {
		super();
		this.nombrePlataforma = nombrePlataforma;
		this.usuarios = new Vector<Usuario>();
		this.categorias= new Vector <Categoria>();
	}

	
	//Ingreso los datos de un nuevo usuario. Compruebo que no exista y lo creo. Sino, error.
	public void crearUsuario (String nombreUsuario, String email){		
		Usuario usuarioABuscar = getUsuario(nombreUsuario);
			
		if (usuarioABuscar == null) {
			Usuario usuario = new UsuarioDefault (nombreUsuario, email);
			usuarios.add(usuario);
			System.out.println("Usuario Agregado");
		}	
		
		else mostrarErrorUsuarioYaExiste (nombreUsuario);
	}
	
	//Se ingresa un usuario, se lo busca, y si existe, se lo elimina.
	public void eliminarUsuario (String nombreUsuario) {
		Usuario usuarioAEliminar = getUsuario(nombreUsuario);
			
		if (usuarioAEliminar != null) {
			usuarios.removeElement(usuarioAEliminar);	
			System.out.println ("El usuario " + usuarioAEliminar.getUsername() + " se ha eliminado correctamente");
		}
		
		else mostrarErrorUsuarioInexistente();
	}
	
	//Idem arriba pero muestra la reputacion.
	public void consultarReputacion(String nombreUsuario){
		Usuario usuarioABuscar = getUsuario(nombreUsuario);
		
		if (usuarioABuscar != null) {
			usuarioABuscar.getReputacion();
		}
		
		else mostrarErrorUsuarioInexistente();
	}

	//Recorro vector de usuarios para encontrar todos los usuarios.
	public void listarUsuarios(){	
		for (Usuario usuario: usuarios){
				usuario.mostrarDatos();
		}
	}
	
	//Verifico existencia del usuario. Luego, la disponibiliad del nuevo username. Finalmente, modifico.
	public void modificarUsername(String usernameAntiguo, String nuevoUsername){
		Usuario usuarioAModificar = getUsuario(usernameAntiguo);
		
		if (usuarioAModificar != null) {
			Usuario usuarioNuevoUsername = getUsuario(nuevoUsername);
			
			if (usuarioNuevoUsername == null) {
				usuarioAModificar.modificarUsername(nuevoUsername);
			}
			
			else mostrarErrorUsernameNoDisponible(nuevoUsername);
		}
		
		else mostrarErrorUsuarioInexistente();		
	}
	
	//Verifico existencia del usuario. Luego, la disponibiliad del nuevo mail. Finalmente, modifico.
	public void modificarEmail (String emailAntiguo, String nuevoEmail){	
		Usuario usuarioAModificar = getUsuarioByEmail(emailAntiguo);
		
		if (usuarioAModificar != null) {
			Usuario usuarioNuevoEmail = getUsuarioByEmail(nuevoEmail);
			
			if (usuarioNuevoEmail == null) {
				usuarioAModificar.modificarEmail(nuevoEmail);
			}
			
			else mostrarErrorEmailNoDisponible(nuevoEmail);
		}
		
		else mostrarErrorUsuarioInexistente();		
	}
	
	//Llamo a los dos metodos modificar, mandando como parametros los datos de la instancia 
	//del usuario y los nuevos datos.
	public void actualizarDatos (Usuario usuario, String nuevoUsername, String nuevoEmail){
		modificarUsername(usuario.getUsername(), nuevoUsername);
		modificarEmail(usuario.getEmail(), nuevoEmail);
	}
	
	//Busca un usuario recorriendo el vector, tomando como parametro el username.
	public Usuario getUsuario(String nombreUsuario){
		for(Usuario usuario : usuarios){
			if(usuario.getUsername().equals(nombreUsuario)){
				return usuario;
			}
		}
		return null;
	}
	
	//IDEM arriba pero con el email.
	private Usuario getUsuarioByEmail(String email){
		for(Usuario usuarioABuscar : usuarios){
			if(usuarioABuscar.getEmail().equals(email)){
				return usuarioABuscar;
			}
		}
		return null;
	}
		
	//Verifico existencia del usuario. Si existe, verifico existencia del producto (dentro del vector de prod del user)
	//Si no existe, creo el nuevo producto.
	public void crearProducto (Usuario usuario, String nombreProducto, String descripcionProd, boolean estado) {
		
		flag();
		if (usuario != null) {
			usuario.crearProducto(nombreProducto, descripcionProd, estado);
		}
		
		else mostrarErrorUsuarioInexistente();
	}
	

	//Compruebo existencia del usuario , y si existe, la existencia del producto recorriendo su vector de productos.
	//Retorno el producto si existe, o null si no.
	public Producto getProducto (Usuario usuario, String nombreProducto){
		
		if (usuario != null) {
			Producto productoABuscar = usuario.buscarProducto(nombreProducto);
			
			if (productoABuscar == null) {
				return null;
			}
			
			else return productoABuscar;
		}
		
		else mostrarErrorUsuarioInexistente();
		return null;
	}
	
	//IDEM arriba, salvo que tomo como parametro un username.
	public void getProducto (String nombreUsuario, String nombreProducto){
		
		Usuario usuarioABuscar = getUsuario(nombreUsuario);	
		getProducto (usuarioABuscar, nombreProducto);
		
	}
	
	//Recorro el vector de usuarios, y llamo al metodo listar productos de c/u.
	public void listarProductos(){
		for(Usuario usuario: usuarios){
			usuario.listarProductos();
		}	
	}
	
	//Compruebo existencia del usuario y del producto. Luego, que el producto no este asociado a ninguna
	//publicaciones. De cumplirse estas condiciones, elimino.
	public void eliminarProducto (Usuario usuario, String nombreProducto) {
		
		if (usuario != null) {
			
			Producto productoAEliminar = usuario.buscarProducto(nombreProducto);
			
			if (productoAEliminar != null) {
				
				Publicacion publicacionConProducto = buscarProductoEnPublicacion(usuario, nombreProducto);
				
				if (publicacionConProducto == null) {
					usuario.eliminarProducto(nombreProducto);
					mostrarAvisoProductoEliminado(nombreProducto);
				}
				
				else mostrarErrorProductoEnPublicacion(nombreProducto);	
			}
			
			else usuario.mostrarErrorProductoInexistenteEnUsuario();
		}
		
		else mostrarErrorUsuarioInexistente();
	}
	
	//Devuelvo una publicacion asociada a un producto, en caso de existir.
	public Publicacion buscarProductoEnPublicacion (Usuario usuario, String  nombreProducto) {
		Publicacion publicacionABuscar = usuario.buscarProductoEnPublicacion(nombreProducto);
		
		if (publicacionABuscar != null){
			return publicacionABuscar;
		}
		
		else return null;		
	}
	
	//Compruebo que no exosta la categoria y la creo.
	public void crearCategoria(String nombreCategoria){
		Categoria categoriaABuscar = getCategoria(nombreCategoria);
		
		if (categoriaABuscar == null) {
			Categoria categoria = new Categoria (nombreCategoria);
			categorias.add(categoria);
			mostrarAvisoCategoriaCreada(nombreCategoria);
		}
		
		else mostrarErrorCategoriaYaExiste(nombreCategoria);
	}
	
	//Verifica existencia de una categoria.
	public Categoria getCategoria(String nombreCategoria){
		for(Categoria categoria : categorias){
			if(categoria.getNombreCategoria().equals(nombreCategoria)){
				return categoria;
			}
		}
		return null;
	}
	
	//Compruebo existencia de la categoria antigua, y si existe, compruebo disponibilidad del nuevo nombre.
	//En ambos casos, recorro el vector de categorias.
	public void modificarCategoria(String nombreCategoria, String nuevoNombreCategoria){
		Categoria categoriaAModificar = getCategoria(nombreCategoria);
		
		if (categoriaAModificar!=null) {
			Categoria categoriaNuevoNombre = getCategoria(nuevoNombreCategoria);
			
			if (categoriaNuevoNombre == null){
				categoriaAModificar.setNombreCategoria(nuevoNombreCategoria);
			}
			
			else mostrarErrorCategoriaYaExiste(nombreCategoria);
		}
		
		else mostrarErrorCategoriaInexistente();
		
	}
	
	//Recorro vector de categorias y las muestro en pantalla.
	public void listarCategorias(){
		for (Categoria categoria: categorias){
			categoria.mostrarDatos();
		}
	}
	
	//Compruebo que no exista la categoria, que la misma no tenga ninguna publicacion asociada y la elimino.
	public void eliminarCategoria (String nombreCategoria){
		Categoria categoriaABuscar = getCategoria(nombreCategoria);
		
		if (categoriaABuscar != null) {
			if (categoriaABuscar.getPublicaciones().size()==0){
				categorias.removeElement(categoriaABuscar);
				mostrarAvisoCategoriaEliminada(nombreCategoria);
			}
			
			else mostrarErrorCategoriaConPublicaciones(nombreCategoria);
		}
		
		else mostrarErrorCategoriaInexistente();
	}
	
	
	//Compruebo existencia de usuario y categoria. Luego, que nadie haya creado una publicacion con el mismo titulo
	//De cumplirse estas condiciones, llamo al metodo crear publicacion del usuario creador.
	public void publicar (Usuario usuario, Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion,
			boolean ventaDirecta) {
		
		if(usuario != null) {
			
			if (categoria != null) {
				
				if (producto != null) {
				
					Publicacion publicacionABuscar = getPublicacion(titulo);
				
					if (publicacionABuscar == null) {
	
						usuario.crearPublicacion(categoria, producto, titulo,
							precio, stock, descripcion, ventaDirecta);	
					}
				
					else mostrarErrorPublicacionYaExiste(titulo);
				}
				
				else mostrarErrorProductoInexistente();			
			}
			
			else mostrarErrorCategoriaInexistente();
		}
		
		else mostrarErrorUsuarioInexistente();		
	}
	
	//Recorro el vector de usuarios, comprobando en cada posicion la existencia del producto a buscar llamando
	//a su metodo buscar publicacion. Si existe la publi, la retorno. Sino, null.
	public Publicacion getPublicacion(String titulo) {
		for (Usuario usuario: usuarios) {		
			Publicacion publicacionABuscar = usuario.buscarPublicacion(titulo);	
			
			if (publicacionABuscar != null) {
				return publicacionABuscar;
			}
		}
		
		return null;
	}
	
	public Publicacion getPublicacion (Publicacion publicacion){
		 Publicacion publicacionABuscar = getPublicacion(publicacion.getTitulo());
		 
		 return publicacionABuscar;
	}

	
	//Listo publicaciones por Usuario. Si el mismo existe, llamo al metodo listarPubli de su clase
	public void listarPublicaciones (Usuario usuario){
		
		if (usuario != null) {
			usuario.listarPublicaciones();
		}
		
		else mostrarErrorUsuarioInexistente();
		
	}
	
	//Listar las publicaciones por categoria. Idem arriba.
	public void listarPublicaciones (Categoria categoria) {
		
		if (categoria != null) {
			categoria.listarPublicaciones();
		}
		
		else mostrarErrorCategoriaInexistente();
	}
	
	//Idem arriba por venta directa o subasta
	public void listarPublicaciones (boolean ventaDirecta) {
		for (Usuario usuario: usuarios) {
			usuario.listarPublicaciones(ventaDirecta);
		}	
	}
	
	//Compruebo la existencia de la publicacion. Si existe, muestro sus datos. Sino error.
	public void mostrarPublicacionPorPantalla (Publicacion publicacion) {
		
		if (publicacion != null) {
			publicacion.mostrarDatos();
		}
		
		else mostrarErrorPublicacionNoExiste();
		
	}
	
	//Si existe la publicacion, llamo al metodo cancelar de su usuario creador. Sino, error.
	public void cancelarPublicacion (Publicacion publicacion) {	
		
		if (publicacion != null) {
			publicacion.getUsuario().cancelarPublicacion(publicacion);
		}
		
		else mostrarErrorPublicacionNoExiste();	
	}
	
	//Verifica la existencia de la publicacion y del usuario que pregunta, asi como que la misma esta activa
	// De ser asi, se llama al metodo "realizarPregunta" del usuario tomado como parametro.
	public void realizarPregunta (Publicacion publicacion, Usuario usuario, String pregunta){

		if (usuario != null) {
			usuario.realizarPregunta(publicacion, pregunta);
		}	
				
		else mostrarErrorUsuarioInexistente();
	}
	
	
	//Comprueba la existencia de la publicacion a la cual pertenece la pregunta, y la existencia
	//de esta ultima. De cumplirse estas condiciones, devuelve la pregunta. Sino, null.
	public Pregunta getPregunta (Publicacion publicacion, int identificador){
			
		Pregunta pregunta = publicacion.getPregunta(identificador);
			
		if (pregunta != null) {
			return pregunta;
		}
			
		else mostrarErrorPreguntaInexistente();
		return null;		
	
	}
	
	//Verifica existencia de publicacion, que su estado se activo y que la pregunta exista. De ser asi, llama 
	//al metodo contestar del usuario que creo la publicacion.
	public void contestarPregunta (Publicacion publicacion, Pregunta pregunta, String respuesta) {
		
		if (publicacion != null) {
			
			if (publicacion.isEstado()==true) {
				
				if (pregunta != null) {
					publicacion.getUsuario().contestarPregunta(pregunta, respuesta);
				}
			}
			
			else mostrarErrorPublicacionInactiva();	
		}
		
		else mostrarErrorPublicacionNoExiste();
	}
	
	//Recoro vector de preg y resp de una publicacion e imprimo en pantalla.
	public void listarPreguntasYRespuestas (Publicacion publicacion) {
		if (publicacion != null) {
			publicacion.listarPreguntasYRespuestas();
		}
	}
	
	
	//Compruebo que exista el comprador, la publicacion, que se una ventaDirecta, que esta este acriva y su stock.
	//Luego, llamo al metodo realizar operacion y compruebo si el usuario vendedor necesita ser upgradeado una vez
	//concretada la misma.
	public void ofertar (Publicacion publicacion, Usuario comprador, int cantidad){
		
		if (comprador != null){
		
			if (publicacion != null){
				
				if (publicacion instanceof VentaDirecta == true) {
				
					if (publicacion.isEstado() == true) {
				
						if (publicacion.getStock() >= cantidad){

							publicacion.realizarOperacion (comprador, cantidad);
	
							checkUpgrade(publicacion.getUsuario());
						
							mostrarAvisoOperacionRealizada();
						}
					
						else mostrarErrorStockInsuficiente();
					}
					
					else mostrarErrorOfertaEnSubasta();
				}
				
				else mostrarErrorPublicacionInactiva();	
			}
			
			else mostrarErrorPublicacionNoExiste();
		}
			
		else mostrarErrorUsuarioInexistente();
	}
	
	//Idem arriba, pero con subastas y sin upgrade, ya que la operacion no se concreta, sino que solo aumenta su precio.
	public void subastar (Publicacion publicacion, Usuario comprador){
		
		if (comprador != null){
			
			if (publicacion != null){
				
				if (publicacion instanceof Subasta == true) {
				
					if (publicacion.isEstado() == true) {
						
						publicacion.realizarOperacion(comprador, 1);	
						mostrarAvisoOperacionRealizada();
						
					}
					
					else mostrarErrorPublicacionInactiva();	
				}
				
				else mostrarErrorPujaEnVentaDirecta();		
			}
			
			else mostrarErrorPublicacionNoExiste();		
		}
		
		else mostrarErrorUsuarioInexistente();
	}
	
	
	
	//Llamo al metodo upgrade. Si el mismo se ha realizado, agrego el "nuevo usuario" al vector de usuarios. Luego, acutalizo
	//las referencias que haya dentro de la plataforma al mismo. y elimino el "usuario viejo"
	private void checkUpgrade(Usuario usuario){
		Usuario userUpgraded = usuario.upgrade();
		
		if (userUpgraded != null){
			usuarios.add(userUpgraded);
			
			for (Usuario usuarioAux: usuarios){
				usuarioAux.actualizarReferenciasDeUsuarioEnVentas(userUpgraded);
				usuarioAux.actualizarReferenciasDeUsuarioEnPreguntas(userUpgraded);
			}
		}
		
		usuarios.removeElement(usuario);
	
	}
	
	public void listarCompras(Usuario usuario){
		if (usuario != null){
			usuario.listarCompras();
		}
		
		else mostrarErrorUsuarioInexistente();
	}
	
	public void listarVentas(Usuario usuario){
		if (usuario != null){
			usuario.listarVentas();
		}
		
		else mostrarErrorUsuarioInexistente();
		
	}
	
	//Getters y Setters plataforma
	public String getNombrePlataforma() {
		return nombrePlataforma;
	}
	public void modificarNombre(String nombrePlataforma) {
		this.nombrePlataforma = nombrePlataforma;
	}

	public Vector<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Vector<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	//AVISOS DE PROCESOS TERMINADOS EXITOSAMENTE
	public void mostrarAvisoCategoriaCreada (String nombreCategoria){
		System.out.println ("La categoria " + nombreCategoria + " ha sido creada exitosamente");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarAvisoCategoriaEliminada(String nombreCategoria){
		System.out.println("La categoria " + nombreCategoria +" ha sido eliminada");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarAvisoProductoEliminado(String nombreProducto){
		System.out.println("El producto " + nombreProducto +" ha sido eliminado");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarAvisoOperacionRealizada(){
		System.out.println("La operacion se ha realizado exitosamente!");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	//ERRORES
	public void mostrarErrorUsuarioInexistente() {
		System.out.println ("El usuario ingresado no existe");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	public void mostrarErrorUsuarioYaExiste(String nombreUsuario) {
		System.out.println("El usario con username=" + nombreUsuario +" ya existe");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorUsernameNoDisponible (String nuevoUsername){
		System.out.println("El username" + nuevoUsername + " pertenece a otro usuario");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorEmailNoDisponible (String nuevoEmail) {
		System.out.println("El username" + nuevoEmail + " pertenece a otro usuario");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	
	public void mostrarErrorCategoriaInexistente(){
		System.out.println("La categoria ingresada no existe");
		System.out.println("-----------------------------------");
	}
	
	public void mostrarErrorCategoriaYaExiste(String nombreCategoria){
		System.out.println("La categoria " + nombreCategoria +" ya ha sido creada");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorCategoriaConPublicaciones(String nombreCategoria){
		System.out.println("La categoria " + nombreCategoria +" no puede ser eliminada, ya que"
				+ " la misma cuenta con publicaciones asociadas");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorProductoInexistente(){
		System.out.println("El producto ingresado no existe para el usuario");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorPublicacionYaExiste(String titulo){
		System.out.println("La publicacion-> " + titulo +" <- ya ha sido creada");
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
	
	public void mostrarErrorPreguntaInexistente(){
		System.out.println("La pregunta no existe");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorProductoEnPublicacion(String nombreProducto){
		System.out.println("El producto " + nombreProducto + " no se puede eliminar,"
				+ " ya que el mismo esta asociado a una publicacion");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void mostrarErrorStockInsuficiente(){
		System.out.println("No se encuentra en stock la cantidad ingresada");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void  mostrarErrorOfertaEnSubasta(){
		System.out.println("No es posible ofertar en una subasta");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	
	public void  mostrarErrorPujaEnVentaDirecta(){
		System.out.println("No es posible pujar en una venta directa");
		System.out.println("-----------------------------------");
		System.out.println();
	}
	

	
	//FLAG
	
	public void flag(){
		System.out.println("paso por aca");
	}
}
