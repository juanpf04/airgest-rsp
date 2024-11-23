package presentacion.controlador.comandos.departamento;

import presentacion.controlador.comandos.Comando;
import negocio.factoria.FactoriaNegocioMall;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaDepartamento implements Comando {
	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocioMall fn = FactoriaNegocioMall.getInstance();
		SADepartamento sd = fn.crearSADepartamento();
		int id = sd.altaDepartamento((TDepartamento) datos);
		Evento evento = null;
		if (id != -1)
			evento = Evento.VISTA_EXITO_ALTA_DEPARTAMENTO;
		else
			evento = Evento.VISTA_FALLO_ALTA_DEPARTAMENTO;

		return new Contexto(evento, id);
	}
}