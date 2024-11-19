package presentacion.controlador.comandos.aerolinea;

import java.util.List;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;
import presentacion.controlador.comandos.Comando;

public class ConsultarTodasAerolineas implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		List<TAerolinea> aerolineas = sa.consultarTodasAerolineas();
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_TODAS_AEROLINEAS;
		return new Contexto(evento, aerolineas);
	}
}
