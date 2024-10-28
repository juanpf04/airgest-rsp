package negocio.personal;

public class TPSeguridad extends TPersonal {

	private int numPlaca;

	public int getNumPlaca() {
		return this.numPlaca;
	}

	public void setNumPlaca(int numPlacas) {
		this.numPlaca = numPlacas;
	}

	public TPSeguridad(int id, String dni, String areaAsignada, boolean activo, int numPlaca) {
		super(id, activo, dni, areaAsignada);

		this.numPlaca = numPlaca;
	}

	@Override
	public String toString() {
		return super.toString() + "\n número de placa: " + numPlaca;
	}
}