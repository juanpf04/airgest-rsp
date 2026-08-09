package negocio.marca;

public class TMarca {

	private int id;

	private String nombre;

	private String origen;

	private boolean activo;

	public TMarca() {
	}

	public TMarca(int id, String nombre, String origen, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.origen = origen;
		this.activo = activo;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getOrigen() {
		return this.origen;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "\n Id: " + this.id + "\n Nombre: " + this.nombre + "\n Origen: " + this.origen + "\n Activo: "
				+ this.activo;
	}
}