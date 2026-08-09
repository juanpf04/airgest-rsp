package presentacion.controlador.comandos.avion;

import negocio.avion.SAAvion;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class BajaAvion implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAvion sav = fn.crearSAAvion();
		boolean exito = sav.bajaAvion((int) datos);
		Evento evento = null;
		if (exito)
			evento = Evento.VISTA_EXITO_BAJA_AVION;
		else
			evento = Evento.VISTA_FALLO_BAJA_AVION;

		return new Contexto(evento, null);
	}

}
