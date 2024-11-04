package integracion.aerolinea;

import java.util.List;

import negocio.aerolinea.TAerolinea;

public interface DAOAerolinea {

	public TAerolinea consultarAerolineaPorId(int idAerolinea);

	public TAerolinea consultarAerolineaPorNombre(String nombre);

	public int altaAerolinea(TAerolinea tAerolinea);

	public boolean modificarAerolinea(TAerolinea tAerolinea);

	public boolean bajaAerolinea(int id);

	public List<TAerolinea> consultarTodasAerolineas();
	
	public List<TAerolinea> consultarAerolineasPorModelo(int id_modelo);

}