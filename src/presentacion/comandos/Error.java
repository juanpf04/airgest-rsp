package presentacion.comandos;

import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class Error implements Comando {

	public Contexto ejecutar(Object datos) {
		return new Contexto(Evento.VISTA_ERROR);
	}
}
