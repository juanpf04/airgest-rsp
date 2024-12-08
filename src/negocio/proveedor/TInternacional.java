package negocio.proveedor;

public class TInternacional extends TProveedor {

	private String pais;

	private double impuesto;

	public TInternacional(int id, String nombre, boolean activo, String pais, double impuesto) {
		super(id, nombre, activo);
		this.pais = pais;
		this.impuesto = impuesto;
	}

	public TInternacional() {
	}

	public String getPais() {
		return this.pais;
	}

	public double getImpuesto() {
		return this.impuesto;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}

	@Override
	public String toString() {
		return super.toString() + "\nPais: " + this.getPais() + "\nImpuesto: " + this.getImpuesto();
	}
}