/**
 * 
 */
package negocio.venta;

import java.io.Serializable;
import javax.persistence.Embeddable;
import java.util.UUID;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author javia
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
@Embeddable
public class Clave implements Serializable {
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
	public Clave() {
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int venta;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private int producto;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Clave))
			return false;
		Clave pk = (Clave) obj;
		if (!(venta == pk.venta))
			return false;
		if (!(producto == pk.producto))
			return false;
		return true;
	}

	private UUID uuid;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + venta;
		hash = hash * prime + producto;
		if (hash == 17) {
			if (uuid == null) {
				uuid = UUID.randomUUID();
			}
			hash = uuid.hashCode();
		}
		return hash;
	}
}