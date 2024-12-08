package negocio.proveedor;

public class TNacional extends TProveedor {

	private String codigoPostal;

	public TNacional() {
	}

	public TNacional(int id, String nombre, boolean activo, String cp) {
		super(id, nombre, activo);
		this.codigoPostal = cp;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String cp) {
		this.codigoPostal = cp;
	}

	@Override
	public String toString() {
		return super.toString() + "\nCodigo Postal: " + this.getCodigoPostal();
	}
}