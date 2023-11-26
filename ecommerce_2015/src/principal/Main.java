
package principal;

import plataforma.Producto;
import preguntaYRespuesta.Pregunta;
import plataforma.Publicacion;
import plataforma.Plataforma;
import categoria.Categoria;
import plataforma.Usuario;

public class Main {

	private static Plataforma plataforma;
	
	public static void main(String args[]){	
		
		/**
		 * EL MAIN ESTÃ� PENSADO PARA QUE NO TENGA QUE SER MODIFICADO.
		 * 
		 * SIN EMBARGO, SI LLEGASEN A MODIFICAR ALGO, DEBERÃ�N JUSTIFICAR EN EL ORAL 
		 * LAS DECISIONES DE DISEÃ‘O.
		 * 
		 */
		
		/**
		 * Hay que realizar todos los mÃ©todos getters y setters necesarios (para cada una de las clases).
		 */
		
		// Creo la plataforma.
		plataforma();
		
		usuarios();
		
		productos();
		
		categorias();
		
		publicaciones();
		
		interacciones();
	}
	
	
	
	public static void plataforma(){
		plataforma = new Plataforma("Aca va el nombre: Carlos Libre");
		plataforma.modificarNombre("nombre");
	}
	
	public static void usuarios(){
		plataforma.crearUsuario("Riber", "ribermail");
		plataforma.crearUsuario("Juan", "megustaelarte");
		plataforma.crearUsuario("Pedro", "pedromail");
		plataforma.crearUsuario("Ricardo", "ioriomail");
		plataforma.crearUsuario("Ricardo", "multikillmail");
		plataforma.crearUsuario("Ricky Fort", "procernacionalmail");
		plataforma.crearUsuario("Papa Frita", "ribermail");
		
		Usuario user1 = plataforma.getUsuario("Papa Frita");
		
		
		/** 
		 * Problema:
		 * Si creamos un usuario con el username "pepe" y otro con username "juan".
		 * Despues modificamos el username "juan" por "pepe".
		 * Tendriamos dos instancias que corresponden a dos usuarios diferentes pero con el mismo username.
		 *
		 * Dado que la busqueda de usuarios estÃ¡ dada por el username. Si buscaramos "pepe" encontrariamos dos resultados. 
		 * Eso ocurre porque nunca pasamos a travÃ©s de la plataforma, donde deberia verificarse que el cambio de nickname es valido.
		 */
		plataforma.modificarUsername("Riber", "riBer");
		plataforma.modificarEmail("ioriomail", "wachosforrosmail");
		plataforma.actualizarDatos(user1, "Frita Papa", "Raduskymail");
		//user1.modificarUsername("username");
		//user1.modificarEmail("mail");
		//user1.actualizarDatos("username", "email");
		
		plataforma.listarUsuarios();
		
		/**
		 * Atencion:
		 * Cuando la plataforma elimina un usuario, sus publicaciones deben ser canceladas.
		 * Todas las ventas concretadas no deben eliminarse.
		 * 
		 * El usuario "Pepe" realizÃ³ una publicacion con 50 unidades de stock y "Juan" concretÃ³ una compra por 1 unidad.
		 * La publicaciÃ³n sigue activa con 49 unidades restantes.
		 * Sin embargo, ahora "Pepe" decide darse de baja.
		 * La publicaciÃ³n con 49 unidades debe ser desactivada/cancelada.
		 * El usuario debe ser borrado de la plataforma.
		 * Pero la compra realizada por "Juan" a esa publicaciÃ³n no debe desaparecer.
		 * 
		 */
		plataforma.eliminarUsuario("Pedro");
		plataforma.eliminarUsuario("Ricardo");
		plataforma.listarUsuarios();
		
		plataforma.consultarReputacion("Ricky Fort");
	}
	
	public static void productos(){
		Usuario user1 = plataforma.getUsuario("Ricky Fort");
		
		/**
		 * crearProducto(USUARIO, NOMBRE DEL PRODUCTO, DESCRIPCION DEL PRODUCTO, ESTADO);
		 * ESTADO = TRUE => NUEVO
		 * ESTADO = FALSE => USADO
		 */
		plataforma.crearProducto(user1, "Auto", "4 ruedas", true);
		plataforma.crearProducto(user1, "PS3", "320 GB", false);
		
		user1.listarProductos();
		
		Usuario user2 = plataforma.getUsuario("Juan");
		
		plataforma.crearProducto(user2, "XBOX 360", "4GB", true);
		plataforma.crearProducto(user2, "XBOX 360", "4GB", true);
		plataforma.crearProducto(user2, "Wii 2", "basura", false);
		plataforma.crearProducto(user2, "Reloj", "a cuerda", false);
		
		user2.listarProductos();
		
		
		/**
		 * Tenemos que tener un mÃ©todo para obtener un producto.
		 */
		plataforma.getProducto(user1, "XBOX 360");
		plataforma.getProducto(user1, "Wii 2");
		plataforma.getProducto("Juan", "Wii 2");
		
		// el producto no debe poder ser eliminado si ya se encuentra en una publicacion.
		/**
		 * Al igual que al obtener el producto, necesito enviarle a quÃ© usuario corresponde ese producto.
		 */
		plataforma.eliminarProducto(user1, "Wii 2");
		
		/*
		plataforma.eliminarProducto("nombre del usuario", "nombre");
		*/
	}
	
