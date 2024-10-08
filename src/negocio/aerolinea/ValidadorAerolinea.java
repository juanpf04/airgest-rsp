package negocio.aerolinea;

public class ValidadorAerolinea {

	public static boolean comprobarAerolinea(TAerolinea tAerolinea) {
		return comprobarNombre(tAerolinea.getNombre());
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

	public static boolean comprobarNombre(String nombre) {
		return nombre.matches("[a-zA-Z]+");
	}

}