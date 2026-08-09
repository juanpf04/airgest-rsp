package presentacion.controlador.comandos.empleado;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.empleado.SAEmpleado;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaEmpleado implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SAEmpleado se = fn.crearSAEmpleado();
		boolean exito = se.bajaEmpleado((int) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_BAJA_EMPLEADO;
		} else {
			evento = Evento.VISTA_FALLO_BAJA_EMPLEADO;
		}
		return new Contexto(evento, exito);
	}
}