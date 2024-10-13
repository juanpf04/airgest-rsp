package presentacion.comandos.personal;

import negocio.factoria.FactoriaNegocio;
import negocio.personal.SAPersonal;
import negocio.personal.TPersonal;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaPersonal implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAPersonal sp = fn.crearSAPersonal();
		int id = sp.altaPersonal((TPersonal) datos);
		Evento evento = null;
		if (id != -1) {
			evento = Evento.VISTA_EXITO_ALTA_PERSONAL;
		} else {
			evento = Evento.VISTA_FALLO_ALTA_PERSONAL;
		}
		return new Contexto(evento, id);
	}

}

