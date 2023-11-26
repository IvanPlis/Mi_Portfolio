package metodosDePago;

abstract public class Tarjeta {
	
	public int nroTarjeta;

	public Tarjeta(int nroTarjeta) {
		super();
		this.nroTarjeta = nroTarjeta;
	}

	public int getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	

}
