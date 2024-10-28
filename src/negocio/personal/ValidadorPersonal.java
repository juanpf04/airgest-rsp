package negocio.personal;

public class ValidadorPersonal {

	public static boolean comprobarDatos(TPersonal tPersonal) {
		boolean valido = tPersonal.getId() > 0 && tPersonal.getAreaAsignada() != null
				&& !tPersonal.getAreaAsignada().isEmpty() && comprobarDNI(tPersonal.getDni());

		if (tPersonal instanceof TPLimpieza)
			valido = valido && ((TPLimpieza) tPersonal).getRol() != null
					&& !((TPLimpieza) tPersonal).getRol().isEmpty();
		else
			valido = valido && ((TPSeguridad) tPersonal).getNumPlaca() > 0;

		return valido;
	}
	
	public static boolean comprobarDNI(String dni) {//CAMBIAR ESTO
		return dni.matches("[a-zA-Z0-9 ]+");
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