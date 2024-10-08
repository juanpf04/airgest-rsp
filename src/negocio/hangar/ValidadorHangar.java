
package negocio.hangar;

public class ValidadorHangar {

	public static boolean comprobarDatos(THangar tHangar) {
		return comprobarDireccion(tHangar.getDireccion()) && comprobarStock(tHangar.getStock())
				&& comprobarCosteDia(tHangar.getCosteDia())
				&& comprobarEspacioAlmacenaje(tHangar.getEspacioAlmacenaje());
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

	public static boolean comprobarDireccion(String direccion) {
		return direccion.matches("[a-zA-Z0-9 ]+");
	}

	public static boolean comprobarStock(int stock) {
		return stock >= 0;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param costeDia
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public static boolean comprobarCosteDia(float costeDia) {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	public static boolean comprobarCosteDia(double costeDia) {
		return costeDia > 0;
	}

	public static boolean comprobarEspacioAlmacenaje(int espacioAlmacenaje) {
		return espacioAlmacenaje > 0;
	}
}