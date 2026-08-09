package presentacion.controlador.comandos.empleado;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarEmpleadoPorId implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado se = fn.crearSAEmpleado();
		TEmpleado empleado = se.consultarEmpleadoPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_EMPLEADO_POR_ID;

		return new Contexto(evento, empleado);
	}
}