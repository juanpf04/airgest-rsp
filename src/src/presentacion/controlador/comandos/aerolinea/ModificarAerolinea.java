package presentacion.controlador.comandos.aerolinea;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ModificarAerolinea implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		boolean exito = sa.modificarAerolinea((TAerolinea) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_MODIFICAR_AEROLINEA;
		} else {
			evento = Evento.VISTA_FALLO_MODIFICAR_AEROLINEA;
		}
		return new Contexto(evento, exito);
	}
}
