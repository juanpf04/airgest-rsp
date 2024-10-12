package presentacion.comandos.aerolinea;

import negocio.aerolinea.SAAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class BajaAerolinea implements Comando {
	
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		boolean exito = sa.bajaAerolinea((int) datos);
		Evento evento = null;
		if (exito) {
			evento = Evento.VISTA_EXITO_ALTA_AEROLINEA;
		} else {
			evento = Evento.VISTA_FALLO_ALTA_AEROLINEA;
		}
		return new Contexto(evento, exito);
	}
}
