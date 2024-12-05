
package negocio.departamento;

import java.util.List;


public interface SADepartamento {
	
	public TDepartamento consultarDepartamentoPorId(int id);

	
	public int altaDepartamento(TDepartamento departamento);

	
	public boolean bajaDepartamento(int id);

	
	public List<TDepartamento> consultarDepartamentos();

	
	public boolean modificarDepartamento(TDepartamento departamento);

	
	public double calcularNomina(int id);
}