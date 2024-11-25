
package negocio.proveedor;

public class TNacional extends TProveedor {
	
	private int codigoPostal;
	
	public TNacional() {
		
	}

	public TNacional(int id, String nombre, boolean activo, int cp) {
		super(id, nombre, activo);
		this.codigoPostal = cp;
	}

	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(int cp) {
		this.codigoPostal = cp;
	}
}