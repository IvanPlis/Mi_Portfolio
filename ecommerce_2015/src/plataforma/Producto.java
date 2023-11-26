package plataforma;

public class Producto {
	
	
	private String nombreProducto;
	private String descripcionProd;
	//nuevo->true  usado->false
	private boolean estado;
	private Usuario usuario;
	
	
	public Producto(String nombreProducto, String descripcionProd, boolean estado, Usuario usuario) {
		super();
		this.nombreProducto = nombreProducto;
		this.descripcionProd = descripcionProd;
		this.estado = estado;
		this.usuario = usuario;
	}
	
	
	public void mostrarDatos() {
		
		String estado;

		if (this.estado==true){
			estado="Nuevo";
		}	
		else estado="Usado";

		System.out.println ("Nombre del Producto: " + this.nombreProducto);
		System.out.println ("	*Descripcion: " + this.descripcionProd);
		System.out.println ("   	*Estado: " + estado);
		System.out.println ("	*Pertenece a:" + this.usuario);
		System.out.println("-----------------------------------");
	}


	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcionProd() {
		return descripcionProd;
	}
	public void setDescripcionProd(String descripcionProd) {
		this.descripcionProd = descripcionProd;
	}

	
	
}
