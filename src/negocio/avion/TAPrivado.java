
package negocio.avion;

import java.time.LocalDate;

public class TAPrivado extends TAvion {

	private String nombreDuenyo;
	private int idCarnet;

	public TAPrivado(int id, int numAsientos, String fechaFabricacion, String nombre, String matricula,
			boolean activo, int idAerolinea, int idModelo, int idHangar, String nombre_duenyo, int idCarnet) {
		super(id, numAsientos, fechaFabricacion, nombre, matricula, activo, idAerolinea, idModelo, idHangar);
		this.nombreDuenyo = nombre_duenyo;
		this.idCarnet = idCarnet;
	}

	public int getIdCarnet() {
		return this.idCarnet;
	}

	public String getNombreDuenyo() {
		return this.nombreDuenyo;
	}

	public void setIdCarnet(int idCarnet) {
		this.idCarnet = idCarnet;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param idModelo
	* @param nombreDuenyo
	* @param idCarnet
	* @param idHangar
	* @param idAerolinea
	* @param matricula
	* @param activo
	* @param fechaFabricacion
	* @param nombre
	* @param numAsientos
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TAPrivado(int id, int idModelo, String nombreDuenyo, int idCarnet, int idHangar, int idAerolinea,
			String matricula, boolean activo, LocalDate fechaFabricacion, String nombre, int numAsientos) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	public void setNombreDuenyo(String nombre_duenyo) {
		this.nombreDuenyo = nombre_duenyo;
	}

	@Override
	public String toString() {
		return super.toString() + "\nNombre dueño: " + this.getNombreDuenyo() + "\nId carnet: " + this.getIdCarnet();
	}
}