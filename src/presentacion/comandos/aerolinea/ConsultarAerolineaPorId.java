package presentacion.comandos.aerolinea;

import negocio.aerolinea.SAAerolinea;
import negocio.aerolinea.TAerolinea;
import negocio.factoria.FactoriaNegocio;
import presentacion.comandos.Comando;
import presentacion.controlador.Contexto;
import presentacion.controlador.Evento;

public class ConsultarAerolineaPorId implements Comando {

	public Contexto ejecutar(Object datos) {
		FactoriaNegocio fn = FactoriaNegocio.getInstance();
		SAAerolinea sa = fn.crearSAAerolinea();
		TAerolinea aerolinea = sa.consultarAerolineaPorId((int) datos);
		Evento evento = Evento.VISTA_RESULTADO_CONSULTAR_AEROLINEA_POR_ID;

		return new Contexto(evento, aerolinea);
	}

}
