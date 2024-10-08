package negocio.personalHangar;

public class TPersonalHangar {

	private int idPersonal;

	private int idHangar;

	public int getIdHangar() {
		return this.idHangar;
	}

	public int getIdPersonal() {
		return this.idPersonal;
	}

	public void setIdHangar(int id) {
		this.idHangar = id;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void setIdPersonal() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setIdPersonal(int id) {
		this.idPersonal = id;
	}

	public TPersonalHangar() {
	}

	public TPersonalHangar(int idPersonal, int idHangar) {
		this.idPersonal = idPersonal;
		this.idHangar = idHangar;
	}
}