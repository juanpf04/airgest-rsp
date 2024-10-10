package presentacion.comandos.hangar;

import negocio.factoria.FactoriaNegocio;
import negocio.hangar.SAHangar;
import negocio.hangar.THangar;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaHangar implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAHangar sh = fn.crearSAHangar();
		int id = sh.altaHangar((THangar) datos);
		Evento evento = null;
		if (id != -1) {
			evento = Evento.VISTA_EXITO_ALTA_HANGAR;
		} else {
			evento = Evento.VISTA_FALLO_ALTA_HANGAR;
		}
		return new Contexto(evento, id);
	}}
