package negocio.empleado;

public class TGerente extends TEmpleado {

	private int despacho;

	private int horasExtra;

	public TGerente() {
	}

	public TGerente(int id, int tag, int horasMensuales, int idDepartamento, boolean activo, int despacho,
			int horasExtra) {
		super(id, tag, horasMensuales, idDepartamento, activo);
		this.despacho = despacho;
		this.horasExtra = horasExtra;
	}

	public int getDespacho() {
		return this.despacho;
	}

	public int getHorasExtra() {
		return this.horasExtra;
	}

	public void setDespacho(int despacho) {
		this.despacho = despacho;
	}

	public void setHorasExtra(int horasExtra) {
		this.horasExtra = horasExtra;
	}

	@Override
	public String toString() {
		return super.toString() + "\n Tipo: Gerente" + "\n Despacho: " + despacho + "\n Horas Extra: " + horasExtra + "\n";
	}
}