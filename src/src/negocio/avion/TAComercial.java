package negocio.avion;

public class TAComercial extends TAvion {

	private String empresa;

	public TAComercial(int id, int numAsientos, String fechaFabricacion, String nombre, String matricula,
			boolean activo, int idAerolinea, int idModelo, int idHangar, String empresa) {
		super(id, numAsientos, fechaFabricacion, nombre, matricula, activo, idAerolinea, idModelo, idHangar);
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param empresa
	* @param activo
	* @param idHangar
	* @param idAerolinea
	* @param idModelo
	* @param nombre
	* @param matricula
	* @param fechaFabricacion
	* @param numAsientos
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TAComercial(int id, String empresa, boolean activo, int idHangar, int idAerolinea, int idModelo,
			String nombre, String matricula, String fechaFabricacion, int numAsientos) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	@Override
	public String toString() {
		return super.toString() + "\nEmpresa de trabajadores: " + this.getEmpresa();
	}
}