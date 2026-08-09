package integracion.personal;

import java.util.List;

import negocio.personal.TPersonal;

public interface DAOPersonal {

	public int altaPersonal(TPersonal tPersonal);

	public boolean bajaPersonal(int id);

	public boolean modificarPersonal(TPersonal tPersonal);

	public TPersonal consultarPersonalPorId(int id);

	public TPersonal consultarPersonalPorDni(String dni);

	public List<TPersonal> consultarPersonalExistente();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idEmpleado
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public String consultarPersonalPorDni(int idEmpleado);

	public List<TPersonal> consultarPersonalPorHangar(int id_hangar);

}