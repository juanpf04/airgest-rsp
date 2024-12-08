package negocio.marca;

import java.util.List;

public interface SAMarca {

	public int altaMarca(TMarca tMarca);

	public boolean bajaMarca(int id);

	public TMarca consultarMarcaPorId(int id);

	public List<TMarca> consultarMarcas();

	public boolean modificarMarca(TMarca tMarca);
}