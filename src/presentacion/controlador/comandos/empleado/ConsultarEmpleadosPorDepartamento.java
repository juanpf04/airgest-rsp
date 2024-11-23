/**
 * 
 */
package presentacion.controlador.comandos.empleado;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarEmpleadosPorDepartamento implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado se = fn.crearSAEmpleado();
		List<TEmpleado> empleados = se.consultarEmpleadosPorDepartamento((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_EMPLEADOS_POR_DEPARTAMENTO;
		return new Contexto(evento, empleados);
	}
}