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
	
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar);

}