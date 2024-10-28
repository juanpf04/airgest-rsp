package negocio.avion;

public class TAComercial extends TAvion {

	private String empresa;

	public TAComercial(int id, int numAsientos, String fechaFabricacion, String nombre, String matricula,
			boolean activo, int idAerolinea, int idModelo, int idHangar, String empresa) {
		super(id, numAsientos, fechaFabricacion, nombre, matricula, activo, idAerolinea, idModelo, idHangar);
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return super.toString() + "\nEmpresa de trabajadores: " + this.getEmpresa();
	}
}