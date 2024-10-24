package integracion.aerolinea;

import negocio.aerolinea.TAerolinea;
import java.util.List;

public interface DAOAerolinea {

	public TAerolinea consultarAerolineaPorId(int idAerolinea);

	public TAerolinea consultarAerolineaPorNombre(String nombre);

	public int altaAerolinea(TAerolinea tAerolinea);

	public boolean modificarAerolinea(TAerolinea tAerolinea);

	public boolean bajaAerolinea(int id);

	public List<TAerolinea> consultarTodasAerolineas();
	
	public List<TAerolinea> consultarAerolineasPorModelo(int id_modelo);

}