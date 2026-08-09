package negocio.departamento;

import java.util.List;

public interface SADepartamento {

	public TDepartamento consultarDepartamentoPorId(int id);

	public int altaDepartamento(TDepartamento tDepartamento);

	public boolean bajaDepartamento(int id);

	public List<TDepartamento> consultarDepartamentos();

	public boolean modificarDepartamento(TDepartamento tDepartamento);

	public double calcularNomina(int id);
}