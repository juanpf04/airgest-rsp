
package negocio.departamento;

public class TDepartamento {

	private int id;

	private String nombre;

	private int sala;

	private double sueldoHora;

	private boolean activo;

	public TDepartamento() {

	}

	public TDepartamento(int id, String nombre, int sala, double sueldoHora, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.sala = sala;
		this.sueldoHora = sueldoHora;
		this.activo = activo;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getSala() {
		return this.sala;
	}

	public double getSueldoHora() {
		return this.sueldoHora;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public void setSueldoHora(double sueldoHora) {
		this.sueldoHora = sueldoHora;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setId(int id) {
		this.id = id;
	}
}