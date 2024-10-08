package negocio.contrato;

import negocio.UtilidadesN;

public class ValidadorContrato {

	public static boolean comprobarDatos(TContrato tContrato) {
		return UtilidadesN.comprobarId(tContrato.getIdAerolinea()) && tContrato.getPrecio() > 0;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarId(int id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}
}