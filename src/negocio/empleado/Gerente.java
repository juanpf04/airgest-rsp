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
		@NamedQuery(name = "negocio.empleado.Gerente.findBydespacho", query = "select obj from Gerente obj where :despacho = obj.despacho "),
		@NamedQuery(name = "negocio.empleado.Gerente.findByhorasExtra", query = "select obj from Gerente obj where :horasExtra = obj.horasExtra ") })
public class Gerente extends Empleado implements Serializable {
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
	private int despacho;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int horasExtra;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int getDespacho() {
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
	public int getHorasExtra() {
		// begin-user-code
		// TODO Auto-generated method stub
		return 0;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param despacho
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setDespacho(int despacho) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param horasExtra
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setHorasExtra(int horasExtra) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Gerente() {
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
	public Gerente(TGerente transfer) {
		// begin-user-code
		// TODO Auto-generated constructor stub
		// end-user-code
	}
}