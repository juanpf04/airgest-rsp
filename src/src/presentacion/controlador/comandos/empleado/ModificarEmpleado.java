package presentacion.controlador.comandos.empleado;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarEmpleado implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado se = fn.crearSAEmpleado();
		boolean exito = se.modificarEmpleado((TEmpleado) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_EMPLEADO;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_EMPLEADO;
		}
		return new Contexto(evento, exito);
	}
}