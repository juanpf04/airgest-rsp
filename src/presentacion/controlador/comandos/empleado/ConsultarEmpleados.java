package presentacion.controlador.comandos.empleado;

import presentacion.controlador.comandos.Comando;

import java.util.List;

import negocio.factoria.FactoriaNegocioMall;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarEmpleados implements Comando {
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado se = fn.crearSAEmpleado();
		List<TEmpleado> empleado = se.consultarEmpleados();
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_EMPLEADOS;
		return new Contexto(evento, empleado);
	}
}