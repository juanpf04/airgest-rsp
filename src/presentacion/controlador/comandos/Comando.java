/**
 * 
 */
package presentacion.controlador.comandos;

import presentacion.controlador.Contexto;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author javia
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public interface Comando {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param datos
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Contexto ejecutar(Object datos);
}