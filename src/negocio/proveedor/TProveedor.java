
package negocio.proveedor;

public class TProveedor {
	
	private int id;
	
	private String nombre;
	
	private boolean activo;

	public TProveedor(int id, String nombre, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public TProveedor() {

	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
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

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}