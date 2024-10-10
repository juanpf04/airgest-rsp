package presentacion.comandos.avion;

import negocio.avion.SAAvion;
import negocio.avion.TAvion;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;


public class AltaAvion implements Comando
{
	@Override
	public Contexto ejecutar(Object datos)
	{
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAvion sav = fn.crearSAAvion();
		int id = sav.altaAvion((TAvion) datos);
		Evento evento = null;
		if (id != -1)
			evento = Evento.VISTA_EXITO_ALTA_AVION;
		else
			evento = Evento.VISTA_FALLO_ALTA_AVION;

		return new Contexto(evento, id);
	}
	
}
