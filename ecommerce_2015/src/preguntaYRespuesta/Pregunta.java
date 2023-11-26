package preguntaYRespuesta;
import plataforma.Publicacion;
import plataforma.Usuario;

public class Pregunta {
	
	private Publicacion publicacion;
	private int identificador;
	private static int id=1;
	private Usuario usuarioQuePregunta;
	private String pregunta;
	private String respuesta;
	
	//Trate de hacer un auto increment con el id, pero no anda correctamente.
	public Pregunta(Publicacion publicacion, Usuario usuario, String pregunta) {
		super();
		this.publicacion = publicacion;
		this.identificador = Pregunta.this.id++;
		this.usuarioQuePregunta = usuario;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}
	
	public void mostrarPreguntasYRespuestas () {
		System.out.println("Publicacion: " + this.publicacion.getTitulo());
		System.out.println ("	-Usuario que pregunta: " + this.usuarioQuePregunta.getUsername());
		System.out.println ("	-Pregunta: " + this.pregunta);
		System.out.println (" 	-Respuesta: " + this.respuesta);
		System.out.println ();
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public Usuario getUsuario() {
		return usuarioQuePregunta;
	}

	public void setUsuario(Usuario usuario) {
		this.usuarioQuePregunta = usuario;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
