package presentacion.controlador.comandos.empleado;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TEmpleado;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;


public class AltaEmpleado implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado se = fn.crearSAEmpleado();
		int id = se.altaEmpleado((TEmpleado) datos);
		Evento evento = null;
		if (id != -1)
			evento = Evento.VISTA_EXITO_ALTA_EMPLEADO;
		else
			evento = Evento.VISTA_FALLO_ALTA_EMPLEADO;

		return new Contexto(evento, id);
	}
}