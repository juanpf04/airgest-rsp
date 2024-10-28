package negocio.modelo;

public class ValidadorModelo {

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tModelo
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarModelo(TModelo tModelo) {
		// begin-user-code
		// TODO Auto-generated method stub
		return comprobarNombre(tModelo.getNombre()) && comprobarMotor(tModelo.getMotor()) && tModelo.getId() != -1;
		// end-user-code
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

	public static boolean comprobarDatos(TModelo tModelo) {
		return comprobarNombre(tModelo.getNombre()) && comprobarMotor(tModelo.getMotor());
	}

	public static boolean comprobarNombre(String nombre) {
		return nombre.matches("^[a-zA-Z]+-[0-9]+$"); // ^para que las letras
														// sean al principio, y
														// $ para que los
														// números sean al final
	}

	public static boolean comprobarMotor(String motor) {
		return motor.matches("^[A-Z]{3}+-[0-9]{2}+$"); // 3 letras mayusculas y
														// 2 dígitos
	}

}