	public static void categorias() {
		plataforma.crearCategoria("Consolas");
		plataforma.crearCategoria("Relojes");
		plataforma.crearCategoria("Autos");
		plataforma.crearCategoria("Celulares");
		
		/**
		 * Â¿CuÃ¡l es el problema?
		 * No sÃ© a quÃ© categorÃ­a le cambio el nombre.
		 */
		plataforma.modificarCategoria("Autos", "Automoviles");
		plataforma.listarCategorias();
	}
	
	
	public static void publicaciones(){
		Categoria categoria1 = plataforma.getCategoria("Consolas");
		Categoria categoria2 = plataforma.getCategoria("Relojes");
		Categoria categoria3 = plataforma.getCategoria("Automoviles");
		
		// En el metodo publicar manejo la fecha de creacion de la publicacion.
		// La clase Date (propia de Java) sirve para crear una fecha.
		
		// El tipo de publicacion (Bronce, Plata, Oro, Platino) dependera de la categoria
		// del usuario.
		// Usuario sin ventas todavia -> Publicacion Bronce.
		// Usuario Normal -> Publicacion Plata.
		// Usuario Gold -> Publicacion Oro.
		// Usuario Platinum -> Publicacion Platino.
		
		/**
		 * Por las dudas les agrego la siguiente aclaraciÃ³n, para que vean de dÃ³nde salen todas las
		 * variables que luego se pasan por parÃ¡metro.
		 * 
		 * 
		 */
	
	
		Usuario user1 = plataforma.getUsuario("Juan");
		Producto producto1 = plataforma.getProducto(user1, "XBOX 360");
		Producto producto2 = plataforma.getProducto(user1, "Reloj");
		
		Usuario user2 = plataforma.getUsuario("Ricky Fort");
		Producto producto3 = plataforma.getProducto(user2, "Auto");
		
		/**
		 * Agreguen validaciones a la hora de publicar para que no pueda pasarse un precio inferior a 0,
		 * o un stock inferior a 0.
		 * Recuerden que venta directa o subasta decidieron definirlo como boolean.
		 * 
		 * Para estandarizarlo:
		 * 
		 * 		FALSE = VENTA DIRECTA
		 * 		TRUE = SUBASTA
		 */
		//plataforma.publicar(user1, categoria1, producto1, "titulo", precio, stock, "descripcion", venta directa o subasta);
		plataforma.publicar(user1, categoria1, producto1, "XBOX 360 PACK 1", 4000, 1, "descripcion", true);
		plataforma.publicar(user1, categoria1, producto1, "XBOX 360 PACK 1", 5000, 1, "descripcion", true);
		plataforma.publicar(user1, categoria1, producto1, "PS3", 5500, 50, "descripcion", false);
		plataforma.publicar(user1, categoria2, producto2, "Reloj chino", 1000, 10, "descripcion", false);
		plataforma.publicar(user2, categoria3, producto3, "Auto sin marca", 80300, 1, "descripcion", true);
		
		// Tendria que recorrer todos los usuarios e ir imprimiendo cada una de sus publicaciones.
		plataforma.listarPublicaciones(categoria1);
		plataforma.listarPublicaciones(user1);
		plataforma.listarPublicaciones(false); /** me tiene que listar las ventas directas. */
		plataforma.listarPublicaciones(true); /** me tiene que listar las ventas directas. */
		
		Publicacion publicacion1 = plataforma.getPublicacion("PS3");
		Publicacion publicacion2 = plataforma.getPublicacion("XBOX 360 PACK 1");
		
		// Debera imprimir una publicacion incluyendo su tÃ­tulo, usuario, categoria, descripcion, stock, precio., preguntas y sus respuestas.
		plataforma.mostrarPublicacionPorPantalla(publicacion1);
		
		/**
		 * Cuando cancelan una publicaciÃ³n no tienen que perder referencia,
		 * en las compras y ventas realizadas.
		 */
		plataforma.cancelarPublicacion(publicacion2);
	}
	
