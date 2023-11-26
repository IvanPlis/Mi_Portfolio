package plataforma;
import categoria.Categoria;
import preguntaYRespuesta.Pregunta;
import java.util.Vector;

abstract public class Publicacion {
	
	protected Usuario usuario;
	protected Categoria categoria;
	protected Producto producto;
	protected String titulo;
	protected double precio;
	protected int stock;
	protected String descripcion;
	protected boolean estado;
	protected Vector <Pregunta> preguntas;
	
	public Publicacion(Usuario usuario, Categoria categoria, Producto producto,
			String titulo, double precio, int stock, String descripcion
			) {
		super();
		this.usuario = usuario;
		this.categoria = categoria;
		this.producto = producto;
		this.titulo = titulo;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
		this.estado = true;
		this.preguntas = new Vector <Pregunta>();
		//true=publi activa
	}
	
	public void mostrarDatos() {
		System.out.println ("Publicacion: " + this.titulo);
		System.out.print ("  Categoria: "); categoria.mostrarDatos();
		System.out.println ("  Usuario: " + this.usuario.getUsername());
		System.out.println ("  Producto: " + this.producto.getNombreProducto());
		System.out.println ("  Precio: " + this.precio);
		System.out.println ("  Stock: " + this.stock);
		System.out.println ("  Descripcion: " + this.descripcion);
		System.out.println ();
	}
	
	public void mostrarDatosParaCategoria () {
		System.out.println ("Titulo: " + this.titulo + "  Precio: " + this.precio);
	}
	
	
	//Recorro vector de preguntas. Si una pregunta coincide con el identificador, la retorno.
	public Pregunta getPregunta (int identificador) {
		for (Pregunta pregunta: preguntas) {
			if (pregunta.getIdentificador() == identificador) {
				return pregunta;
			}	
		}	
		return null;
	}
	
	public void listarPreguntasYRespuestas(){
		for(Pregunta pregunta: preguntas){
			pregunta.mostrarPreguntasYRespuestas();
		}
	}
	
	abstract public void realizarOperacion(Usuario comprador, int cantidad);

	
	protected void actualizarStock(int cantidadVendida){
		this.stock=this.stock-cantidadVendida;
	}
	
	//Recorro el vector de preguntas de la publicacion, y corroboro si algun usuario coincide con el usuarioUpgrade. Si 
	//hay coincidencia, reemplazo uno por otro.
	public void actualizarReferenciasDeUsuarioEnPreguntas (Usuario userUpgraded){
		for (Pregunta pregunta: preguntas){
			if (pregunta.getUsuario().equals(userUpgraded)){
				pregunta.setUsuario(userUpgraded);
			}
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Vector<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Vector<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
}
