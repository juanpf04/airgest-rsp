
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

	/** 
	* (non-Javadoc)
	* @see Observador#actualizar(Object datos)
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void actualizar(Object datos) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}