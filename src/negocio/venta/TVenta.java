
package negocio.venta;

public class TVenta {

	private int id;

	private double precio;

	private String fecha;

	private int idEmpleado;

	public TVenta() {

	}

	public TVenta(int id, double precio, String fecha, int idEmpleado) {
		this.id = id;
		this.precio = precio;
		this.fecha = fecha;
		this.idEmpleado = idEmpleado;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
}