package presentacion.comandos.avion;

import negocio.avion.SAAvion;
import negocio.avion.TAvion;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ModificarAvion implements Comando {

	@Override
	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAvion sav = fn.crearSAAvion();
		boolean exito = sav.modificarAvion((TAvion) datos);
		Evento evento = null;
		if (exito)
			evento = Evento.VISTA_EXITO_MODIFICAR_AVION;
		else
			evento = Evento.VISTA_EXITO_MODIFICAR_AVION;

		return new Contexto(evento, null);
	}

}
