package negocio.empleado;

public class TEmpleado {

	private int id;

	private int tag;

	private int horasMensuales;

	private boolean activo;

	private int idDepartamento;

	public TEmpleado() {
	}

	public TEmpleado(int id, int tag, int horasMensuales, int idDepartamento, boolean activo) {
		this.id = id;
		this.tag = tag;
		this.horasMensuales = horasMensuales;
		this.idDepartamento = idDepartamento;
		this.activo = activo;
	}

	public int getId() {
		return this.id;
	}

	public int getTag() {
		return this.tag;
	}

	public int getHorasMensuales() {
		return this.horasMensuales;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public int getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public void setHorasMensuales(int horasMensuales) {
		this.horasMensuales = horasMensuales;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return " id: " + id + "\n Tag: " + tag + "\n Horas Mensuales: " + horasMensuales + "\n Id Departamento: "
				+ idDepartamento + "\n activo: " + activo + "\n";
	}
}