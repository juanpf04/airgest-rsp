package presentacion.comandos.aerolinea;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class AltaAerolinea implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		int id = sa.altaAerolinea((TAerolinea) datos);
		Evento evento = null;
		if (id != -1)
			evento = Evento.VISTA_EXITO_ALTA_AEROLINEA;
		else
			evento = Evento.VISTA_FALLO_ALTA_AEROLINEA;

		return new Contexto(evento, id);
	}
}
