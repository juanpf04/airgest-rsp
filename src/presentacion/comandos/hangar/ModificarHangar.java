package presentacion.comandos.hangar;

import negocio.factoria.FactoriaNegocio;
import negocio.hangar.SAHangar;
import negocio.hangar.THangar;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarHangar implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAHangar sh = fn.crearSAHangar();
		boolean exito = sh.modificarHangar((THangar) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_HANGAR;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_HANGAR;
		}
		return new Contexto(evento, exito);
	}

}
