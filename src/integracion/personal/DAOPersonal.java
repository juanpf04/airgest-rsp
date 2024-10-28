package integracion.personal;

import negocio.personal.TPersonal;
import java.util.List;

public interface DAOPersonal {

	public int altaPersonal(TPersonal tPersonal);

	public boolean bajaPersonal(int id);

	public boolean modificarPersonal(TPersonal tPersonal);

	public TPersonal consultarPersonalPorId(int id);

	public TPersonal consultarPersonalPorDni(String dni);
//	public TPersonal consultarPersonalPorIdEmpleado(int idEmpleado); hace falta? no tiene sentido --> por dni ser�a no?

	public List<TPersonal> consultarPersonalExistente();
	
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar);

}