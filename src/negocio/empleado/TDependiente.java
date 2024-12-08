package negocio.empleado;

public class TDependiente extends TEmpleado {

	private int seccion;

	private boolean noches;

	public TDependiente() {
	}

	public TDependiente(int id, int tag, int horasMensuales, int idDepartamento, boolean activo, int seccion,
			boolean noches) {
		super(id, tag, horasMensuales, idDepartamento, activo);
		this.seccion = seccion;
		this.noches = noches;
	}

	public int getSeccion() {
		return this.seccion;
	}

	public boolean getNoches() {
		return this.noches;
	}

	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}

	public void setNoches(boolean noches) {
		this.noches = noches;
	}

	@Override
	public String toString() {
		return super.toString() + "\n Tipo: Dependiente" +"\n Seccion: " + seccion + "\n Noches: " + noches + "\n";
	}
}