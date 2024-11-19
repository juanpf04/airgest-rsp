package presentacion.controlador.comandos.modelo;

import negocio.factoria.FactoriaNegocio;
import negocio.modelo.SAModelo;
import negocio.modelo.TModelo;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class AltaModelo implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAModelo sm = fn.crearSAModelo();
		int id = sm.altaModelo((TModelo) datos);
		Evento evento = null;
		if (id != -1) {
			evento = Evento.VISTA_EXITO_ALTA_MODELO;
		} else {
			evento = Evento.VISTA_FALLO_ALTA_MODELO;
		}
		return new Contexto(evento, id);
	}

}