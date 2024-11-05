package integracion.hangar;

import java.util.List;

import negocio.hangar.THangar;

public interface DAOHangar {

	public THangar consultarHangarPorId(int id);

	public boolean actualizarStock(int id, int stock);

	public int altaHangar(THangar tHangar);

	public boolean bajaHangar(int id);

	public List<THangar> consultarTodosHangares();

	public boolean modificarHangar(THangar tHangar);

	public THangar consultarHangarPorDireccion(String direccion);
	
	public List<THangar> consultarHangaresPorPersonal(int id_personal);

}