	public static void interacciones(){
		
		// preguntas - respuestas
		// solo se pueden realizar preguntas y respuestas en publicaciones activas.
		
		Publicacion publicacion1 = plataforma.getPublicacion("PS3");
		Publicacion publicacion2 = plataforma.getPublicacion("Reloj chino");
		Publicacion publicacion3 = plataforma.getPublicacion("XBOX 360 PACK 1");
		
		Usuario userQuePregunta = plataforma.getUsuario("Juan");
		
		/**
		 * Verifiquen que la publicacion este ACTIVA!.
		 */
		plataforma.realizarPregunta(publicacion1, userQuePregunta, "tenes stock?");
		plataforma.realizarPregunta(publicacion1, userQuePregunta, "envias por oca?");
		plataforma.realizarPregunta(publicacion2, userQuePregunta, "denunciado lince?");
		plataforma.realizarPregunta(publicacion3, userQuePregunta, "horarios?");
		
		plataforma.listarPreguntasYRespuestas(publicacion1);
		plataforma.listarPreguntasYRespuestas(publicacion2);
		plataforma.listarPreguntasYRespuestas(publicacion3);
		
		/**
		 * Si le paso como parametro un objeto de tipo pregunta.
		 * Getter de tipo pregunta.
		 * Para obtener la pregunta, utilizamos un identificador de pregunta de tipo int.
		 * Entonces, en la clase Pregunta tendremos que generar un identificador para cada instancia creada.
		 */
		
		//plataforma.getPregunta(publicacion1, identificador de pregunta); // identificador de pregunta es un int.
		Pregunta pregunta = plataforma.getPregunta(publicacion1, 1);
		plataforma.contestarPregunta(publicacion1, pregunta, "Si, tengo");
		plataforma.contestarPregunta(publicacion2, pregunta, "Ok");
		plataforma.contestarPregunta(publicacion3, pregunta, "12 a 18hs");
		
		plataforma.listarPreguntasYRespuestas(publicacion1);
		plataforma.listarPreguntasYRespuestas(publicacion2);
		plataforma.listarPreguntasYRespuestas(publicacion3);
		
		// compras - ventas
		/*
		 * Este metodo es para compras directas.
		 */
		
		
		Usuario usuarioComprador = plataforma.getUsuario("este usuario va a comprar");
		//plataforma.ofertar(publicacion1, usuarioComprador, cantidad);
		plataforma.ofertar(publicacion1, usuarioComprador, 2);
		
		/*
		 * Subasta
		 * Les dejo un mÃ©todo por separado a ofertar.
		 * En este deberÃ­an subir el precio y guardarse el ultimo usuario que oferto
		 */
		

		Usuario usuarioComprador2 = plataforma.getUsuario("este usuario quiere comprar");
		Usuario usuarioComprador3 = plataforma.getUsuario("este usuario tambien va a comprar");
		
		plataforma.subastar(publicacion1, usuarioComprador);
		plataforma.mostrarPublicacionPorPantalla(publicacion1);
		plataforma.subastar(publicacion1, usuarioComprador2);
		plataforma.mostrarPublicacionPorPantalla(publicacion1);
		plataforma.subastar(publicacion1, usuarioComprador3);
		plataforma.mostrarPublicacionPorPantalla(publicacion1);
		
		plataforma.listarVentas(publicacion1.getUsuario());
		plataforma.listarCompras(usuarioComprador);
		
		// ademas de ofertar puedo indicarle el medio de pago.
		// Para esto la proxima clase vemos una estructura llamada Enum.
		/**
		 * Si no le paso medioDePago que tome un medio de pago por default.
		 */
		
		/*
		plataforma.ofertar(publicacion2, usuarioComprador, cantidad,  medioDePago);
		plataforma.ofertar(publicacion1, usuarioComprador, cantidad);
		plataforma.ofertar(publicacion2, usuarioComprador, cantidad);
		
		/**
		 * Si no le paso medioDeEnvio que tome un medio de envio por default.
		 */
		
		/*
		plataforma.ofertar(publicacion2, usuarioComprador, cantidad,  medioDeEnvio);
		
		/**
		 * En este mÃ©todo le puedo pasar tanto medioDePago como medioDeEnvio.
		 */
		
		/*
		plataforma.ofertar(publicacion2, usuarioComprador, cantidad,  medioDePago, medioDeEnvio);
		
		/**
		 * ATENCION: Aprovechen la modularizacion para reutilizar el mÃ©todo ofertar.
		 * Cuando no tienen parÃ¡metros especÃ­ficos y deben utilizar el default, lo setean
		 * y lo pasan como parÃ¡metro al mÃ©todo que si recibe dichos parÃ¡metros.
		 */
		
		/**
		 * Las compras y/o ventas tienen que mostrar mÃ©todo de pago y envÃ­o. 
		 */
		/*
		 * 
		plataforma.listarCompras(usuarioComprador);
		plataforma.listarCompras(usuarioComprador2);
		
		*/
	}
}
