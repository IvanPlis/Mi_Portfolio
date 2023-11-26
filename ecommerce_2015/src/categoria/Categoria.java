package categoria;

import java.util.Vector;

import plataforma.Publicacion;

public class Categoria {
	
	private String nombreCategoria;
	private Vector <Publicacion> publicaciones;

	public Categoria(String nombreCategoria) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.publicaciones = new Vector <Publicacion>();
	}
	
	public void mostrarDatos(){
		System.out.println(this.nombreCategoria);
	}
	
	public void listarPublicaciones (){
		for (Publicacion publicacion : publicaciones) {
			publicacion.mostrarDatos();
		}
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Vector<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(Vector<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	
	
	
}
