package negocio.personal;

public class TPersonal {

	private int id;

	private String dni;

	private String areaAsignada;

	private boolean activo;

	public TPersonal() {
	}

	public TPersonal(int id, boolean activo, String dni, String areaAsignada) {
		this.id = id;
		this.dni = dni;
		this.areaAsignada = areaAsignada;
		this.activo = activo;
	}

	public int getId() {
		return this.id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public String getDni() {
		return this.dni;
	}

	public String getAreaAsignada() {
		return this.areaAsignada;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setAreaAsignada(String areaAsignada) {
		this.areaAsignada = areaAsignada;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return " id: " + id + "\n DNI: " + dni + "\n Área asignada: " + areaAsignada + "\n activo: "
				+ activo + "\n";
	}
}