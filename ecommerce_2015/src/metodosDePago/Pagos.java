package metodosDePago;

public interface Pagos {
	
	public void pagar(double monto);
	public void comprobarEstado();
	public void cancelar();
	
}
