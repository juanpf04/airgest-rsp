package negocio.empleado;

import java.util.List;

public interface SAEmpleado {

	public int altaEmpleado(TEmpleado tEmpleado);

	public boolean bajaEmpleado(int id);

	public TEmpleado consultarEmpleadoPorId(int id);

	public List<TEmpleado> consultarEmpleados();

	public boolean modificarEmpleado(TEmpleado tEmpleado);

	public List<TEmpleado> consultarEmpleadosPorDepartamento(int idDepartamento);
}