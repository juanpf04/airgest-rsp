package integracion.modelo;

import java.util.List;

import negocio.modelo.TModelo;

public interface DAOModelo {

	public TModelo consultarModeloPorNombre(String nombre);

	public int altaModelo(TModelo tModelo);

	public boolean modificarModelo(TModelo tModelo);

	public boolean bajaModelo(int id);

	public List<TModelo> consultarTodosModelos();

	public TModelo consultarModeloPorId(int id);

	public List<TModelo> consultarModelosPorAerolinea(int idAerolinea);

}