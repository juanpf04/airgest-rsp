
package negocio.avion;

import java.time.LocalDate;

public class TAComercial extends TAvion {

	private int trabajadores;

	public TAComercial(int id, int numAsientos, LocalDate fechaFabricacion, String nombre, String matricula,
			boolean activo, int idAerolinea, int idModelo, int idHangar, int trabajadores) {
		//super(id, numAsientos, fechaFabricacion, nombre, matricula, activo, idAerolinea, idModelo, idHangar);
		this.trabajadores = trabajadores;
	}

	public int getTrabajadores() {
		return this.trabajadores;
	}

	public void setTrabajadores(int trabajadores) {
		this.trabajadores = trabajadores;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @param numTrabajadores
	* @param activo
	* @param idHangar
	* @param idAerolinea
	* @param idModelo
	* @param nombre
	* @param matricula
	* @param fechaFabricacion
	* @param numAsientos
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TAComercial(int id, int numTrabajadores, Boolean activo, int idHangar, int idAerolinea, int idModelo,
			String nombre, String matricula, LocalDate fechaFabricacion, Integer numAsientos) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	@Override
	public String toString() {
		return super.toString() + "\nNúmero de trabajadores: " + this.getTrabajadores();
	}
}