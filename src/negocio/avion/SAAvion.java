package negocio.avion;

import java.util.List;

public interface SAAvion {

	public int altaAvion(TAvion tAvion);

	public boolean bajaAvion(int idAvion);

	public TAvion consultarAvionPorId(int idAvion);

	public List<TAvion> consultarTodosAviones();

	public boolean modificarAvion(TAvion tAvion);

	public List<TAvion> consultarAvionesPorModelo(int idModelo);

	public List<TAvion> consultarAvionesPorAerolinea(int idAerolinea);

	public List<TAvion> consultarAvionesPorHangar(int idHangar);

	public List<TAvion> consultarAvionesDeAerolineaPorHangar(int id_aerolinea, int id_hangar);

}