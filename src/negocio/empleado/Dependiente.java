/**
 * 
 */
package negocio.empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author javia
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.empleado.Dependiente.findByseccion", query = "select obj from Dependiente obj where :seccion = obj.seccion "),
		@NamedQuery(name = "negocio.empleado.Dependiente.findBynoches", query = "select obj from Dependiente obj where :noches = obj.noches ") })
public class Dependiente extends Empleado implements Serializable {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private static final long serialVersionUID = 0;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int seccion;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private boolean noches;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int getSeccion() {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean getNoches() {
		// begin-user-code
		// TODO Auto-generated method stub
		return false;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param seccion
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setSeccion(int seccion) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param noches
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setNoches(boolean noches) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Dependiente() {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param transfer
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Dependiente(TDependiente transfer) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}
}