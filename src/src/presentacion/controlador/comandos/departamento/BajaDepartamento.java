package presentacion.controlador.comandos.departamento;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.departamento.SADepartamento;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaDepartamento implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		boolean exito = sd.bajaDepartamento((int) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_BAJA_DEPARTAMENTO;
		} else {
			evento = Evento.VISTA_FALLO_BAJA_DEPARTAMENTO;
		}
		return new Contexto(evento, exito);
	}
}