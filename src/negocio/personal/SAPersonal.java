package negocio.personal;

import java.util.List;

import negocio.personalHangar.TPersonalHangar;

public interface SAPersonal {

	public int altaPersonal(TPersonal tPersonal);

	public boolean bajaPersonal(int id);

	public boolean vincularPersonal(TPersonalHangar tPersonalHangar);

	public boolean desvincularPersonal(TPersonalHangar tPersonalHangar);

	public boolean modificarPersonal(TPersonal tPersonal);

	public TPersonal consultarPersonalPorId(int id);

	public List<TPersonal> consultarPersonalExistente();
	
	public List<TPersonal> consultarPersonalPorHangar(int id_hangar);

}